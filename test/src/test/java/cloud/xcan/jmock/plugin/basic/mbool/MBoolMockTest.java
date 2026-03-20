package cloud.xcan.jmock.plugin.basic.mbool;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MBool;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MBoolMockTest {

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      Assertions.assertTrue("true".equals(str) || "false".equals(str),
          "Expected true/false but got: " + str);
    }
  }

  @Test
  public void case2_withNullWeight() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      Assertions.assertTrue("true".equals(str) || "false".equals(str));
    }
  }

  @Test
  public void case3_withNullWeightProducesNull() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    boolean hasNull = false;
    boolean hasValue = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (str == null) {
        hasNull = true;
      } else {
        hasValue = true;
        Assertions.assertTrue("true".equals(str) || "false".equals(str));
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 1:1 nullWeight");
    Assertions.assertTrue(hasValue, "Should produce non-null values too");
  }

  @Test
  public void case4_customValues() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1", "男|女"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (str == null) {
        hasNull = true;
      } else {
        Assertions.assertTrue("男".equals(str) || "女".equals(str),
            "Expected 男/女 but got: " + str);
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 1:1 nullWeight");
  }
}
