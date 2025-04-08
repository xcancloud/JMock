package cloud.xcan.jmock.core.function.id.mincid;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.id.MIncId;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MIncIdMockTest {

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("IncId", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MIncId mock = (MIncId) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long longVal = mock.mock();
      System.out.println(longVal);
      Assertions.assertEquals(i + 1, longVal.intValue());
    }
  }

  @Test
  public void case2_initStepTest() throws Exception {
    FunctionToken token = new FunctionToken("IncId", new String[]{"2", "5"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MIncId mock = (MIncId) parser.parse(token);
    for (int i = 2; i < 50; i += 5) {
      Long longVal = mock.mock();
      System.out.println(longVal);
      Assertions.assertEquals(i, longVal.intValue());
    }
  }

  @Test
  public void case3_uniquenessTest() throws Exception {
    FunctionToken token = new FunctionToken("IncId", new String[]{"2", "5"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MIncId mock = (MIncId) parser.parse(token);
    Map<Long, Long> ids = new ConcurrentHashMap<>();
    int threads = 10, bach = 10000;
    CountDownLatch latch = new CountDownLatch(threads);
    for (int i = 0; i < threads; i++) {
      new Thread(() -> {
        for (int j = 0; j < bach; j++) {
          Long value = mock.mock();
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
