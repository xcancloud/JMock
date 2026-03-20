package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.plugin.MOneOf;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Unit tests for {@link MOneOf}.
 */
public class MOneOfMockTest {

  @Test
  public void case1_returnsOneOfTheItems() {
    MOneOf mock = new MOneOf("apple|banana|cherry");
    Set<String> pool = Set.of("apple", "banana", "cherry");
    for (int i = 0; i < 30; i++) {
      Object result = mock.mock();
      Assertions.assertNotNull(result);
      Assertions.assertTrue(pool.contains(result.toString()), "Unexpected result: " + result);
    }
  }

  @Test
  public void case2_singleItem_alwaysReturnsThatItem() {
    MOneOf mock = new MOneOf("only");
    for (int i = 0; i < 10; i++) {
      Assertions.assertEquals("only", mock.mock());
    }
  }

  @Test
  public void case3_randomnessDistributesAcrossAllItems() {
    MOneOf mock = new MOneOf("a|b|c|d|e");
    Set<Object> seen = new HashSet<>();
    for (int i = 0; i < 200; i++) {
      seen.add(mock.mock());
    }
    // Over 200 runs should observe all 5 items
    Assertions.assertEquals(5, seen.size(), "Expected all 5 items in seen: " + seen);
  }

  @Test
  public void case4_getterReturnsItems() {
    MOneOf mock = new MOneOf("x|y|z");
    Assertions.assertEquals("x|y|z", mock.getItems());
  }

  @Test
  public void case5_twoItems_selectsEitherRandomly() {
    MOneOf mock = new MOneOf("yes|no");
    boolean sawYes = false;
    boolean sawNo = false;
    for (int i = 0; i < 100; i++) {
      String result = mock.mock().toString();
      if ("yes".equals(result)) sawYes = true;
      else if ("no".equals(result)) sawNo = true;
      if (sawYes && sawNo) break;
    }
    Assertions.assertTrue(sawYes, "Should see 'yes'");
    Assertions.assertTrue(sawNo, "Should see 'no'");
  }
}
