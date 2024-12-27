package cloud.xcan.comp.jmock.core.parser;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.MockFunction;

public interface MockFunctionTokenParser {

  MockFunction parse(FunctionToken token) throws Exception;

}
