package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.plugin.MSample;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MSample}.
 */
public class MSampleMockTest {

  @Test
  public void case1_sampledListHasCorrectSize() {
    MSample mock = new MSample("a|b|c|d|e", 3);
    List<?> result = (List<?>) mock.mock();
    Assertions.assertEquals(3, result.size());
  }

  @Test
  public void case2_sampledItemsAreUniqueSubsetOfInput() {
    MSample mock = new MSample("a|b|c|d|e", 3);
    Set<String> pool = Set.of("a", "b", "c", "d", "e");
    for (int i = 0; i < 50; i++) {
      List<?> result = (List<?>) mock.mock();
      Assertions.assertEquals(3, result.size());
      // All sampled items must be from pool
      for (Object item : result) {
        Assertions.assertTrue(pool.contains(item), "Unexpected item: " + item);
      }
      // No duplicates within one sample
      Set<Object> unique = new HashSet<>(result);
      Assertions.assertEquals(3, unique.size(), "Sample should have no duplicates");
    }
  }

  @Test
  public void case3_countExceedingPoolSize_clampedToPoolSize() {
    MSample mock = new MSample("a|b|c", 10);
    List<?> result = (List<?>) mock.mock();
    // Count clamped to 3
    Assertions.assertEquals(3, result.size());
  }

  @Test
  public void case4_zeroCount_returnsEmptyList() {
    MSample mock = new MSample("a|b|c", 0);
    List<?> result = (List<?>) mock.mock();
    Assertions.assertTrue(result.isEmpty());
  }

  @Test
  public void case5_negativeCountClampsToZero() {
    MSample mock = new MSample("a|b|c", -2);
    List<?> result = (List<?>) mock.mock();
    Assertions.assertTrue(result.isEmpty(), "Negative count should produce empty list");
  }

  @Test
  public void case6_fullSample_containsAllItems() {
    MSample mock = new MSample("x|y|z", 3);
    List<?> result = (List<?>) mock.mock();
    Set<Object> set = new HashSet<>(result);
    Assertions.assertTrue(set.contains("x"));
    Assertions.assertTrue(set.contains("y"));
    Assertions.assertTrue(set.contains("z"));
  }

  @Test
  public void case7_randomness_variesAcrossRuns() {
    MSample mock = new MSample("a|b|c|d|e|f", 3);
    boolean seenVariation = false;
    List<?> first = (List<?>) mock.mock();
    for (int i = 0; i < 50; i++) {
      List<?> next = (List<?>) mock.mock();
      if (!next.equals(first)) {
        seenVariation = true;
        break;
      }
    }
    Assertions.assertTrue(seenVariation, "Sampling should produce different results");
  }

  @Test
  public void case8_gettersReturnCorrectValues() {
    MSample mock = new MSample("p|q|r", 2);
    Assertions.assertEquals("p|q|r", mock.getItems());
    Assertions.assertEquals(2, mock.getCount());
  }
}
