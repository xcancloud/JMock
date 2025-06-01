package cloud.xcan.jmock.core.function.locale.mlocale;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.locale.MLocale;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleMockTest {

  @Test
  public void case1_fixedJoinerTest() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String locale = mock.mock();
      System.out.println("value: " + locale);
      Assertions.assertTrue(locale.startsWith("zh") ? locale.contains(MLocale.DEFAULT_JOINER)
          : !locale.contains(MLocale.DEFAULT_JOINER));
      Assertions.assertTrue(locale.equals("zh_CN") || locale.equals("en"));
    }
  }

  // * Full MockConstructor: @Locale(joiner)
  @Test
  public void case1_joinerTest() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{"-"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String locale = mock.mock();
      System.out.println("value: " + locale);
      Assertions.assertTrue(locale.startsWith("zh") ? locale.contains("-")
          : !locale.contains("-"));
      Assertions.assertTrue(locale.equals("zh-CN") || locale.equals("en"));
    }
  }
}
