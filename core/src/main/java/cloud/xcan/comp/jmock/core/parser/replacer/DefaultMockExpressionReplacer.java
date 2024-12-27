package cloud.xcan.comp.jmock.core.parser.replacer;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.MockFunction;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.comp.jmock.core.parser.extractor.DefaultMockExpressionExtractor;
import cloud.xcan.comp.jmock.core.parser.extractor.DefaultMockTextExtractor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultMockExpressionReplacer implements MockReplacer {

  private final SimpleMockFunctionTokenParser parser;
  private final Map<FunctionToken, MockFunction> functionTokenMap;

  public DefaultMockExpressionReplacer() {
    this.parser = new SimpleMockFunctionTokenParser();
    this.functionTokenMap = new HashMap<>();
  }

  public DefaultMockExpressionReplacer(SimpleMockFunctionTokenParser parser,
      HashMap<FunctionToken, MockFunction> functionTokenMap) {
    this.parser = parser;
    this.functionTokenMap = functionTokenMap;
  }

  @Override
  public synchronized String replace(String content) throws Exception {
    if (isEmpty(content)) {
      return content;
    }

    // Parse mock MockFunction
    return replace(content, new DefaultMockExpressionExtractor(content).extract());
  }

  @Override
  public synchronized String replace(String content, List<FunctionToken> tokens) throws Exception {
    if (isEmpty(content)) {
      return content;
    }

    // Parse mock MockFunction
    if (isEmpty(tokens)) {
      tokens = new DefaultMockTextExtractor(content).extract();
    }

    if (isEmpty(tokens)) {
      return content;
    }

    int offset = 0;
    StringBuilder result = new StringBuilder();
    for (FunctionToken token : tokens) {
      // Note: Non mock functions do not replace
      MockFunction function = parseAndCacheMockFunction(token);
      String value = isNull(function) ? token.token() // Set to src content
          : function.mock().toString(); // Set mock value
      result.append(content, 0, token.startPos() + offset).append(value);
      if (content.length() > token.endPos() + offset + 1) {
        result.append(content, token.endPos() + offset + 1, content.length());
      }
      content = result.toString();
      result = new StringBuilder();
      offset += value.length() - (token.endPos() - token.startPos() + 1);
    }
    return content;
  }

  /**
   * Fix:: Duplicate ID generated during multithreading. <- Use synchronized
   */
  private synchronized MockFunction parseAndCacheMockFunction(FunctionToken token)
      throws Exception {
    MockFunction function = functionTokenMap.get(token);
    if (isNull(function)) {
      function = parser.parse(token);
      functionTokenMap.put(token, function);
    }
    return function;
  }

}