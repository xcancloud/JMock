package cloud.xcan.jmock.core.function.locale.mtimezone;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MTimeZoneExceptionTest {

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("MTimeZone", new String[]{"**",""});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ConstructorMismatchException.class, e.getClass());
    }
  }

}
