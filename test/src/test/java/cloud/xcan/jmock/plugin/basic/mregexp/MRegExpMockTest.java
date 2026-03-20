package cloud.xcan.jmock.plugin.basic.mregexp;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MRegExp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MRegExpMockTest {

  @Test
  public void case1_basicRegex() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"[a-z][a-z][0-9]{2}"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      Assertions.assertTrue(str.matches("[a-z][a-z][0-9]{2}"),
          "Should match pattern [a-z][a-z][0-9]{2}, got: " + str);
    }
  }

  @Test
  public void case2_regexWithNullWeight() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"[a-z][a-z][0-9]{2}", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    boolean hasNull = false;
    boolean hasValue = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (str == null) {
        hasNull = true;
      } else {
        hasValue = true;
        Assertions.assertTrue(str.matches("[a-z][a-z][0-9]{2}"),
            "Should match pattern, got: " + str);
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 1:1 nullWeight");
    Assertions.assertTrue(hasValue, "Should produce non-null values too");
  }

  @Test
  public void case4_mobilePhoneRegex() throws Exception {
    FunctionToken token = new FunctionToken("RegExp", new String[]{"(1[3-9]\\d{9})"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      Assertions.assertTrue(str.matches("1[3-9]\\d{9}"),
          "Should match Chinese mobile pattern, got: " + str);
    }
  }
}
