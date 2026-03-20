package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FILE_EXTENSIONS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FILE_PREFIXES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FILE_SUFFIXES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FILENAME_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FILENAME_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_FILENAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5008)
public class MFileName extends AbstractMockFunction {

  final String[] filePrefixes;
  final String[] fileSuffixes;
  final String[] fileExtensions;

  @JMockConstructor(descI18nKey = DOC_FILENAME_C1,
      example = "@FileName()",
      exampleValues = {"presentation_jan.xml", "report.pptx"})
  public MFileName() {
    this.filePrefixes = getString(DATA_FILE_PREFIXES).split("\\|");
    this.fileSuffixes = getString(DATA_FILE_SUFFIXES).split("\\|");
    this.fileExtensions = getString(DATA_FILE_EXTENSIONS).split("\\|");
  }

  @Override
  public String mock() {
    return generateFileName(filePrefixes, fileSuffixes, fileExtensions);
  }

  static String generateFileName(String[] prefixes, String[] suffixes, String[] extensions) {
    String prefix = prefixes[JMockRandom.nextInt(prefixes.length)];
    String suffix = "";
    String extension = extensions[JMockRandom.nextInt(extensions.length)];

    // 50% chance to add a suffix
    if (JMockRandom.nextBoolean()) {
      suffix = "_" + suffixes[JMockRandom.nextInt(suffixes.length)];
    }

    // 30% chance to add a number
    if (JMockRandom.nextDouble() < 0.3) {
      int num = JMockRandom.nextInt(10) + 1;
      return prefix + num + suffix + "." + extension;
    } else {
      return prefix + suffix + "." + extension;
    }
  }
}
