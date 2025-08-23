package cloud.xcan.jmock.plugin.id.muuid;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MUuid;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MUuidMockConstructorTest {


  @Test
  public void MUuid1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Uuid",
        new String[]{});
    MUuid mock = (MUuid) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(false, mock.getWithoutSeparator());
  }

  /**
   * Full MockConstructor: @Float(min,max,scale,nullWeight)
   */
  @Test
  public void MUuid2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Uuid",
        new String[]{"true"});
    MUuid mock = (MUuid) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(true, mock.getWithoutSeparator());
  }
}
