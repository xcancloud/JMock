package cloud.xcan.jmock.core.function.id.mguid;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.id.MGuid;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MGuidMockConstructorTest {


  @Test
  public void MGuid1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Guid",
        new String[]{});
    MGuid mock = (MGuid) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  /**
   * Full MockConstructor: @Float(min,max,scale,nullWeight)
   */
  @Test
  public void MGuid2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Guid",
        new String[]{"true"});
    MGuid mock = (MGuid) parser.parse(token);
    Assertions.assertNotNull(mock);
  }
}
