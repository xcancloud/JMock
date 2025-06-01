package cloud.xcan.jmock.core.parser.extractor;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.FunctionStartException;
import java.util.ArrayList;
import java.util.List;

public final class DefaultMockExpressionExtractor extends DefaultMockTextExtractor {

  public DefaultMockExpressionExtractor(String expression) {
    super(expression);
  }

  public DefaultMockExpressionExtractor(String expression, char escapeChar, char funcIdentifier) {
    super(expression, escapeChar, funcIdentifier);
  }

  @Override
  public List<FunctionToken> extract() {
    skipWhitespace();

    if (pos > maxPos || chars[pos] != funcIdentifier) {
      throw FunctionStartException.of(funcIdentifier, pos);
    }

    List<FunctionToken> tokens = new ArrayList<>(1);
    tokens.add(parseFunctionToken());
    return tokens;
  }
}
