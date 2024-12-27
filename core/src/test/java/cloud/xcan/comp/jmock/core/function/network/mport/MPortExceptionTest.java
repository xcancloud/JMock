package cloud.xcan.comp.jmock.core.function.network.mport;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MPortExceptionTest {

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("Port", new String[]{"0", "8888"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MIN_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Port", new String[]{"2000", "99999"});
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void minNullValueTest() {
    FunctionToken token = new FunctionToken("Port", new String[]{null, "9000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_NOT_NULL_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxNullValueTest() {
    FunctionToken token = new FunctionToken("Port", new String[]{"2000", null});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_NOT_NULL_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void minAndMaxValueTest() {
    FunctionToken token = new FunctionToken("Port", new String[]{"5000", "4000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }


}
