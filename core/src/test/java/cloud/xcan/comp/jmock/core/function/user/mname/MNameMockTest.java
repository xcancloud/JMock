package cloud.xcan.comp.jmock.core.function.user.mname;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.user.MName;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MNameMockTest {


  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MName", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MName mock = (MName) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MName", new String[]{"张三|李四"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MName mock = (MName) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println(str);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MName", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MName mock = (MName) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println(str);
    }
  }

}
