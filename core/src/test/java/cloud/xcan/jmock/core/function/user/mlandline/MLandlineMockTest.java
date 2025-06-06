package cloud.xcan.jmock.core.function.user.mlandline;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MLandline;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLandlineMockTest {


  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MLandline", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLandline mock = (MLandline) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
      Assertions.assertNotNull(str);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MLandline", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLandline mock = (MLandline) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
      Assertions.assertNotNull(str);
    }
  }

}
