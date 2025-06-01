package cloud.xcan.jmock.core.parser.extractor;

import static cloud.xcan.angus.spec.utils.ObjectUtils.isEmpty;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_FUNC_NAME_EMPTY;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_FUNC_NAME_INVALID;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_FUNC_NAME_START_NOT_UPPERCASE;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_PARAMETER_NAME_INVALID;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_TEXT_IS_EMPTY;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.TokenChars;
import cloud.xcan.jmock.core.exception.FunctionEndException;
import cloud.xcan.jmock.core.exception.InvalidNameException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class DefaultMockTextExtractor implements MockExtractor {

  private static final Pattern VALID_FUNC_NAME = Pattern.compile("^[A-Z][a-zA-Z0-9]*$");
  private static final Pattern VALID_PARAM_NAME = Pattern.compile("^[a-z][a-zA-Z0-9]*$");

  protected final char escapeChar;
  protected final char funcIdentifier;
  protected final String text;
  protected final char[] chars;
  protected int pos;
  protected final int maxPos;
  protected final List<FunctionToken> tokens = new ArrayList<>();

  public DefaultMockTextExtractor(String text) {
    this(text, TokenChars.DEFAULT_ESCAPE_CHAR, TokenChars.FUNC_IDENTIFIER);
  }

  public DefaultMockTextExtractor(String text, char escapeChar, char funcIdentifier) {
    validateIdentifiers(escapeChar, funcIdentifier);

    if (isEmpty(text)) {
      throw InvalidNameException.of("", pos, PARSER_TEXT_IS_EMPTY);
    }

    this.text = text;
    this.chars = text.toCharArray();
    this.maxPos = chars.length - 1;
    this.pos = 0;
    this.escapeChar = escapeChar;
    this.funcIdentifier = funcIdentifier;
  }

  private void validateIdentifiers(char escapeChar, char funcIdentifier) {
    if (escapeChar == funcIdentifier) {
      throw new IllegalArgumentException(
          "Escape character and function identifier cannot be the same: " + escapeChar
      );
    }
  }

  @Override
  public List<FunctionToken> extract() {
    while (pos < maxPos) {
      char current = chars[pos];

      if (isEscaped()) {
        pos++;
        continue;
      }

      if (current == funcIdentifier) {
        tokens.add(parseFunctionToken());
      } else {
        pos++;
      }
    }
    return tokens;
  }

  public FunctionToken parseFunctionToken() {
    int start = pos;
    pos++; // Skip '@'

    String name = parseFunctionName();
    validateFunctionName(name);

    skipWhitespace();

    if (pos > maxPos || chars[pos] != TokenChars.FUNC_PARAM_START) {
      return new FunctionToken(funcIdentifier, name, start, pos,
          Collections.emptyMap(), text.substring(start, pos));
    }

    pos++; // Skip '('
    Map<String, String> params = parseParameters();

    skipWhitespace();

    if (pos > maxPos || chars[pos] != TokenChars.FUNC_PARAM_END) {
      throw FunctionEndException.of(name, pos);
    }

    pos++; // Skip ')'
    int end = pos;

    return new FunctionToken(funcIdentifier, name, start, end, params, text.substring(start, end));
  }

  private String parseFunctionName() {
    StringBuilder name = new StringBuilder();
    boolean isFirstChar = true;

    while (pos <= maxPos) {
      char current = chars[pos];

      if (isFirstChar) {
        if (!Character.isUpperCase(current)) {
          throw InvalidNameException.of("", pos, PARSER_FUNC_NAME_START_NOT_UPPERCASE);
        }
        isFirstChar = false;
      }

      if (!isValidNameChar(current)) {
        break;
      }

      name.append(current);
      pos++;
    }
    return name.toString();
  }

  private boolean isValidNameChar(char c) {
    return Character.isLetterOrDigit(c);
  }

  private void validateFunctionName(String name) {
    if (name.isEmpty()) {
      throw InvalidNameException.of(null, pos, PARSER_FUNC_NAME_EMPTY);
    }
    if (!VALID_FUNC_NAME.matcher(name).matches()) {
      throw InvalidNameException.of(name, pos, PARSER_FUNC_NAME_INVALID);
    }
  }

  private Map<String, String> parseParameters() {
    Map<String, String> params = new LinkedHashMap<>();
    int paramIndex = 0;
    StringBuilder currentParam = new StringBuilder();
    boolean inEscape = false;
    boolean isKeyValue = false;
    String currentKey = null;

    while (pos <= maxPos) {
      char current = chars[pos];

      if (inEscape) {
        currentParam.append(current);
        inEscape = false;
        pos++;
        continue;
      }

      if (current == escapeChar) {
        inEscape = true;
        pos++;
        continue;
      }

      if (current == TokenChars.FUNC_PARAM_END) {
        break;
      }

      if (current == TokenChars.FUNC_PARAM_SEPARATOR) {
        addParameter(params, currentParam.toString(), currentKey, paramIndex, isKeyValue);
        currentParam.setLength(0);
        currentKey = null;
        paramIndex++;
        isKeyValue = false;
        pos++;
        continue;
      }

      if (current == '=' && !isKeyValue) {
        currentKey = currentParam.toString();
        validateParamName(currentKey);
        currentParam.setLength(0);
        isKeyValue = true;
        pos++;
        continue;
      }

      currentParam.append(current);
      pos++;
    }

    // Add last parameter
    if (!currentParam.isEmpty() || paramIndex == 1) {
      addParameter(params, currentParam.toString(), currentKey, paramIndex, isKeyValue);
    }

    return params;
  }

  private void addParameter(Map<String, String> params, String value,
      String key, int index, boolean isKeyValue) {
    if (isKeyValue && key != null) {
      params.put(key, value);
    } else {
      params.put(String.valueOf(index), value);
    }
  }

  private void validateParamName(String name) {
    if (!VALID_PARAM_NAME.matcher(name).matches()) {
      throw InvalidNameException.of(name, pos, PARSER_PARAMETER_NAME_INVALID);
    }
  }

  public void skipWhitespace() {
    while (pos <= maxPos && Character.isWhitespace(chars[pos])) {
      pos++;
    }
  }

  private boolean isEscaped() {
    return pos > 0 && chars[pos - 1] == escapeChar;
  }
}

