package cloud.xcan.jmock.api.i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ThreadLocaleHolderTest {

  @AfterEach
  void tearDown() {
    ThreadLocaleHolder.removeLocale();
  }

  @Test
  void setGetRemove() {
    ThreadLocaleHolder.setLocale(Locale.FRENCH);
    assertEquals(Locale.FRENCH, ThreadLocaleHolder.getLocale());
    ThreadLocaleHolder.removeLocale();
    assertNull(ThreadLocaleHolder.localeHolder.get());
    assertEquals(Locale.getDefault(), ThreadLocaleHolder.getLocale());
  }
}
