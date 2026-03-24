package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SnowIdUtilsTest {

  @Test
  void nextId_monotonicWithinSameMillis() {
    SnowIdUtils id = new SnowIdUtils(1, 1);
    long a = id.nextId();
    long b = id.nextId();
    assertTrue(b > a);
  }

  @Test
  void invalidWorker_throws() {
    assertThrows(IllegalArgumentException.class, () -> new SnowIdUtils(-1, 0));
    assertThrows(IllegalArgumentException.class, () -> new SnowIdUtils(32, 0));
  }

  @Test
  void invalidDatacenter_throws() {
    assertThrows(IllegalArgumentException.class, () -> new SnowIdUtils(0, -1));
    assertThrows(IllegalArgumentException.class, () -> new SnowIdUtils(0, 32));
  }
}
