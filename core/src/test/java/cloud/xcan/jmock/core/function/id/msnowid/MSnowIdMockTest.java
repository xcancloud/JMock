package cloud.xcan.jmock.core.function.id.msnowid;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.id.MSnowId;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MSnowIdMockTest {

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MSnowId", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSnowId mock = (MSnowId) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long value = mock.mock();
      System.out.println(value);
//      Assertions.assertEquals(i + 1, value.intValue());
    }
  }

  // * Full MockConstructor: @String(length,min,max,chars,nullWeight)
  @Test
  public void case2_withoutSeparatorTrueTest() throws Exception {
    FunctionToken token = new FunctionToken("MSnowId", new String[]{"22", "11"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSnowId mock = (MSnowId) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long value = mock.mock();
      System.out.println(value);
//      Assertions.assertFalse(str.contains("-"));
    }
  }

  @Test
  public void case3_uniquenessTest() throws Exception {
    FunctionToken token = new FunctionToken("MSnowId", new String[]{"22", "11"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MSnowId mock = (MSnowId) parser.parse(token);
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
