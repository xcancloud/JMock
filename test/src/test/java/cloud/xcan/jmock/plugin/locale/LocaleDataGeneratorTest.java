package cloud.xcan.jmock.plugin.locale;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MLocale;
import cloud.xcan.jmock.plugin.MTimeZone;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class LocaleDataGeneratorTest {

  @RepeatedTest(20)
  void testMLocale_default() {
    String locale = new MLocale().mock();
    assertNotNull(locale);
    assertFalse(locale.isEmpty());
  }

  @Test
  void testMLocale_variety() {
    Set<String> locales = new HashSet<>();
    MLocale gen = new MLocale();
    for (int i = 0; i < 50; i++) {
      locales.add(gen.mock());
    }
    assertTrue(locales.size() >= 5, "Should generate varied locales, got: " + locales.size());
  }

  @RepeatedTest(20)
  void testMTimeZone_default() {
    String tz = new MTimeZone().mock();
    assertNotNull(tz);
    assertFalse(tz.isEmpty());
  }

  @Test
  void testMTimeZone_variety() {
    Set<String> zones = new HashSet<>();
    MTimeZone gen = new MTimeZone();
    for (int i = 0; i < 50; i++) {
      zones.add(gen.mock());
    }
    assertTrue(zones.size() >= 5, "Should generate varied timezones, got: " + zones.size());
  }
}
