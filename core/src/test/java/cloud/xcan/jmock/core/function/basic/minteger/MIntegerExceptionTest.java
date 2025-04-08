package cloud.xcan.jmock.core.function.basic.minteger;


import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MIntegerExceptionTest {

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("Integer", new String[]{"10", "1L"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ConstructorMismatchException.class, e.getClass());
    }
  }

  @Test
  public void exception2() {
    FunctionToken token = new FunctionToken("Integer", new String[]{"10", "1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_MAX_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }
}
