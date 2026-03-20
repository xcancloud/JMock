package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FILE_EXTENSIONS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FILE_PREFIXES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FILE_SUFFIXES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_UNIX_PATHS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_WINDOWS_PATHS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FILE_PATH_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FILE_PATH_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_FILE_PATH_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5009)
public class MFilePath extends AbstractMockFunction {

  private final String[] windowsPaths;
  private final String[] unixPaths;
  private final String[] filePrefixes;
  private final String[] fileSuffixes;
  private final String[] fileExtensions;

  @JMockConstructor(descI18nKey = DOC_FILE_PATH_C1,
      example = "@FilePath()",
      exampleValues = {"F:\\Media\\Photos\\report_jan.exe", "/tmp/archive_jan.pdf"})
  public MFilePath() {
    this.windowsPaths = getString(DATA_WINDOWS_PATHS).split("\\|");
    this.unixPaths = getString(DATA_UNIX_PATHS).split("\\|");
    this.filePrefixes = getString(DATA_FILE_PREFIXES).split("\\|");
    this.fileSuffixes = getString(DATA_FILE_SUFFIXES).split("\\|");
    this.fileExtensions = getString(DATA_FILE_EXTENSIONS).split("\\|");
  }

  @Override
  public String mock() {
    String fileName = MFileName.generateFileName(filePrefixes, fileSuffixes, fileExtensions);
    if (JMockRandom.nextBoolean()) {
      return windowsPaths[JMockRandom.nextInt(windowsPaths.length)] + fileName;
    } else {
      return unixPaths[JMockRandom.nextInt(unixPaths.length)] + fileName;
    }
  }
}
