package cloud.xcan.jmock.plugin.geography.mcity;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MCity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCityMockConstructorTest {


  @Test
  public void MCity1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("City",
        new String[]{});
    MCity mock = (MCity) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDictArray());
  }

  @Test
  public void MCity2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("City",
        new String[]{"en"});
    MCity mock = (MCity) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDictArray());
  }

  @Test
  public void MCity3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("City",
        new String[]{"等我|耳机|佛山"});
    MCity mock = (MCity) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDictArray());
  }

}
