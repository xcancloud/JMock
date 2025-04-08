package cloud.xcan.jmock.core.function.user.mpassd;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MPassd;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MPassdMockTest {

  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("MPassd", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MPassd", new String[]{"5", "8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MPassd",
        new String[]{"1", "2000", "true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MPassd",
        new String[]{"5", "8", "true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }

  //
  @Test
  public void case5() throws Exception {
    FunctionToken token = new FunctionToken("MPassd", new String[]{"true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String passd = mock.mock();
      System.out.println("passd = " + passd);
    }
  }


}
