package cloud.xcan.jmock.plugin.basic.mlong;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLong;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLongMockTest {

  @Test
  public void MLong1_defaultRange() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      Long val = mock.mock();
      Assertions.assertNotNull(val);
    }
  }

  @Test
  public void MLong2_customRange() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"-100L", "100L"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      Long val = mock.mock();
      Assertions.assertNotNull(val);
      Assertions.assertTrue(val >= -100L && val <= 100L,
          "Value out of range: " + val);
    }
  }

  @Test
  public void MLong3_nullWeight() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    boolean hasNull = false;
    boolean hasValue = false;
    for (int i = 0; i < 1000; i++) {
      Long val = mock.mock();
      if (val == null) {
        hasNull = true;
      } else {
        hasValue = true;
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 1:1 nullWeight");
    Assertions.assertTrue(hasValue, "Should produce non-null values too");
  }

  @Test
  public void MLong4_rangeAndNullWeight() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"-100L", "100L", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      Long val = mock.mock();
      if (val == null) {
        hasNull = true;
      } else {
        Assertions.assertTrue(val >= -100L && val <= 100L,
            "Value out of range: " + val);
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 1:1 nullWeight");
  }
}
