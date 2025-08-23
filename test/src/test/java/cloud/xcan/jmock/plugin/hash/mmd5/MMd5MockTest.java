package cloud.xcan.jmock.plugin.hash.mmd5;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MMd5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MMd5MockTest {

  @Test
  public void case1_defaultSizeTest() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertEquals(32, str.length());
    }
  }

  @Test
  public void case2_16SizeAndMockTest() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{"16"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertEquals(16, str.length());
    }
  }

}
