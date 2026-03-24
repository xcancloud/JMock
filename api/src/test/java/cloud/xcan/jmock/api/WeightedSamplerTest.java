package cloud.xcan.jmock.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class WeightedSamplerTest {

  @Test
  void of_nullOrEmpty_yieldsZeroNullProbability() {
    assertEquals(0.0, WeightedSampler.of((String) null).getNullProbability());
    assertEquals(0.0, WeightedSampler.of("").getNullProbability());
  }

  @Test
  void of_validRatio() {
    WeightedSampler s = WeightedSampler.of("1:9");
    assertEquals(0.1, s.getNullProbability(), 1e-9);
  }

  @Test
  void of_trimsParts() {
    WeightedSampler s = WeightedSampler.of(" 2 : 8 ");
    assertEquals(0.2, s.getNullProbability(), 1e-9);
  }

  @Test
  void of_invalidFormat_throws() {
    assertThrows(IllegalArgumentException.class, () -> WeightedSampler.of("1"));
    assertThrows(IllegalArgumentException.class, () -> WeightedSampler.of("1:2:3"));
  }

  @Test
  void of_nonPositiveTotal_throws() {
    assertThrows(IllegalArgumentException.class, () -> WeightedSampler.of("0:0"));
    assertThrows(IllegalArgumentException.class, () -> WeightedSampler.of("-1:1"));
  }

  @Test
  void of_nonNumericParts_throwNumberFormat() {
    assertThrows(NumberFormatException.class, () -> WeightedSampler.of("x:1"));
  }

  @Test
  void of_doubleOverload() {
    assertEquals(0.25, WeightedSampler.of(0.25).getNullProbability());
  }

  @Test
  void shouldBeNull_zeroProbability_neverNull() {
    WeightedSampler s = WeightedSampler.of(0.0);
    for (int i = 0; i < 50; i++) {
      assertFalse(s.shouldBeNull());
    }
  }

  @Test
  void shouldBeNull_certainNull() {
    WeightedSampler s = WeightedSampler.of(1.0);
    for (int i = 0; i < 20; i++) {
      assertTrue(s.shouldBeNull());
    }
  }
}
