package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class RandomUtilsTest {

  @Test
  void nextBoolean() {
    RandomUtils.nextBoolean();
  }

  @Test
  void nextBytes() {
    byte[] b = RandomUtils.nextBytes(8);
    assertEquals(8, b.length);
    assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextBytes(-1));
  }

  @Test
  void nextInt_rangeAndEqualBounds() {
    assertEquals(5, RandomUtils.nextInt(5, 5));
    int v = RandomUtils.nextInt(0, 10);
    assertTrue(v >= 0 && v < 10);
  }

  @Test
  void nextInt_withNullWeight_alwaysNullWhenCertain() {
    assertNull(RandomUtils.nextInt(0, 5, 1.0));
  }

  @Test
  void nextInt_plain() {
    int v = RandomUtils.nextInt();
    assertTrue(v >= 0);
  }

  @Test
  void nextLong_range() {
    assertEquals(7L, RandomUtils.nextLong(7L, 7L));
    long v = RandomUtils.nextLong(0L, 100L);
    assertTrue(v >= 0L && v < 100L);
  }

  @Test
  void nextLong_nullWeight() {
    assertNull(RandomUtils.nextLong(0L, 10L, 1.0));
  }

  @Test
  void nextLong_unbounded() {
    long v = RandomUtils.nextLong();
    assertTrue(v >= 0L);
  }

  @Test
  void nextDouble_rangeAndValidation() {
    assertEquals(1.0, RandomUtils.nextDouble(1.0, 1.0));
    double v = RandomUtils.nextDouble();
    assertTrue(v >= 0.0);
    assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextDouble(5.0, 1.0));
    assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextDouble(-1.0, 2.0));
  }

  @Test
  void nextDouble_scaled() {
    Double d = RandomUtils.nextDouble(0.0, 1.0, 2);
    assertNotNull(d);
    assertNull(RandomUtils.nextDouble(0.0, 1.0, 2, 1.0));
  }

  @Test
  void nextFloat_range() {
    assertEquals(2f, RandomUtils.nextFloat(2f, 2f));
    float v = RandomUtils.nextFloat();
    assertTrue(v >= 0f);
    assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextFloat(3f, 1f));
  }

  @Test
  void nextFloat_scaled() {
    Float f = RandomUtils.nextFloat(0f, 1f, 2);
    assertNotNull(f);
    assertNull(RandomUtils.nextFloat(0f, 1f, 2, 1.0));
  }

  @RepeatedTest(5)
  void nextLong_invalidOrder_throws() {
    assertThrows(IllegalArgumentException.class, () -> RandomUtils.nextLong(10L, 5L));
  }

  @Test
  void constructorForCoverage() {
    assertNotNull(new RandomUtils());
  }
}
