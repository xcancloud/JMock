package cloud.xcan.jmock.api.support.utils;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
public class RandomEmailUtils {


  /**
   * <p> Returns a random email  </p>
   *
   * @return the random email
   */
  public static String email(int min, int max, String[] suffixArray) {
    Integer length = RandomUtils.nextInt(min, max);
    String prefix = RandomStringUtils.random(length);
    Integer dictIndex = RandomUtils.nextInt(0, suffixArray.length);
    String suffix = suffixArray[dictIndex];
    return prefix + (suffix.startsWith("@") ? "" : "@") + suffix;
  }


}
