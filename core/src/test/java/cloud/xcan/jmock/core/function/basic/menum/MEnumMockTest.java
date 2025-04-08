package cloud.xcan.jmock.core.function.basic.menum;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MEnum;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MEnumMockTest {

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{"DocGenerator|B"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{"DocGenerator|B", "2:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MEnum",
        new String[]{"DocGenerator|B|C", "1:2:3", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

}
