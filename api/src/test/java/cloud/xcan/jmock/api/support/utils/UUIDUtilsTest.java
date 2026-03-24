package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class UUIDUtilsTest {

  @Test
  void randomStringUUID_withDashes() {
    String u = UUIDUtils.randomStringUUID(false);
    assertNotNull(u);
    assertTrue(u.contains("-"));
    assertEquals(36, u.length());
  }

  @Test
  void randomStringUUID_withoutDashes() {
    String u = UUIDUtils.randomStringUUID(true);
    assertNotNull(u);
    assertFalse(u.contains("-"));
    assertEquals(32, u.length());
  }
}
