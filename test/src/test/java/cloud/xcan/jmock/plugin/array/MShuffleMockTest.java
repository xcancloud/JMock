package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.plugin.MShuffle;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MShuffle}.
 */
public class MShuffleMockTest {

  @Test
  public void case1_shuffledListContainsAllOriginalItems() {
    MShuffle mock = new MShuffle("a|b|c|d");
    List<?> result = (List<?>) mock.mock();
    Assertions.assertEquals(4, result.size());
    Set<Object> set = new HashSet<>(result);
    Assertions.assertTrue(set.contains("a"));
    Assertions.assertTrue(set.contains("b"));
    Assertions.assertTrue(set.contains("c"));
    Assertions.assertTrue(set.contains("d"));
  }

  @Test
  public void case2_singleItem_returnsListWithOneItem() {
    MShuffle mock = new MShuffle("only");
    List<?> result = (List<?>) mock.mock();
    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals("only", result.get(0));
  }

  @Test
  public void case3_shuffleProducesDifferentOrderOverManyRuns() {
    MShuffle mock = new MShuffle("1|2|3|4|5");
    boolean sawDifferentOrder = false;
    String first = mock.mock().toString();
    for (int i = 0; i < 50; i++) {
      String next = mock.mock().toString();
      if (!next.equals(first)) {
        sawDifferentOrder = true;
        break;
      }
    }
    Assertions.assertTrue(sawDifferentOrder,
        "Shuffle should produce different orders over multiple calls");
  }

  @Test
  public void case4_originalItemListNotMutatedBetweenCalls() {
    MShuffle mock = new MShuffle("x|y|z");
    List<?> first = (List<?>) mock.mock();
    List<?> second = (List<?>) mock.mock();
    // Both results should have size 3
    Assertions.assertEquals(3, first.size());
    Assertions.assertEquals(3, second.size());
  }

  @Test
  public void case5_getterReturnsItems() {
    MShuffle mock = new MShuffle("p|q");
    Assertions.assertEquals("p|q", mock.getItems());
  }
}
