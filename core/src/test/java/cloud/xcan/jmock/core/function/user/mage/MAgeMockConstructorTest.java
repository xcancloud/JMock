package cloud.xcan.jmock.core.function.user.mage;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MAge;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAgeMockConstructorTest {


  @Test
  public void MAge1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Age",
        new String[]{});
    MAge mock = (MAge) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1, mock.getMin());
    Assertions.assertEquals(100, mock.getMax());
  }

  /**
   * Full MockConstructor: @MAge(min,max)
   */
  @Test
  public void MAge2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Age",
        new String[]{"104", "10000"});
    MAge mock = (MAge) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(104, mock.getMin());
    Assertions.assertEquals(10000, mock.getMax());
  }
}
