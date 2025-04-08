package cloud.xcan.jmock.core.function.basic.mregexp;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MRegExp;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MRegExpMockTest {

  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"[a-z][a-z][0-9]{2}"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"[a-z][a-z][0-9]{2}", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("email = " + str);
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"(1[3-9]\\d{9})"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("mobile = " + str);
    }
  }
}
