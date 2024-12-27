package cloud.xcan.comp.jmock.core.function.hash.mmd5;


import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.MD5_16_LENGTH;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.MD5_32_LENGTH;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.hash.MMd5;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MMd5MockConstructorTest {

  @Test
  public void MMd5() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(MD5_32_LENGTH, mock.getLength());
    org.junit.Assert.assertEquals(32, mock.mock().length());
  }

  @Test
  public void MMd5_1() throws Exception {
    FunctionToken token = new FunctionToken("MMd5", new String[]{"16"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMd5 mock = (MMd5) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(MD5_16_LENGTH, mock.getLength());
    org.junit.Assert.assertEquals(16, mock.mock().length());
  }

}
