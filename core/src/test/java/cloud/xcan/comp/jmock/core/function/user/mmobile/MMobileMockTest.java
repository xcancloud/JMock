package cloud.xcan.comp.jmock.core.function.user.mmobile;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.user.MMobile;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MMobileMockTest {

  @Test
  public void mobile() throws Exception {
    FunctionToken token = new FunctionToken("MMobile", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMobile mock = (MMobile) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String mobile = mock.mock();
      System.out.println("mobile = " + mobile);
    }
  }

  @Test
  public void mobile1() throws Exception {
    FunctionToken token = new FunctionToken("MMobile", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMobile mock = (MMobile) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String mobile = mock.mock();
      System.out.println("mobile = " + mobile);
    }
  }


}
