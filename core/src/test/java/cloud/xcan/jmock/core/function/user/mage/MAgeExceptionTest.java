package cloud.xcan.jmock.core.function.user.mage;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MAgeExceptionTest {

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("Age", new String[]{"-1", "10"});
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
  public void minAndMaxValueTest() {
    FunctionToken token = new FunctionToken("Age", new String[]{"10", "9"});
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
