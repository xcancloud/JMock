package cloud.xcan.jmock.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import org.junit.jupiter.api.Test;

class SupportedLanguageTest {

  @Test
  void toLocale() {
    assertEquals(Locale.ENGLISH, SupportedLanguage.en.toLocale());
    assertEquals(Locale.CHINA, SupportedLanguage.zh_CN.toLocale());
  }

  @Test
  void contain() {
    assertTrue(SupportedLanguage.contain("en"));
    assertTrue(SupportedLanguage.contain("zh_CN"));
    assertFalse(SupportedLanguage.contain("fr"));
    assertFalse(SupportedLanguage.contain(""));
    assertFalse(SupportedLanguage.contain(null));
  }
}
