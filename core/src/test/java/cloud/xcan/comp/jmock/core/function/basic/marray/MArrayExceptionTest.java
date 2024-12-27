package cloud.xcan.comp.jmock.core.function.basic.marray;


import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_GENEXP_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_SIZE_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MArrayExceptionTest {

  @Test
  public void minSizeTest() {
    FunctionToken token = new FunctionToken("MArray", new String[]{"-1", "@String()"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_SIZE_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

  @Test
  public void illegalGenExpTest() {
    FunctionToken token = new FunctionToken("MArray", new String[]{"@KKK()"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_GENEXP_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

}
