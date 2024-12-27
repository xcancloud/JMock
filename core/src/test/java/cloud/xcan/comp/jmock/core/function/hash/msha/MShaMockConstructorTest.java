package cloud.xcan.comp.jmock.core.function.hash.msha;


import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA1_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA224_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA256_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA384_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA512_VERSION;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.hash.MSha;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MShaMockConstructorTest {

  @Test
  public void MSha() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(SHA512_VERSION, mock.getVersion());
    Assert.assertEquals(128, mock.mock().length());
  }

  @Test
  public void MSha_1() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA1_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(SHA1_VERSION, mock.getVersion());
    Assert.assertEquals(40, mock.mock().length());
  }

  @Test
  public void MSha_2() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{"SHA-224"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(SHA224_VERSION, mock.getVersion());
    Assert.assertEquals(56, mock.mock().length());
  }


  @Test
  public void MSha_3() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA256_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(SHA256_VERSION, mock.getVersion());
    Assert.assertEquals(64, mock.mock().length());
  }


  @Test
  public void MSha_4() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA384_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(SHA384_VERSION, mock.getVersion());
    Assert.assertEquals(96, mock.mock().length());
  }
}
