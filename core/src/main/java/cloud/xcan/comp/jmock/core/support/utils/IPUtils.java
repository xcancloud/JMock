package cloud.xcan.comp.jmock.core.support.utils;

import static cloud.xcan.comp.jmock.core.support.utils.RandomStringUtils.random;

import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
public class IPUtils {

  static char[] IP_DEFAULT_CHARS = "abcdef1234567890"
      .toCharArray();

  static char[] URL_DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890"
      .toCharArray();

  /**
   * <p> Returns a random ipV4  </p>
   *
   * @return the random ipV4
   */
  public static String ipV4() {
    Integer first = RandomUtils.nextInt(0, 255);
    Integer second = RandomUtils.nextInt(0, 255);
    Integer third = RandomUtils.nextInt(0, 255);
    Integer fourth = RandomUtils.nextInt(0, 255);
    return first + "." + second + "." + third + "." + fourth;
  }


  /**
   * <p> Returns a random ipV6  </p>
   *
   * @return the random ipV6
   */
  public static String ipV6() {
    String first = random(4, IP_DEFAULT_CHARS);
    String second = random(4, IP_DEFAULT_CHARS);
    String third = random(4, IP_DEFAULT_CHARS);
    String fourth = random(4, IP_DEFAULT_CHARS);
    String fifth = random(4, IP_DEFAULT_CHARS);
    String sixth = random(4, IP_DEFAULT_CHARS);
    String seventh = random(4, IP_DEFAULT_CHARS);
    String eighth = random(4, IP_DEFAULT_CHARS);
    return first + "." + second + "." + third + "." + fourth + ":" + fifth + ":" + sixth + ":"
        + seventh + ":" + eighth;
  }

  /**
   * <p> Returns a random mac  </p>
   *
   * @return the random mac
   */
  public static String mac() {
    String first = random(2, IP_DEFAULT_CHARS);
    String second = random(2, IP_DEFAULT_CHARS);
    String third = random(2, IP_DEFAULT_CHARS);
    String fourth = random(2, IP_DEFAULT_CHARS);
    String fifth = random(2, IP_DEFAULT_CHARS);
    String sixth = random(2, IP_DEFAULT_CHARS);
    return first + ":" + second + ":" + third + ":" + fourth + ":" + fifth + ":" + sixth;
  }

  /**
   * <p> Returns a random url  </p>
   *
   * @return the random url
   */
  public static String randomUrl(String protocol, String domain, String[] dictArray,
      boolean allowQueryParams) {
    StringBuilder path = new StringBuilder(StringUtils.EMPTY);
    String randomUrl = protocol + "://" + domain;
    Integer level = RandomUtils.nextInt(2, 6);
    for (int i = 0; i < level; i++) {
      Integer length = RandomUtils.nextInt(3, 7);
      path.append("/").append(random(length, URL_DEFAULT_CHARS));
    }
    if (allowQueryParams) {
      Integer paramLevel = RandomUtils.nextInt(2, 6);
      paramLevel = (dictArray.length < level) ? dictArray.length : paramLevel;
      path.append("&");
      for (int i = 0; i < paramLevel; i++) {
        Integer length = RandomUtils.nextInt(3, 7);
        Integer index = RandomUtils.nextInt(0, dictArray.length);
        path.append(dictArray[index]).append("=").append(random(length, URL_DEFAULT_CHARS));
        if (i != paramLevel - 1) {
          path.append("?");
        }
      }
    }
    return randomUrl + path;
  }

  public static String randomUrl(Integer max, String protocol, String domain, String[] dictArray,
      boolean allowQueryParams) {
    StringBuilder url = new StringBuilder(protocol)
        .append("://")
        .append(domain);
    if (url.length() >= max) {
      return url.toString();
    }
    Integer level = RandomUtils.nextInt(2, 6);
    for (int i = 0; i < level; i++) {
      Integer length = RandomUtils.nextInt(3, 7);
      if (url.length() + length >= max) {
        return url.toString();
      }
      url.append("/").append(random(length, URL_DEFAULT_CHARS));
    }
    if (allowQueryParams && url.length() < max) {
      Integer paramLevel = Math.min(dictArray.length, RandomUtils.nextInt(2, 6));
      for (int i = 0; i < paramLevel; i++) {
        Integer length = RandomUtils.nextInt(3, 7);
        Integer index = RandomUtils.nextInt(0, dictArray.length);
        String paramStr = dictArray[index] + "=" + random(length, URL_DEFAULT_CHARS);
        if (url.length() + paramStr.length() >= max) {
          return url.toString();
        }
        url.append(i == 0 ? "?" : "&").append(paramStr);
      }
    }
    return url.toString();
  }
}
