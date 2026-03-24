package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.plugin.MRepeat;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MRepeat}.
 */
public class MRepeatMockTest {

  @Test
  public void case1_staticStringRepeated() {
    MRepeat mock = new MRepeat("hello", 3);
    Object result = mock.mock();
    Assertions.assertInstanceOf(List.class, result);
    List<?> list = (List<?>) result;
    Assertions.assertEquals(3, list.size());
    list.forEach(item -> Assertions.assertEquals("hello", item));
  }

  @Test
  public void case2_singleRepeatByDefault() {
    MRepeat mock = new MRepeat("world");
    List<?> list = (List<?>) mock.mock();
    Assertions.assertEquals(1, list.size());
    Assertions.assertEquals("world", list.get(0));
  }

  @Test
  public void case3_zeroCount_returnsEmptyList() {
    MRepeat mock = new MRepeat("x", 0);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  public void case4_negativeCountClampsToZero() {
    MRepeat mock = new MRepeat("x", -5);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertTrue(list.isEmpty(), "Negative count should produce empty list");
  }

  @Test
  public void case5_largeCount() {
    MRepeat mock = new MRepeat("item", 100);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertEquals(100, list.size());
  }

  @Test
  public void case6_gettersReturnCorrectValues() {
    MRepeat mock = new MRepeat("val", 4);
    Assertions.assertEquals("val", mock.getValue());
    Assertions.assertEquals(4, mock.getCount());
  }
}
