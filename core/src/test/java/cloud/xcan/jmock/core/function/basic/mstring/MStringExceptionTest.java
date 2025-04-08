package cloud.xcan.jmock.core.function.basic.mstring;


import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MStringExceptionTest {

  @BeforeAll
  public static void setLocale() {
    Locale.setDefault(new Locale("en"));
  }

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("String", new String[]{"-1", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_MIN_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

  @Test
  public void maxValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("String", new String[]{"1", "-1"});
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
    FunctionToken token = new FunctionToken("String", new String[]{"10", "9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_MAX_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

  @Test
  public void nullWeightValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("String", new String[]{"10", "a:1"});
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_WEIGHT_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }
}
