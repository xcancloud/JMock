package cloud.xcan.jmock.core.parser.extractor;

import static cloud.xcan.jmock.api.TokenChars.DEFAULT_ESCAPE_CHAR;
import static cloud.xcan.jmock.api.TokenChars.FUNC_IDENTIFIER;
import static cloud.xcan.jmock.api.TokenChars.FUNC_PARAM_END;
import static cloud.xcan.jmock.api.TokenChars.FUNC_PARAM_SEPARATOR;
import static cloud.xcan.jmock.api.TokenChars.FUNC_PARAM_START;
import static cloud.xcan.jmock.api.TokenChars.METHOD_CALL_IDENTIFIER;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_EF_IDENTIFIER_SAME_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_EMC_IDENTIFIER_SAME_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_FMC_IDENTIFIER_SAME_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_INPUT_TEXT_IS_EMPTY;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.exception.FunctionEndException;
import cloud.xcan.jmock.core.exception.FunctionNameException;
import cloud.xcan.jmock.core.exception.FunctionStartException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The default text tokenizer, the content of text can contain one or more function and method call
 * expressions, and the format can be xml, json, txt, etc.
 *
 * @author XiaoLong Liu
 */
public class DefaultMockTextExtractor implements MockExtractor<FunctionToken> {

  final char escapeIdentifier;

  final char functionIdentifier;

  final String text;

  final char[] charsToProcess;

  int pos;

  final int max;

  final List<FunctionToken> tokens = new ArrayList<>();

  public DefaultMockTextExtractor(String text) {
    this(text, DEFAULT_ESCAPE_CHAR, FUNC_IDENTIFIER, METHOD_CALL_IDENTIFIER);
  }

  public DefaultMockTextExtractor(String text, char escapeIdentifier, char functionIdentifier,
      char methodCallIdentifier) {
    if (text == null || text.isEmpty()) {
      ParamParseException.throw0(PARSER_INPUT_TEXT_IS_EMPTY);
    }
    if (escapeIdentifier == functionIdentifier) {
      throw ParamParseException
          .of(PARSER_EF_IDENTIFIER_SAME_T, new Object[]{escapeIdentifier, functionIdentifier});
    } else if (escapeIdentifier == methodCallIdentifier) {
      throw ParamParseException
          .of(PARSER_EMC_IDENTIFIER_SAME_T, new Object[]{escapeIdentifier, methodCallIdentifier});
    } else if (functionIdentifier == methodCallIdentifier) {
      throw ParamParseException
          .of(PARSER_FMC_IDENTIFIER_SAME_T, new Object[]{functionIdentifier, methodCallIdentifier});
    }
    this.text = text;
    this.charsToProcess = text.toCharArray();
    this.max = this.charsToProcess.length - 1;
    this.pos = 0;
    this.escapeIdentifier = escapeIdentifier;
    this.functionIdentifier = functionIdentifier;
  }

  /**
   * Note: Nested functions not supported.
   */
  @Override
  public List<FunctionToken> extract() {
    int tokenStart;
    StringBuilder name;
    StringBuilder params;
    while (this.pos < this.max) {
      char ch = this.charsToProcess[this.pos];
      ch = skipBlankChar(ch);
      ch = skipEscapeChar(ch);
      if (this.functionIdentifier == ch) {
        tokenStart = this.pos;
        this.pos++;
        ch = this.charsToProcess[this.pos];
        if (isFuncNameChar(true, ch)) {
          name = findName(ch);
          ch = this.charsToProcess[this.pos];
          ch = skipBlankChar(ch);
          if (ch == FUNC_PARAM_START) {
            this.pos++;
            ch = this.charsToProcess[this.pos];
            ch = skipBlankChar(ch);
            if (ch == FUNC_PARAM_END) {
              tokens.add(new FunctionToken(this.functionIdentifier, name.toString(),
                  tokenStart, this.pos, null, text.substring(tokenStart, this.pos + 1)));
            } else {
              params = findParams(ch);
              ch = this.charsToProcess[this.pos];
              if (ch == FUNC_PARAM_END) {
                tokens.add(new FunctionToken(this.functionIdentifier, name.toString(),
                    tokenStart, this.pos, splitAndEscape(params.toString(), FUNC_PARAM_SEPARATOR),
                    text.substring(tokenStart, this.pos + 1)));
              } else {
                throw FunctionEndException.of(name.toString(), this.pos);
              }
            }
          } else {
            throw FunctionStartException.of(name.toString(), this.pos);
          }
        } else {
          name = new StringBuilder();
          name.append(this.functionIdentifier).append(ch);
          throw FunctionNameException.of(name.toString(), this.pos, ch);
        }
      } else {
        // Ignore unsupported identifiers
        this.pos++;
      }
    }
    return this.tokens;
  }

  protected StringBuilder findName(char ch) {
    StringBuilder name = new StringBuilder();
    name.append(ch);
    this.pos++;
    ch = this.charsToProcess[this.pos];
    while (isFuncNameChar(false, ch) && this.pos < this.max - 1) {
      name.append(ch);
      this.pos++;
      ch = this.charsToProcess[this.pos];
    }
    return name;
  }

  protected StringBuilder findParams(char ch) {
    StringBuilder params = new StringBuilder();
    params.append(ch);
    this.pos++;
    ch = this.charsToProcess[this.pos];
    while (ch != FUNC_PARAM_END && this.pos < this.max) {
      params.append(ch);
      this.pos++;
      ch = this.charsToProcess[this.pos];
    }
    return params;
  }

  protected char skipEscapeChar(char ch) {
    while (isEscape(ch) && this.pos < this.max) {
      this.pos += 2;
      ch = this.charsToProcess[this.pos];
    }
    return ch;
  }

  private boolean isEscape(char ch) {
    return this.escapeIdentifier == ch;
  }

  protected char skipBlankChar(char ch) {
    while (isBlankChar(ch)) {
      this.pos++;
      ch = this.charsToProcess[this.pos];
    }
    return ch;
  }

  protected boolean isBlankChar(char ch) {
    return ch == ' ' || ch == '\t' || ch == '\r' || ch == '\n';
  }

  protected boolean isFuncNameChar(boolean isFirst, int c) {
    return isFirst ? c >= 'A' && c <= 'Z'
        : (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
  }

  public Map<String, String> splitAndEscape(String str, char split) {
    if (str == null || str.isEmpty()) {
      return null;
    }
    Map<String, String> map = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    char[] chars = str.toCharArray();
    int pos = 0;
    char ch = chars[pos];
    int paramIndex = 1;
    while (pos < chars.length) {
      if (isEscape(ch)) {
        pos++;
        ch = chars[pos];
        sb.append(ch);
      } else if (ch == split) {
        String[] param = sb.toString().split("=", 2);
        if (param.length >= 2) {
          map.put(param[0], param[1]);
        } else {
          map.put(String.valueOf(paramIndex), param[0]);
        }
        paramIndex++;
        sb = new StringBuilder();
      } else {
        ch = chars[pos];
        sb.append(ch);
      }
      pos++;
      if (pos < chars.length) {
        ch = chars[pos];
      } else {
        String[] param = sb.toString().split("=", 2);
        if (param.length >= 2) {
          map.put(param[0], param[1]);
        } else {
          map.put(String.valueOf(paramIndex), param[0]);
        }
        paramIndex++;
      }
    }
    return map;
  }

  public char getEscapeIdentifier() {
    return escapeIdentifier;
  }

  public char getFunctionIdentifier() {
    return functionIdentifier;
  }

  public String getText() {
    return text;
  }

  public char[] getCharsToProcess() {
    return charsToProcess;
  }

  public int getPos() {
    return pos;
  }

  public int getMax() {
    return max;
  }

  public List<FunctionToken> getTokens() {
    return tokens;
  }
}
