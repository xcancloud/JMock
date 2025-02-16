package cloud.xcan.comp.jmock.core.function.basic.mfloat;


import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MFloatExceptionTest {

  @Test
  public void maxValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Float", new String[]{"1f", "-1f"});
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_MIN_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

  @Test
  public void minAndMaxValueTest() {
    FunctionToken token = new FunctionToken("Float", new String[]{"10", "9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ConstructorMismatchException.class, e.getClass());
    }
  }

  @Test
  public void nullWeightValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Float", new String[]{"a:1"});
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_WEIGHT_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }
}
