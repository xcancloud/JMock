package cloud.xcan.comp.jmock.core.support.utils;

import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
public class AppVersionUtils {

  /**
   * <p> Returns a random appVersion  </p>
   *
   * @param prefixDictArray
   * @param releaseStateDictArray
   * @param buildStateDictArray
   * @return the random appVersion
   */
  public static String appVersion(String[] prefixDictArray,
      String[] releaseStateDictArray, String[] buildStateDictArray) {
    String prefix = StringUtils.EMPTY;
    String releaseState = StringUtils.EMPTY;
    String buildState = StringUtils.EMPTY;
    if (Objects.nonNull(prefixDictArray)) {
      Integer prefixIndex = RandomUtils.nextInt(0, prefixDictArray.length);
      prefix = prefixDictArray[prefixIndex] + "-";
    }
    if (Objects.nonNull(releaseStateDictArray)) {
      Integer releaseStateIndex = RandomUtils.nextInt(0, releaseStateDictArray.length);
      releaseState = "-" + releaseStateDictArray[releaseStateIndex];
    }
    if (Objects.nonNull(buildStateDictArray)) {
      Integer buildStateIndex = RandomUtils.nextInt(0, buildStateDictArray.length);
      buildState = "+" + buildStateDictArray[buildStateIndex];
    }
    String appVersion = buildAppVersion();
    return prefix + appVersion + releaseState + buildState;
  }

  public static String buildAppVersion() {
    Integer major = RandomUtils.nextInt(0, 255);
    Integer minor = RandomUtils.nextInt(0, 255);
    Integer patch = RandomUtils.nextInt(0, 255);
    return major + "." + minor + "." + patch;
  }

}
