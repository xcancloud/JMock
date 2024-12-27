package cloud.xcan.comp.jmock.core.function.user.mage;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.user.MAge;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MAgeMockConstructorTest {


  @Test
  public void MAge1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Age",
        new String[]{});
    MAge mock = (MAge) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(1, mock.getMin());
    Assert.assertEquals(100, mock.getMax());
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
    Assert.assertNotNull(mock);
    Assert.assertEquals(104, mock.getMin());
    Assert.assertEquals(10000, mock.getMax());
  }
}
