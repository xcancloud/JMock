package cloud.xcan.jmock.core.function.hash.msha;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.hash.MSha;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.core.support.utils.EncryptionUtils;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MShaMockConstructorTest {

  @Test
  public void MSha() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(EncryptionUtils.SHA512_VERSION, mock.getVersion());
    Assert.assertEquals(128, mock.mock().length());
  }

  @Test
  public void MSha_1() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA1_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(EncryptionUtils.SHA1_VERSION, mock.getVersion());
    Assert.assertEquals(40, mock.mock().length());
  }

  @Test
  public void MSha_2() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{"SHA-224"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(EncryptionUtils.SHA224_VERSION, mock.getVersion());
    Assert.assertEquals(56, mock.mock().length());
  }


  @Test
  public void MSha_3() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA256_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(EncryptionUtils.SHA256_VERSION, mock.getVersion());
    Assert.assertEquals(64, mock.mock().length());
  }


  @Test
  public void MSha_4() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA384_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(EncryptionUtils.SHA384_VERSION, mock.getVersion());
    Assert.assertEquals(96, mock.mock().length());
  }
}
