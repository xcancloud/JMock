package cloud.xcan.comp.jmock.core.function.user.mmobile;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.user.MMobile;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MMobileMockConstructorTest {


  @Test
  public void MLandline() throws Exception {
    FunctionToken token = new FunctionToken("MMobile", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMobile mock = (MMobile) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  @Test
  public void MLandline_1() throws Exception {
    FunctionToken token = new FunctionToken("MMobile", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMobile mock = (MMobile) parser.parse(token);
    Assert.assertNotNull(mock);
  }

}
