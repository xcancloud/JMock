package cloud.xcan.jmock.core.support.utils;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.Validate;

/**
 * <p>Utility library that supplements the standard {@link Random} class.</p>
 *
 * <p>Caveat: Instances of {@link Random} are not cryptographically secure.</p>
 *
 * <p>Please note that the Apache Commons project provides a component
 * dedicated to pseudo-random number generation, namely
 * <a href="https://commons.apache.org/rng">Commons RNG</a>, that may be
 * a better choice for applications with more stringent requirements (performance and/or
 * correctness).</p>
 */
public class RandomUtils {

  /**
   * Random object used by random method. This has to be not local to the random method so as to not
   * return the same value in the same millisecond.
   */
  public static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

  /**
   * <p>
   * {@code RandomUtils} instances should NOT be constructed in standard programming. Instead, the
   * class should be used as {@code RandomUtils.nextBytes(5);}.
   * </p>
   *
   * <p>
   * This constructor is public to permit tools that require a JavaBean instance to operate.
   * </p>
   */
  public RandomUtils() {
  }

  /**
   * <p>
   * Returns a random boolean value
   * </p>
   *
   * @return the random boolean
   */
  public static Boolean nextBoolean() {
    return RANDOM.nextBoolean();
  }

  /**
   * <p>
   * Creates an array of random bytes.
   * </p>
   *
   * @param count the size of the returned array
   * @return the random byte array
   * @throws IllegalArgumentException if {@code count} is negative
   */
  public static byte[] nextBytes(final int count) {
    Validate.isTrue(count >= 0, "Count cannot be negative.");

    final byte[] result = new byte[count];
    RANDOM.nextBytes(result);
    return result;
  }

  /**
   * <p>
   * Returns a random integer within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random integer
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Integer nextInt(final int startInclusive, final int endExclusive,
      final double nullWeight) {
    // Handle random null value
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }
    return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
  }

  /**
   * <p>
   * Returns a random integer within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random integer
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Integer nextInt(final int startInclusive, final int endExclusive) {
    if (startInclusive == endExclusive) {
      return startInclusive;
    }
    return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
  }

  /**
   * <p> Returns a random int within 0 - Integer.MAX_VALUE </p>
   *
   * @return the random integer
   * @see #nextInt(int, int)
   */
  public static int nextInt() {
    return nextInt(0, Integer.MAX_VALUE);
  }


  /**
   * <p>
   * Returns a random Long within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random Long
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Long nextLong(final long startInclusive, final long endExclusive,
      final double nullWeight) {
    // Handle random null value
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }
    return startInclusive + RANDOM.nextLong(endExclusive - startInclusive);
  }

  /**
   * <p>
   * Returns a random long within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned
   * @param endExclusive   the upper bound (not included)
   * @return the random long
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static long nextLong(final long startInclusive, final long endExclusive) {
    Validate.isTrue(endExclusive >= startInclusive,
        "Start value must be smaller or equal to end value.");
//    Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.");
    if (startInclusive == endExclusive) {
      return startInclusive;
    }

    return startInclusive + nextLong(endExclusive - startInclusive);
  }

  /**
   * <p> Returns a random long within 0 - Long.MAX_VALUE </p>
   *
   * @return the random long
   * @see #nextLong(long, long)
   */
  public static long nextLong() {
    return nextLong(Long.MAX_VALUE);
  }

  /**
   * Generates a {@code long} value between 0 (inclusive) and the specified value (exclusive).
   *
   * @param n Bound on the random number to be returned.  Must be positive.
   * @return a random {@code long} value between 0 (inclusive) and {@code n} (exclusive).
   */
  private static long nextLong(final long n) {
    // Extracted from o.a.c.rng.core.BaseProvider.nextLong(long)
    long bits;
    long val;
    do {
      bits = RANDOM.nextLong() >>> 1;
      val = bits % n;
    } while (bits - val + (n - 1) < 0);

    return val;
  }

  /**
   * <p>
   * Returns a random double within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random double
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static double nextDouble(final double startInclusive, final double endExclusive) {
    Validate.isTrue(endExclusive >= startInclusive,
        "Start value must be smaller or equal to end value.");
    Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.");

    if (startInclusive == endExclusive) {
      return startInclusive;
    }

    return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextDouble());
  }

  /**
   * <p> Returns a random double within 0 - Double.MAX_VALUE </p>
   *
   * @return the random double
   * @see #nextDouble(double, double)
   */
  public static double nextDouble() {
    return nextDouble(0, Double.MAX_VALUE);
  }

  /**
   * <p>
   * Returns a random double within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random double
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Double nextDouble(final double startInclusive, final double endExclusive,
      int scale) {
    return nextDouble(startInclusive, endExclusive, scale, 0);
  }

  /**
   * <p>
   * Returns a random double within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random double
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Double nextDouble(final double startInclusive, final double endExclusive, int scale,
      double nullWeight) {
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }

    double cvalue = Math.pow(10, scale);
    return (double) Math.round(
        (startInclusive + ((endExclusive - startInclusive) * RANDOM.nextDouble())) * cvalue)
        / cvalue;
  }

  /**
   * <p>
   * Returns a random float within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random float
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static float nextFloat(final float startInclusive, final float endExclusive) {
    Validate.isTrue(endExclusive >= startInclusive,
        "Start value must be smaller or equal to end value.");
    Validate.isTrue(startInclusive >= 0, "Both range values must be non-negative.");

    if (startInclusive == endExclusive) {
      return startInclusive;
    }

    return startInclusive + ((endExclusive - startInclusive) * RANDOM.nextFloat());
  }

  /**
   * <p>
   * Returns a random float within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random float
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Float nextFloat(final float startInclusive, final float endExclusive, int scale) {
    return nextFloat(startInclusive, endExclusive, scale, 0);
  }

  /**
   * <p>
   * Returns a random float within the specified range.
   * </p>
   *
   * @param startInclusive the smallest value that can be returned, must be non-negative
   * @param endExclusive   the upper bound (not included)
   * @return the random float
   * @throws IllegalArgumentException if {@code startInclusive > endExclusive} or if {@code
   *                                  startInclusive} is negative
   */
  public static Float nextFloat(final float startInclusive, final float endExclusive, int scale,
      double nullWeight) {
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }
    StringBuilder pattern = new StringBuilder("0.");
    pattern.append("0".repeat(Math.max(0, scale)));
    float val = startInclusive + ((endExclusive - startInclusive) * RANDOM.nextFloat());
    String formatVal = new DecimalFormat(pattern.toString()).format(val);
    return Float.parseFloat(formatVal);
  }

  /**
   * <p> Returns a random float within 0 - Float.MAX_VALUE </p>
   *
   * @return the random float
   * @see #nextFloat(float, float)
   */
  public static float nextFloat() {
    return nextFloat(0, Float.MAX_VALUE);
  }
}
