package cloud.xcan.jmock.plugin.hash.mmd5;


import static cloud.xcan.jmock.api.support.utils.EncryptionUtils.MD5_16_LENGTH;
import static cloud.xcan.jmock.api.support.utils.EncryptionUtils.MD5_32_LENGTH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MMd5;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MMd5MockConstructorTest {

  @Test
  public void MMd5() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(MD5_32_LENGTH, mock.getLength());
    Assertions.assertEquals(32, mock.mock().length());
  }

  @Test
  public void MMd5_1() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{"16"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(MD5_16_LENGTH, mock.getLength());
    Assertions.assertEquals(16, mock.mock().length());
  }

}
