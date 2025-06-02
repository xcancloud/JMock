package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class MBrowser extends AbstractMockFunction {

  public static final SecureRandom random = new SecureRandom();

  public static final List<String> BROWSERS = Arrays.asList(
      "Chrome", "Firefox", "Safari", "Edge", "Opera", "Brave", "Vivaldi", "Chromium"
  );

  public static final List<String> BROWSER_VERSIONS = Arrays.asList(
      "115", "116", "117", "118", "119", "120",
      "100", "101", "102", "103", "104", "105"
  );

  public MBrowser() {
  }

  @Override
  public String mock() {
    String browser = BROWSERS.get(random.nextInt(BROWSERS.size()));
    String version = BROWSER_VERSIONS.get(random.nextInt(BROWSER_VERSIONS.size()));

    // 80% chance to include version number
    if (random.nextDouble() < 0.8) {
      return browser + " " + version;
    } else {
      return browser;
    }
  }
}
