package cloud.xcan.jmock.plugin.hash.msha;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.support.utils.EncryptionUtils;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MSha;
import org.junit.jupiter.api.Test;

public class MShaMockTest {

  @Test
  public void case1_defaultSizeTest() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2_16SizeAndMockTest() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA1_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA224_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA256_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case5() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{EncryptionUtils.SHA384_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }
}
