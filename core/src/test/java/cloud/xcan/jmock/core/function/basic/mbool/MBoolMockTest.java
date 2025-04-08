package cloud.xcan.jmock.core.function.basic.mbool;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MBool;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MBoolMockTest {

  @Test
  public void case1_1t() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1", "男|女"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }
}
