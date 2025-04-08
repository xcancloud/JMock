package cloud.xcan.jmock.core.function.geography.mlatitude;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MLatitudeExceptionTest {

  @Test
  public void minLatNullValueTest() {
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{null, "70D", "7", "1:9"});
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
  public void maxLatNullValueTest() {
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{"40D", null, "7", "1:9"});
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
  public void minLatValueTest() {
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{"-91D", "70D", "7", "1:9"});
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
  public void maxLatValueTest() {
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{"40D", "91D", "7", "1:9"});
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

  @Test
  public void minMaxLatValueTest() {
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{"40D", "30D", "7", "1:9"});
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
