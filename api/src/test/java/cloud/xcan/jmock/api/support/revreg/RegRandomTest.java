package cloud.xcan.jmock.api.support.revreg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RegRandomTest {

  @Test
  void random_matchesPattern() {
    String s = RegRandom.random("[A]{3}");
    assertNotNull(s);
    assertEquals(3, s.length());
  }

  @Test
  void random_cachesGenerex() {
    assertNotNull(RegRandom.random("\\d"));
    assertNotNull(RegRandom.random("\\d"));
  }
}
