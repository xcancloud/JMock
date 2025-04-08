package cloud.xcan.jmock.core.parser.extractor;

import static cloud.xcan.jmock.api.TokenChars.FUNC_PARAM_END;
import static cloud.xcan.jmock.api.TokenChars.FUNC_PARAM_SEPARATOR;
import static cloud.xcan.jmock.api.TokenChars.FUNC_PARAM_START;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.FunctionEndException;
import cloud.xcan.jmock.core.exception.FunctionNameException;
import cloud.xcan.jmock.core.exception.FunctionStartException;
import java.util.List;

/**
 * The default expression tokenizer, only parses the text containing a valid expression.
 *
 * @author XiaoLong Liu
 */
public final class DefaultMockExpressionExtractor extends DefaultMockTextExtractor {

  public DefaultMockExpressionExtractor(String expression) {
    super(expression);
  }

  public DefaultMockExpressionExtractor(String expression, char escapeIdentifier,
      char functionIdentifier, char methodCallIdentifier) {
    super(expression, escapeIdentifier, functionIdentifier, methodCallIdentifier);
  }

  /**
   * Note: Nested functions not supported.
   */
  @Override
  public List<FunctionToken> extract() {
    char ch = this.charsToProcess[this.pos];
    ch = skipBlankChar(ch);
    if (ch != this.functionIdentifier) {
      throw FunctionStartException.of(this.functionIdentifier, this.pos);
    }
    int tokenStart;
    StringBuilder name;
    StringBuilder params;
    for (int i = 0; i < this.max; i++) {
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
            return tokens;
          } else {
            params = findParams(ch);
            ch = this.charsToProcess[this.pos];
            if (ch == FUNC_PARAM_END) {
              tokens.add(new FunctionToken(this.functionIdentifier, name.toString(),
                  tokenStart, this.pos, splitAndEscape(params.toString(), FUNC_PARAM_SEPARATOR),
                  text.substring(tokenStart, this.pos + 1)));
              return tokens;
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
    }
    return this.tokens;
  }
}
