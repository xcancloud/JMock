package cloud.xcan.jmock.api;

import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Shared random source for all JMock functions.
 * <p>
 * Uses {@link ThreadLocalRandom} by default for high-throughput performance.
 * Call {@link #secure()} to obtain a cryptographically strong instance when needed.
 */
public final class JMockRandom {

  private static volatile SecureRandom secureInstance;

  private JMockRandom() {
  }

  /**
   * Returns a high-performance, thread-local random generator.
   * Suitable for the vast majority of mock data generation scenarios.
   */
  public static ThreadLocalRandom current() {
    return ThreadLocalRandom.current();
  }

  /**
   * Returns a cryptographically secure random generator.
   * Use only when security-quality randomness is required (e.g., key generation).
   */
  public static SecureRandom secure() {
    if (secureInstance == null) {
      synchronized (JMockRandom.class) {
        if (secureInstance == null) {
          secureInstance = new SecureRandom();
        }
      }
    }
    return secureInstance;
  }

  /**
   * Returns a random int in [origin, bound).
   */
  public static int nextInt(int origin, int bound) {
    return current().nextInt(origin, bound);
  }

  /**
   * Returns a random int in [0, bound).
   */
  public static int nextInt(int bound) {
    return current().nextInt(bound);
  }

  /**
   * Returns a random long in [origin, bound).
   */
  public static long nextLong(long origin, long bound) {
    return current().nextLong(origin, bound);
  }

  /**
   * Returns a random double in [0.0, 1.0).
   */
  public static double nextDouble() {
    return current().nextDouble();
  }

  /**
   * Returns a random double in [origin, bound).
   */
  public static double nextDouble(double origin, double bound) {
    return current().nextDouble(origin, bound);
  }

  /**
   * Returns a random boolean.
   */
  public static boolean nextBoolean() {
    return current().nextBoolean();
  }
}
