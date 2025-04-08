package cloud.xcan.jmock.core.function.id.muuid;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MUuidExceptionTest {

  @Test
  public void exception1() {
    FunctionToken token = new FunctionToken("MUuid", new String[]{"*"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ConstructorMismatchException.class, e.getClass());
    }
  }
}
