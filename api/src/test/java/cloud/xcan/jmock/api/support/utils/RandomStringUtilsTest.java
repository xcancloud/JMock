package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class RandomStringUtilsTest {

  @Test
  void random_length() {
    assertEquals(5, RandomStringUtils.random(5).length());
  }

  @Test
  void random_withChars() {
    char[] chars = new char[]{'a'};
    assertEquals("aaa", RandomStringUtils.random(3, chars));
  }

  @Test
  void random_nullChars_usesDefault() {
    assertEquals(4, RandomStringUtils.random(4, (char[]) null).length());
  }

  @Test
  void random_nullWeight() {
    assertNull(RandomStringUtils.random(3, 1.0));
  }

  @Test
  void randomNum() {
    assertTrue(RandomStringUtils.randomNum(6).matches("\\d{6}"));
  }

  @Test
  void randomWeightedPick() {
    List<String> items = List.of("a", "b");
    List<Integer> weights = List.of(5, 10);
    for (int i = 0; i < 30; i++) {
      String s = RandomStringUtils.random(items, weights, 10, 0);
      assertTrue("a".equals(s) || "b".equals(s));
    }
  }

  @Test
  void randomPassword() {
    String p = RandomStringUtils.randomPassword(4, 6, new char[]{'x', 'y'});
    assertTrue(p.length() >= 4 && p.length() < 6);
  }

  @Test
  void randomRegexp() {
    assertNotNull(RandomStringUtils.randomRegexp("[ab]{3}", 0));
    assertNull(RandomStringUtils.randomRegexp("[a]", 1.0));
  }

  @Test
  void constructorForCoverage() {
    assertNotNull(new RandomStringUtils());
  }
}
