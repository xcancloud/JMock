package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MOs extends AbstractMockFunction {

  public static final List<String> DESKTOP_OS = Arrays.asList(
      "Windows 11", "Windows 10", "Windows 8.1", "Windows 7",
      "macOS Ventura", "macOS Monterey", "macOS Big Sur",
      "Ubuntu 22.04", "Ubuntu 20.04", "Fedora 38", "Debian 12",
      "Arch Linux", "Linux Mint 21"
  );

  public static final List<String> SERVER_OS = Arrays.asList(
      "Windows Server 2022", "Windows Server 2019",
      "Red Hat Enterprise Linux 9", "CentOS Stream 9",
      "Ubuntu Server 22.04", "Debian 11", "SUSE Linux Enterprise 15"
  );

  public static final List<String> MOBILE_OS = Arrays.asList(
      "Android 13", "Android 12", "iOS 16", "iOS 15", "iPadOS 16"
  );

  public MOs() {
  }

  @Override
  public String mock() {
    int category = random.nextInt(100);
    if (category < 60) {
      // Desktop OS (60% probability)
      return DESKTOP_OS.get(random.nextInt(DESKTOP_OS.size()));
    } else if (category < 85) {
      // Mobile OS (25% probability)
      return MOBILE_OS.get(random.nextInt(MOBILE_OS.size()));
    } else {
      // Server OS (15% probability)
      return SERVER_OS.get(random.nextInt(SERVER_OS.size()));
    }
  }
}
