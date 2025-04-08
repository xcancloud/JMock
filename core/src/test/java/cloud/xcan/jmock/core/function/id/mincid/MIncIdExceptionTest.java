package cloud.xcan.jmock.core.function.id.mincid;


import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MIncIdExceptionTest {

  @Test
  public void stepNullValueTest() {
    FunctionToken token = new FunctionToken("IncId", new String[]{"1", "-1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_MIN_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }
}
