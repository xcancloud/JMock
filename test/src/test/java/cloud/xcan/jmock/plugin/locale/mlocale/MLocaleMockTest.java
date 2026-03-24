package cloud.xcan.jmock.plugin.locale.mlocale;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleMockTest {

  /**
   * {@link MLocale#mock()} yields {@code lang} or {@code lang + joiner + country} (Java uses
   * lowercase language, uppercase country).
   */
  private static void assertWellFormedLocale(String value, String joiner) {
    int j = value.indexOf(joiner);
    if (j < 0) {
      Assertions.assertTrue(value.matches("[a-z]{2,3}"), () -> "language only: " + value);
    } else {
      Assertions.assertEquals(j, value.lastIndexOf(joiner),
          () -> "single joiner segment: " + value);
      String lang = value.substring(0, j);
      String region = value.substring(j + joiner.length());
      Assertions.assertTrue(lang.matches("[a-z]{2,3}"), () -> "language part: " + value);
      Assertions.assertTrue(region.matches("[A-Z]{2}"), () -> "country part: " + value);
    }
  }

  @Test
  public void case1_fixedJoinerTest() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    for (int i = 0; i < 30; i++) {
      assertWellFormedLocale(mock.mock(), MLocale.DEFAULT_JOINER);
    }
  }

  // * Full MockConstructor: @Locale(joiner)
  @Test
  public void case1_joinerTest() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{"-"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    for (int i = 0; i < 30; i++) {
      assertWellFormedLocale(mock.mock(), "-");
    }
  }
}
