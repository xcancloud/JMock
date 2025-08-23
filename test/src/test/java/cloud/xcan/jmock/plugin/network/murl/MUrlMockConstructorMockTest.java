package cloud.xcan.jmock.plugin.network.murl;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MUrl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MUrlMockConstructorMockTest {

  @Test
  public void url() throws Exception {
    FunctionToken token = new FunctionToken("MUrl", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("127.0.0.1:8080", mock.getDomain());
    Assertions.assertEquals("http", mock.getProtocol());
  }

  @Test
  public void url1() throws Exception {
    FunctionToken token = new FunctionToken("MUrl", new String[]{"60"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(!mock.isAllowQueryParams());
  }

  @Test
  public void url2() throws Exception {
    FunctionToken token = new FunctionToken("MUrl",
        new String[]{"60", "https", "www.xcan.org", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("https", mock.getProtocol());
    Assertions.assertEquals("www.xcan.org", mock.getDomain());
    Assertions.assertTrue(mock.isAllowQueryParams());
  }

}
