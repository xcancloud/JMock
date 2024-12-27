
package cloud.xcan.comp.jmock.core.function.date.mlocaleDateTime;


import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleDateTimeExceptionTest {

  @Test
  public void exception1() {
    FunctionToken token = new FunctionToken("MLocaleDateTime", new String[]{"Asia/Shanghai"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      Assertions.assertEquals("Unknown pattern letter: i", e.getCause().getMessage());
    }
  }
}
