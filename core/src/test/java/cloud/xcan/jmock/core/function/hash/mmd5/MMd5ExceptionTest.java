package cloud.xcan.jmock.core.function.hash.mmd5;


import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_UNACCEPTABLE_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MMd5ExceptionTest {

  @Test
  public void minSizeTest() {
    FunctionToken token = new FunctionToken("MMd5", new String[]{"10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_UNACCEPTABLE_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

}
