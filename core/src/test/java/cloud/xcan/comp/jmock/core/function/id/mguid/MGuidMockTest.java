package cloud.xcan.comp.jmock.core.function.id.mguid;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.id.MGuid;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MGuidMockTest {

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("Guid", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGuid mock = (MGuid) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println(value);
      Assertions.assertTrue(value.contains("-"));
    }
  }

  // * Full MockConstructor: @String(length,min,max,chars,nullWeight)
  @Test
  public void case2_withoutSeparatorTrueTest() throws Exception {
    FunctionToken token = new FunctionToken("Guid", new String[]{"true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGuid mock = (MGuid) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println(value);
      Assertions.assertFalse(value.contains("-"));
    }
  }

  @Test
  public void case3_withoutSeparatorFalseTest() throws Exception {
    FunctionToken token = new FunctionToken("Guid", new String[]{"false"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGuid mock = (MGuid) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String value = mock.mock();
      System.out.println(value);
      Assertions.assertTrue(value.contains("-"));
    }
  }

  @Test
  public void case4_uniquenessTest() throws Exception {
    FunctionToken token = new FunctionToken("Guid", new String[]{"false"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGuid mock = (MGuid) parser.parse(token);
    Map<String, String> ids = new ConcurrentHashMap<>();
    int threads = 10, bach = 10000;
    CountDownLatch latch = new CountDownLatch(threads);
    for (int i = 0; i < threads; i++) {
      new Thread(() -> {
        for (int j = 0; j < bach; j++) {
          String value = mock.mock();
          if (ids.containsKey(value)) {
            System.out.println(Thread.currentThread().getName() + ": " + value);
          }
          ids.put(value, value);
        }
        latch.countDown();
      }).start();
    }
    latch.await();
    Assertions.assertEquals(ids.keySet().size(), threads * bach);
  }
}
