package cloud.xcan.jmock.core.parser;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.MockFunction;

public interface MockFunctionTokenParser {

  MockFunction parse(FunctionToken token) throws Exception;

}
