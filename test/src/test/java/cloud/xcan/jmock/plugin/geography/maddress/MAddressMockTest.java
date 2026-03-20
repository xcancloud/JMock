package cloud.xcan.jmock.plugin.geography.maddress;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MAddress;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAddressMockTest {

  @Test
  public void MAddress1_defaultChinese() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String addr = mock.mock();
      Assertions.assertNotNull(addr);
      Assertions.assertTrue(addr.length() >= 5, "Address too short: " + addr);
    }
  }

  @Test
  public void MAddress2_english() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String addr = mock.mock();
      Assertions.assertNotNull(addr);
      Assertions.assertTrue(addr.length() >= 5, "English address too short: " + addr);
    }
  }

  @Test
  public void MAddress3_customDict() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{"123|4537"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    Set<String> expected = new HashSet<>();
    expected.add("123");
    expected.add("4537");
    for (int i = 0; i < 20; i++) {
      String addr = mock.mock();
      Assertions.assertNotNull(addr);
      Assertions.assertTrue(expected.contains(addr),
          "Expected one of " + expected + " but got: " + addr);
    }
  }
}
