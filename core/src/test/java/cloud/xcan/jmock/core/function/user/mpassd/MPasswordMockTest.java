package cloud.xcan.jmock.core.function.user.mpassd;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MPassword;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MPasswordMockTest {

  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("MPassword", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MPassword", new String[]{"5", "8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MPassword",
        new String[]{"1", "2000", "true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MPassword",
        new String[]{"5", "8", "true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  //
  @Test
  public void case5() throws Exception {
    FunctionToken token = new FunctionToken("MPassword", new String[]{"true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }


}
