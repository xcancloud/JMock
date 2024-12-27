package cloud.xcan.comp.jmock.core.function.user.memail;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.user.MEmail;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MEmailMockTest {

  @Test
  public void email() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println("value = " + value);
    }
  }

  @Test
  public void email1() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"1", "2000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println("value = " + value);
    }
  }

  @Test
  public void email2() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"1", "20", "qq.com|wx.com"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println("value = " + value);
    }
  }


}
