package cloud.xcan.jmock.core.function.user.mlandline;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MLandline;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MLandlineMockConstructorTest {


  @Test
  public void MLandline() throws Exception {
    FunctionToken token = new FunctionToken("MLandline", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLandline mock = (MLandline) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  @Test
  public void MLandline_1() throws Exception {
    FunctionToken token = new FunctionToken("MLandline", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLandline mock = (MLandline) parser.parse(token);
    Assert.assertNotNull(mock);
  }

}
