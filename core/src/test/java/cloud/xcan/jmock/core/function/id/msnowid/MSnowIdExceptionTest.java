package cloud.xcan.jmock.core.function.id.msnowid;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.lang.reflect.InvocationTargetException;
import org.junit.jupiter.api.Test;

public class MSnowIdExceptionTest {

  @Test
  public void dcIdNullValueTest() {
    FunctionToken token = new FunctionToken("MSnowId", new String[]{"-1", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("dataCenterId can't be greater than -1 or less than 0",
          ((InvocationTargetException) e).getTargetException().getMessage());
    }
  }

  @Test
  public void midNullValueTest() {
    FunctionToken token = new FunctionToken("MSnowId", new String[]{"1", "-1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("workerId can't be greater than -1 or less than 0",
          ((InvocationTargetException) e).getTargetException().getMessage());
    }
  }
}
