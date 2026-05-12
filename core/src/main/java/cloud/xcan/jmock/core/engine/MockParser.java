package cloud.xcan.jmock.core.engine;

import cloud.xcan.jmock.api.TokenChars;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Lexer and Parser combined: scans input text and produces a list of {@link MockExpr} nodes.
 * <p>
 * Handles:
 * <ul>
 *   <li>Text segments (non-function content)</li>
 *   <li>Function calls: @FuncName() or @FuncName(param1, param2)</li>
 *   <li>Nested function calls: @Repeat(@Email(), 5)</li>
 *   <li>Escape sequences: \@ produces literal @</li>
 * </ul>
 *
 * @since 2.0.0
 */
public final class MockParser {

  private static final Pattern VALID_FUNC_NAME = Pattern.compile("^[A-Z][a-zA-Z0-9]*$");

  private final char escapeChar;
  private final char funcIdentifier;

  public MockParser() {
    this(TokenChars.DEFAULT_ESCAPE_CHAR, TokenChars.FUNC_IDENTIFIER);
  }

  public MockParser(char escapeChar, char funcIdentifier) {
    this.escapeChar = escapeChar;
    this.funcIdentifier = funcIdentifier;
  }

  /**
   * Parse input text into a list of AST nodes (TextSegments and FunctionCalls).
   */
  public List<MockExpr> parse(String text) {
    if (text == null || text.isEmpty()) {
      return Collections.emptyList();
    }

    List<MockExpr> nodes = new ArrayList<>();
    char[] chars = text.toCharArray();
    int len = chars.length;
    int pos = 0;
    StringBuilder textBuf = new StringBuilder();
    int textStart = 0;

    while (pos < len) {
      // Handle escape
      if (chars[pos] == escapeChar && pos + 1 < len) {
        char next = chars[pos + 1];
        if (next == funcIdentifier || next == ',' || next == '|' || next == escapeChar) {
          textBuf.append(next);
          pos += 2;
          continue;
        }
      }

      if (chars[pos] == funcIdentifier && pos + 1 < len && Character.isUpperCase(chars[pos + 1])) {
        // Flush text buffer
        if (!textBuf.isEmpty()) {
          nodes.add(new MockExpr.TextSegment(textBuf.toString(), textStart, pos));
          textBuf.setLength(0);
        }

        ParseResult result = parseFunctionCall(chars, pos, len);
        nodes.add(result.expr);
        pos = result.endPos;
        textStart = pos;
      } else {
        if (textBuf.isEmpty()) {
          textStart = pos;
        }
        textBuf.append(chars[pos]);
        pos++;
      }
    }

    // Flush remaining text
    if (!textBuf.isEmpty()) {
      nodes.add(new MockExpr.TextSegment(textBuf.toString(), textStart, pos));
    }

    return nodes;
  }

  /**
   * Parse a single function expression (strict mode: input must be exactly one function).
   */
  public MockExpr parseExpression(String expression) {
    List<MockExpr> nodes = parse(expression);
    if (nodes.size() != 1 || !(nodes.getFirst() instanceof MockExpr.FunctionCall)) {
      throw new IllegalArgumentException(
          "Expected a single function expression, got: " + expression);
    }
    return nodes.getFirst();
  }

  private ParseResult parseFunctionCall(char[] chars, int pos, int len) {
    int start = pos;
    pos++; // Skip '@'

    // Parse function name
    StringBuilder name = new StringBuilder();
    while (pos < len && Character.isLetterOrDigit(chars[pos])) {
      name.append(chars[pos]);
      pos++;
    }

    String funcName = name.toString();
    if (funcName.isEmpty() || !VALID_FUNC_NAME.matcher(funcName).matches()) {
      // Not a valid function, treat as text
      return new ParseResult(
          new MockExpr.TextSegment(String.valueOf(chars, start, pos - start), start, pos), pos);
    }

    // Skip whitespace
    while (pos < len && Character.isWhitespace(chars[pos])) {
      pos++;
    }

    // No parameters?
    if (pos >= len || chars[pos] != TokenChars.FUNC_PARAM_START) {
      return new ParseResult(
          new MockExpr.FunctionCall(funcName, Collections.emptyList(), false, start, pos), pos);
    }

    pos++; // Skip '('

    // Parse parameters
    List<MockExpr.Argument> args = new ArrayList<>();
    int argIndex = 0;
    StringBuilder argBuf = new StringBuilder();
    boolean inEscape = false;
    boolean inQuote = false;       // currently inside a "..." literal
    boolean argQuoted = false;     // current argument contained a quoted segment

    while (pos < len && (inQuote || chars[pos] != TokenChars.FUNC_PARAM_END)) {
      if (inEscape) {
        argBuf.append(chars[pos]);
        inEscape = false;
        pos++;
        continue;
      }

      if (chars[pos] == escapeChar) {
        inEscape = true;
        pos++;
        continue;
      }

      // Quote handling: strip surrounding double quotes, keep inner content
      // (including commas, colons, etc.) as a single literal value.
      if (chars[pos] == '"') {
        inQuote = !inQuote;
        argQuoted = true;
        pos++;
        continue;
      }

      // Inside quotes: every char (including ',') is literal content
      if (inQuote) {
        argBuf.append(chars[pos]);
        pos++;
        continue;
      }

      // Nested function call as argument
      if (chars[pos] == funcIdentifier && pos + 1 < len && Character.isUpperCase(chars[pos + 1])) {
        ParseResult nested = parseFunctionCall(chars, pos, len);
        args.add(MockExpr.Argument.expr(String.valueOf(argIndex), nested.expr));
        argIndex++;
        pos = nested.endPos;

        // Skip trailing comma after nested expression
        while (pos < len && Character.isWhitespace(chars[pos])) {
          pos++;
        }
        if (pos < len && chars[pos] == TokenChars.FUNC_PARAM_SEPARATOR) {
          pos++;
        }
        continue;
      }

      if (chars[pos] == TokenChars.FUNC_PARAM_SEPARATOR) {
        args.add(MockExpr.Argument.literal(String.valueOf(argIndex), argBuf.toString()));
        argBuf.setLength(0);
        argIndex++;
        argQuoted = false;
        pos++;
        continue;
      }

      argBuf.append(chars[pos]);
      pos++;
    }

    // Last argument: include even if buffer is empty when it was an explicit "" literal
    if (!argBuf.isEmpty() || argQuoted) {
      args.add(MockExpr.Argument.literal(String.valueOf(argIndex), argBuf.toString()));
    }

    if (pos < len && chars[pos] == TokenChars.FUNC_PARAM_END) {
      pos++; // Skip ')'
    }

    boolean explicitEmptyParens = args.isEmpty();
    MockExpr.FunctionCall call =
        new MockExpr.FunctionCall(funcName, args, explicitEmptyParens, start, pos);

    // Check if this is a Repeat call → convert to ArrayExpr
    if ("Repeat".equals(funcName) && args.size() >= 2) {
      MockExpr.Argument first = args.getFirst();
      MockExpr.Argument second = args.get(1);
      if (first.isExpression() && !second.isExpression()) {
        try {
          int count = Integer.parseInt(second.literalValue().trim());
          return new ParseResult(new MockExpr.ArrayExpr(first.exprValue(), count, start, pos), pos);
        } catch (NumberFormatException ignored) {
          // Fall through to regular function call
        }
      }
    }

    return new ParseResult(call, pos);
  }

  private record ParseResult(MockExpr expr, int endPos) {

  }
}
