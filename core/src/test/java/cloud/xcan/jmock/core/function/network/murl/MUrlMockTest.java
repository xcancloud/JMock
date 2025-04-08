package cloud.xcan.jmock.core.function.network.murl;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.network.MUrl;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MUrlMockTest {

  @Test
  public void url() throws Exception {
    FunctionToken token = new FunctionToken("MUrl", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String url = mock.mock();
      System.out.println("url = " + url);
    }
  }

  @Test
  public void url1() throws Exception {
    FunctionToken token = new FunctionToken("MUrl", new String[]{"80"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String url = mock.mock();
      System.out.println("url = " + url);
    }
  }

  @Test
  public void url2() throws Exception {
    FunctionToken token = new FunctionToken("MUrl",
        new String[]{"60", "https", "www.xcan.org", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String url = mock.mock();
      System.out.println("url = " + url);
    }
  }

  @Test
  public void url3() throws Exception {
    FunctionToken token = new FunctionToken("MUrl",
        new String[]{"60", "https", "www.xcan.org", "false"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String url = mock.mock();
      System.out.println("url = " + url);
    }
  }

}
