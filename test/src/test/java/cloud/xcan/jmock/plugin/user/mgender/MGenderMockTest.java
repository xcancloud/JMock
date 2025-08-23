package cloud.xcan.jmock.plugin.user.mgender;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MGender;
import org.junit.jupiter.api.Test;

public class MGenderMockTest {

  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("MGender", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGender mock = (MGender) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println("value = " + value);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MGender", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGender mock = (MGender) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println("value = " + value);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MGender", new String[]{"F|M"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGender mock = (MGender) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println("value = " + value);
    }
  }

}
