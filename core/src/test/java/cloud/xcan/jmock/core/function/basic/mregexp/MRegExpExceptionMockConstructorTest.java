package cloud.xcan.jmock.core.function.basic.mregexp;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MRegExpExceptionMockConstructorTest {


  @Test
  public void MRegExp() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{""});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_UNACCEPTABLE_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

}
