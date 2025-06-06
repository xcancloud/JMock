package cloud.xcan.jmock.core.function.id.msnowid;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.id.MSnowId;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MSnowIdMockConstructorTest {


  @Test
  public void MSnowId1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("SnowId",
        new String[]{});
    MSnowId mock = (MSnowId) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1, mock.getDcId());
    Assertions.assertEquals(1, mock.getMId());
  }

  /**
   * Full MockConstructor: @Float(min,max,scale,nullWeight)
   */
  @Test
  public void MSnowId2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("SnowId",
        new String[]{"4", "5"});
    MSnowId mock = (MSnowId) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(4, mock.getDcId());
    Assertions.assertEquals(5, mock.getMId());
  }
}
