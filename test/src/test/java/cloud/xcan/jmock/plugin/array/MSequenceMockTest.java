package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.plugin.MSequence;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Unit tests for {@link MSequence}.
 */
public class MSequenceMockTest {

  @Test
  public void case1_defaultStartStepCount_startAt0Step1() {
    MSequence mock = new MSequence(5);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertEquals(5, list.size());
    for (int i = 0; i < 5; i++) {
      Assertions.assertEquals((long) i, list.get(i));
    }
  }

  @Test
  public void case2_withStartAndCount_step1() {
    MSequence mock = new MSequence(10, 3);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertEquals(3, list.size());
    Assertions.assertEquals(10L, list.get(0));
    Assertions.assertEquals(11L, list.get(1));
    Assertions.assertEquals(12L, list.get(2));
  }

  @Test
  public void case3_withStartStepCount() {
    MSequence mock = new MSequence(0, 2, 4);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertEquals(4, list.size());
    Assertions.assertEquals(0L, list.get(0));
    Assertions.assertEquals(2L, list.get(1));
    Assertions.assertEquals(4L, list.get(2));
    Assertions.assertEquals(6L, list.get(3));
  }

  @Test
  public void case4_negativeStep_descendingSequence() {
    MSequence mock = new MSequence(10, -1, 4);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertEquals(4, list.size());
    Assertions.assertEquals(10L, list.get(0));
    Assertions.assertEquals(9L, list.get(1));
    Assertions.assertEquals(8L, list.get(2));
    Assertions.assertEquals(7L, list.get(3));
  }

  @Test
  public void case5_zeroCount_returnsEmptyList() {
    MSequence mock = new MSequence(0);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  public void case6_negativeCountClampsToZero() {
    MSequence mock = new MSequence(1, 1, -3);
    List<?> list = (List<?>) mock.mock();
    Assertions.assertTrue(list.isEmpty());
  }

  @Test
  public void case7_gettersReturnCorrectValues() {
    MSequence mock = new MSequence(5, 2, 3);
    Assertions.assertEquals(5L, mock.getStart());
    Assertions.assertEquals(2L, mock.getStep());
    Assertions.assertEquals(3, mock.getCount());
  }
}
