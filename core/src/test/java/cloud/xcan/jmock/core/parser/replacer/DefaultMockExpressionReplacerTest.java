package cloud.xcan.jmock.core.parser.replacer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.FunctionStartException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Core tests load {@code MIncId} via {@code xcan-jmock.id-plugin} (test scope) for
 * {@link #replaceOffsetTest_uniquenessTest}.
 */
public class DefaultMockExpressionReplacerTest {

  @Test
  public void replaceOffsetTest() throws Exception {
    try {
      new DefaultMockExpressionReplacer().replace("aa @String(10)");
    } catch (Exception e) {
      assertEquals(FunctionStartException.class, e.getClass());
    }

    String content = "@String(10) bb";
    String result = new DefaultMockExpressionReplacer().replace(content);
    Assertions.assertTrue(result.endsWith(" bb"), "got: " + result);
    Assertions.assertTrue(result.length() >= 13 && result.length() <= 16,
        "unexpected length: " + result.length());
  }

  @Test
  public void replaceOffsetTest_uniquenessTest() throws Exception {
    DefaultMockExpressionReplacer replacer = new DefaultMockExpressionReplacer();
    FunctionToken token = new FunctionToken("IncId", new String[]{});
    Map<String, String> ids = new ConcurrentHashMap<>();
    int threads = 10, bach = 10000;
    CountDownLatch latch = new CountDownLatch(threads);
    for (int i = 0; i < threads; i++) {
      new Thread(() -> {
        for (int j = 0; j < bach; j++) {
          try {
            String value = replacer.replace("@IncId()", List.of(token));
            ids.put(value, value);
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        }
        latch.countDown();
      }).start();
    }
    latch.await();
    Assertions.assertEquals(threads * bach, ids.size(), "each replacement should be unique");
  }


  /**
   * Note: Non mock functions do not replace.
   */
  @Test
  public void nonMockFunctionTest() {
    String result = null;
    try {
      result = new DefaultMockTextReplacer().replace("aa @GetVariable(v1)");
    } catch (Exception e) {
      Assertions.assertEquals(
          "Function expression must start with identifier(@) , error position 1",
          e.getMessage());
    }

    Assertions.assertEquals("aa @GetVariable(v1)", result);
  }

}
