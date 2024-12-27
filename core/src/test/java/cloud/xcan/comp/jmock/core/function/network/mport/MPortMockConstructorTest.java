package cloud.xcan.comp.jmock.core.function.network.mport;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.network.MPort;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MPortMockConstructorTest {


  @Test
  public void MPort1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Port",
        new String[]{});
    MPort mock = (MPort) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1024, mock.getMin());
    Assertions.assertEquals(65535, mock.getMax());
  }

  /**
   * Full MockConstructor: @Float(min,max,scale,nullWeight)
   */
  @Test
  public void MPort2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Port",
        new String[]{"1200", "35000"});
    MPort mock = (MPort) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1200, mock.getMin());
    Assertions.assertEquals(35000, mock.getMax());
  }
}
