
package cloud.xcan.jmock.core.function.date.mlocaleTime;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleTimeExceptionTest {

  @Test
  public void exception1() {
    FunctionToken token = new FunctionToken("MLocaleTime", new String[]{"Asia/Shanghai"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      Assertions.assertEquals("Unknown pattern letter: i", e.getCause().getMessage());
    }
  }

  @Test
  public void exception2() {
    FunctionToken token = new FunctionToken("MLocaleTime", new String[]{"HH:mm:ss", "aa"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("Unknown time-zone ID: aa", e.getCause().getMessage());
    }
  }
}
