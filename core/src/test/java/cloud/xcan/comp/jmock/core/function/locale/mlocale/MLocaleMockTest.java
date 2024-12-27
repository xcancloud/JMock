package cloud.xcan.comp.jmock.core.function.locale.mlocale;

import static cloud.xcan.comp.jmock.core.function.locale.MLocale.DEFAULT_JOINER;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.locale.MLocale;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
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
      Assert.assertTrue(locale.startsWith("zh") ? locale.contains(DEFAULT_JOINER)
          : !locale.contains(DEFAULT_JOINER));
      Assert.assertTrue(locale.equals("zh_CN") || locale.equals("en"));
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
      Assert.assertTrue(locale.startsWith("zh") ? locale.contains("-")
          : !locale.contains("-"));
      Assert.assertTrue(locale.equals("zh-CN") || locale.equals("en"));
    }
  }
}
