package cloud.xcan.jmock.core.function.id.mguid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

/**
 * @author wjl
 */
public class MGuidExceptionTest {

  @Test
  public void exception1() {
    FunctionToken token = new FunctionToken("MGuid", new String[]{"*"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ConstructorMismatchException.class, e.getClass());
    }
  }
}
