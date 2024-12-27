package cloud.xcan.comp.jmock.core.function.hash.msha;

import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA1_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA224_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA256_VERSION;
import static cloud.xcan.comp.jmock.core.support.utils.EncryptionUtils.SHA384_VERSION;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.hash.MSha;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
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
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA1_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA224_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA256_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case5() throws Exception {
    FunctionToken token = new FunctionToken("MSha", new String[]{SHA384_VERSION});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSha mock = (MSha) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }
}
