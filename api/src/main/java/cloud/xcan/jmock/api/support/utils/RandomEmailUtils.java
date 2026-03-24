package cloud.xcan.jmock.api.support.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
public class RandomEmailUtils {

  private static final String DEFAULT_EMAIL_SUFFIX = "@example.com";

  /**
   * <p> Returns a random email  </p>
   *
   * @return the random email
   */
  public static String email(int min, int max, String[] suffixArray) {
    List<String> suffixes = sanitizeSuffixes(suffixArray);
    Integer length = RandomUtils.nextInt(min, max);
    String prefix = RandomStringUtils.random(length);
    String suffix = suffixes.get(RandomUtils.nextInt(0, suffixes.size()));
    return prefix + (suffix.startsWith("@") ? "" : "@") + suffix;
  }

  private static List<String> sanitizeSuffixes(String[] suffixArray) {
    if (suffixArray == null || suffixArray.length == 0) {
      return List.of(DEFAULT_EMAIL_SUFFIX);
    }
    List<String> list = Arrays.stream(suffixArray)
        .map(s -> s == null ? "" : s.trim())
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toList());
    return list.isEmpty() ? List.of(DEFAULT_EMAIL_SUFFIX) : list;
  }

}
