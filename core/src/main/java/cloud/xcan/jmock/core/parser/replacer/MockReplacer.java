package cloud.xcan.jmock.core.parser.replacer;

import cloud.xcan.jmock.api.FunctionToken;
import java.util.List;

/**
 * Replace the MockFunction function in the text with the function value
 */
public interface MockReplacer {

  String replace(String content) throws Exception;

  String replace(String content, List<FunctionToken> tokens) throws Exception;

}
