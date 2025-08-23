package cloud.xcan.jmock.plugin.user.mmobile;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MMobile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MMobileMockConstructorTest {


  @Test
  public void MLandline() throws Exception {
    FunctionToken token = new FunctionToken("MMobile", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMobile mock = (MMobile) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MLandline_1() throws Exception {
    FunctionToken token = new FunctionToken("MMobile", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMobile mock = (MMobile) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

}
