package cloud.xcan.jmock.api;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class JMockRandomTest {

  @Test
  void current_returnsThreadLocalRandom() {
    assertTrue(JMockRandom.current() instanceof ThreadLocalRandom);
  }

  @Test
  void secure_returnsSameSingleton() {
    SecureRandom a = JMockRandom.secure();
    SecureRandom b = JMockRandom.secure();
    assertNotNull(a);
    assertTrue(a == b);
  }

  @Test
  void secure_initializationIsThreadSafe() {
    assertDoesNotThrow(() -> {
      Thread[] threads = new Thread[4];
      for (int i = 0; i < threads.length; i++) {
        threads[i] = new Thread(() -> assertNotNull(JMockRandom.secure()));
      }
      for (Thread t : threads) {
        t.start();
      }
      for (Thread t : threads) {
        t.join();
      }
    });
  }

  @RepeatedTest(30)
  void nextInt_boundedInRange() {
    int v = JMockRandom.nextInt(2, 10);
    assertTrue(v >= 2 && v < 10);
  }

  @RepeatedTest(30)
  void nextInt_upperBound() {
    int v = JMockRandom.nextInt(5);
    assertTrue(v >= 0 && v < 5);
  }

  @RepeatedTest(30)
  void nextLong_boundedInRange() {
    long v = JMockRandom.nextLong(100L, 200L);
    assertTrue(v >= 100L && v < 200L);
  }

  @RepeatedTest(20)
  void nextDouble_unitInterval() {
    double v = JMockRandom.nextDouble();
    assertTrue(v >= 0.0 && v < 1.0);
  }

  @RepeatedTest(20)
  void nextDouble_range() {
    double v = JMockRandom.nextDouble(1.0, 3.0);
    assertTrue(v >= 1.0 && v < 3.0);
  }

  @RepeatedTest(20)
  void nextBoolean_returnsBoolean() {
    JMockRandom.nextBoolean();
  }
}
