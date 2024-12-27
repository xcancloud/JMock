package cloud.xcan.comp.jmock.core.function.locale.mlocale;


import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_LENGTH_T;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MLocaleExceptionTest {

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("Locale", new String[]{"**"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_LENGTH_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

}
