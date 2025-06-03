package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_FILENAME_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_FILENAME_DESC;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_FILE_PATH_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_FILE_PATH_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;
import static cloud.xcan.jmock.plugin.MFileName.generateRandomFileName;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_FILE_PATH_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5009)
public class MFilePath extends AbstractMockFunction {

  public static final List<String> WINDOWS_PATHS = Arrays.asList(
      "C:\\Program Files\\", "D:\\Projects\\", "C:\\Users\\Admin\\Documents\\",
      "E:\\Backups\\", "C:\\Windows\\System32\\", "F:\\Media\\Photos\\"
  );

  public static final List<String> UNIX_PATHS = Arrays.asList(
      "/home/user/", "/usr/local/bin/", "/var/log/", "/etc/",
      "/opt/apps/", "/mnt/data/", "/tmp/", "/root/"
  );

  @JMockConstructor(descI18nKey = DOC_FILE_PATH_C1,
      example = "@FilePath()",
      exampleValues = {"F:\\Media\\Photos\\report_jan.exe", "/tmp/archive_jan.pdf"})
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
