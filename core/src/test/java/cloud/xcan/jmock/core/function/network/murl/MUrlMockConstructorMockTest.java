package cloud.xcan.jmock.core.function.network.murl;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.network.MUrl;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MUrlMockConstructorMockTest {

  @Test
  public void url() throws Exception {
    FunctionToken token = new FunctionToken("MUrl", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals("127.0.0.1:8080", mock.getDomain());
    Assert.assertEquals("http", mock.getProtocol());
  }

  @Test
  public void url1() throws Exception {
    FunctionToken token = new FunctionToken("MUrl", new String[]{"60"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(!mock.isAllowQueryParams());
  }

  @Test
  public void url2() throws Exception {
    FunctionToken token = new FunctionToken("MUrl",
        new String[]{"60","https", "www.xcan.org", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MUrl mock = (MUrl) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals("https", mock.getProtocol());
    Assert.assertEquals("www.xcan.org", mock.getDomain());
    Assert.assertTrue(mock.isAllowQueryParams());
  }

}
