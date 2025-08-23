package cloud.xcan.jmock.api.support.utils;

import cloud.xcan.jmock.api.support.revreg.RegRandom;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * <p>Generates random {@link String} for {@link MString} mock.</p>
 *
 * <p>#ThreadSafe#</p>
 */
public class RandomStringUtils {

  /**
   * <p>Random object used by random method.</p>
   */
  static ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
  static char[] DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"
      .toCharArray();

  static char[] DEFAULT_NUM_CHARS = "1234567890".toCharArray();

  /**
   * <p>This constructor is public to permit tools that require a JavaBean instance
   * to operate.</p>
   */
  public RandomStringUtils() {
  }

  /**
   * <p>Creates a random mstring based on a variety of options, then it will use the {@code
   * DEFAULT_CHARS} and not allowed to produce null.</p>
   *
   * @param length the length of random mstring to create
   * @return the random mstring
   */
  public static String random(int length) {
    return random(length, DEFAULT_CHARS, 0);
  }

  /**
   * <p>Creates a random mstring based on a variety of options, then it will use the {@code
   * DEFAULT_CHARS}.</p>
   *
   * @param length     the length of random mstring to create
   * @param nullWeight null ratio, valid when allowNull=true, default 1:9, which means that 10 times
   *                   are generated and 1 time is empty
   * @return the random mstring
   */
  public static String random(int length, final double nullWeight) {
    return random(length, DEFAULT_CHARS, nullWeight);
  }

  /**
   * <p>Creates a random mstring based on a variety of options, then it will use the {@code
   * DEFAULT_CHARS}.</p>
   *
   * @param length the length of random mstring to create
   * @param chars  the set of chars to choose randoms from, must not be empty. If {@code null}, then
   *               it will use the {@code DEFAULT_CHARS}.
   * @return the random mstring
   */
  public static String random(final int length, final char[] chars) {
    return random(length, chars, 0);
  }

  /**
   * <p>Creates a random number based on a variety of options, then it will use the {@code
   * DEFAULT_NUM_CHARS}.</p>
   *
   * @param length the length of random number to create
   * @return the random number
   */
  public static String randomNum(final int length) {
    return random(length, DEFAULT_NUM_CHARS, 0);
  }

  /**
   * <p>Creates a random mstring based on a variety of options.</p>
   *
   * <p>
   * The parameter verification will be ignored here, and the verification will be completed before
   * calling.
   * </p>
   *
   * @param length     the length of random mstring to create
   * @param chars      the set of chars to choose randoms from, must not be empty. If {@code null},
   *                   then it will use the {@code DEFAULT_CHARS}.
   * @param nullWeight null ratio, valid when allowNull=true, default 1:9, which means that 10 times
   *                   are generated and 1 time is empty
   * @return the random mstring
   */
  public static String random(final int length, final char[] chars, final double nullWeight) {
    // Handle random null value
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }
    // Generate length chars
    StringBuilder res = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int randIndex = RANDOM.nextInt(chars.length);
      res.append(chars[randIndex]);
    }
    return res.toString();
  }

  public static String random(List<String> stringList, List<Integer> weightArray, int count,
      double nullWeight) {
    // Handle random null value
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }
    // Generate length chars
    int randIndex = RANDOM.nextInt(count);
    int binarySearch = binarySearch(weightArray, randIndex);
    return stringList.get(binarySearch);
  }

  private static int binarySearch(List<Integer> weightArray, int target) {
    int left = 0, right = weightArray.size() - 1;
    while (left + 1 < right) {
      // Prevent (left + right) overflow
      int mid = left + (right - left) / 2;

      Integer max = weightArray.get(mid);
      Integer min = weightArray.get(mid - 1);
      if (rangeInDefined(target, min, max)) {
        return mid;
      } else if (weightArray.get(mid) <= target) {
        left = mid;
      } else {
        right = mid;
      }
    }
    // Post-processing:
    // End Condition: left + 1 == right
    Integer leftMax = weightArray.get(left);
    Integer leftMin = 0;
    if (left != 0) {
      leftMin = weightArray.get(left - 1);
    }
    if (rangeInDefined(target, leftMin, leftMax)) {
      return left;
    }
    Integer rightMax = weightArray.get(right);
    Integer rightMin = 0;
    if (right != 0) {
      rightMin = weightArray.get(right - 1);
    }
    if (rangeInDefined(target, rightMin, rightMax)) {
      return right;
    }
    return 0;
  }

  private static boolean rangeInDefined(int current, int min, int max) {
    return current >= min && current < max;
  }

  public static String randomPassword(int min, int max, char[] chars) {
    Integer length = RandomUtils.nextInt(min, max);
    return RandomStringUtils.random(length, chars);
  }

  public static String randomRegexp(final String regexp, final double nullWeight) {
    // Handle random null value
    if (nullWeight > 0) {
      double rand = RANDOM.nextDouble();
      if (nullWeight >= rand) {
        return null;
      }
    }
    return RegRandom.random(regexp);
  }
}
