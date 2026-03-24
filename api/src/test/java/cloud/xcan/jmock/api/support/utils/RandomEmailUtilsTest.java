package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class RandomEmailUtilsTest {

  @Test
  void email_withAtSuffix() {
    String e = RandomEmailUtils.email(3, 5, new String[]{"@ex.com", "foo.com"});
    assertNotNull(e);
    assertTrue(e.contains("@"));
    assertTrue(e.contains("."));
  }

  @Test
  void email_blankSuffixList_fallsBackToDefaultDomain() {
    String e = RandomEmailUtils.email(3, 5, new String[]{""});
    assertNotNull(e);
    assertTrue(e.contains("@"), e);
    assertTrue(e.contains("."), e);
  }
}
