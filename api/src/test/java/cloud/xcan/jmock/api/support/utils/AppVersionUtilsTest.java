package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppVersionUtilsTest {

  @Test
  void buildAppVersion_pattern() {
    String v = AppVersionUtils.buildAppVersion();
    assertNotNull(v);
    assertTrue(v.matches("\\d+\\.\\d+\\.\\d+"));
  }

  @Test
  void appVersion_withDicts() {
    String v = AppVersionUtils.appVersion(
        new String[]{"p"},
        new String[]{"alpha"},
        new String[]{"build"});
    assertTrue(v.startsWith("p-"));
    assertTrue(v.contains("+build"));
  }

  @Test
  void appVersion_nullDicts() {
    String v = AppVersionUtils.appVersion(null, null, null);
    assertTrue(v.matches("\\d+\\.\\d+\\.\\d+"));
  }
}
