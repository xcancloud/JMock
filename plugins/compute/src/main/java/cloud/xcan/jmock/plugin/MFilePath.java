package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrowser.random;
import static cloud.xcan.jmock.plugin.MFileName.generateRandomFileName;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MFilePath extends AbstractMockFunction {

  public static final List<String> WINDOWS_PATHS = Arrays.asList(
      "C:\\Program Files\\", "D:\\Projects\\", "C:\\Users\\Admin\\Documents\\",
      "E:\\Backups\\", "C:\\Windows\\System32\\", "F:\\Media\\Photos\\"
  );

  public static final List<String> UNIX_PATHS = Arrays.asList(
      "/home/user/", "/usr/local/bin/", "/var/log/", "/etc/",
      "/opt/apps/", "/mnt/data/", "/tmp/", "/root/"
  );

  public MFilePath() {
  }

  @Override
  public String mock() {
    if (random.nextBoolean()) {
      // Windows path
      String base = WINDOWS_PATHS.get(random.nextInt(WINDOWS_PATHS.size()));
      return base + generateRandomFileName();
    } else {
      // Unix-like path
      String base = UNIX_PATHS.get(random.nextInt(UNIX_PATHS.size()));
      return base + generateRandomFileName();
    }
  }
}
