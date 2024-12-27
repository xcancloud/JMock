package cloud.xcan.comp.jmock.core.function.hash.mmd5;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.hash.MMd5;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MMd5MockTest {

  @Test
  public void case1_defaultSizeTest() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      org.junit.Assert.assertEquals(32, str.length());
    }
  }

  @Test
  public void case2_16SizeAndMockTest() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{"16"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      org.junit.Assert.assertEquals(16, str.length());
    }
  }

}
