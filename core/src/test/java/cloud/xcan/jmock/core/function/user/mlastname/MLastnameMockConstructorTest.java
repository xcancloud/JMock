package cloud.xcan.jmock.core.function.user.mlastname;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MLastname;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLastnameMockConstructorTest {


  @Test
  public void MLastname() throws Exception {
    FunctionToken token = new FunctionToken("MLastname", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLastname mock = (MLastname) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.mock());
  }

  @Test
  public void MLastname_1() throws Exception {
    FunctionToken token = new FunctionToken("MLastname", new String[]{"张|王"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLastname mock = (MLastname) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(List.of("张", "王").contains(mock.mock()));
  }

}
