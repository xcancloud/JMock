package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FILENAME_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FILENAME_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_FILENAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5008)
public class MFileName extends AbstractMockFunction {

  public static final List<String> FILE_PREFIXES = Arrays.asList(
      "report", "document", "image", "data", "backup", "config", "settings",
      "log", "archive", "temp", "file", "project", "presentation", "budget"
  );

  public static final List<String> FILE_SUFFIXES = Arrays.asList(
      "final", "draft", "v2", "backup", "old", "new", "2023", "2024", "jan", "feb"
  );

  public static final List<String> FILE_EXTENSIONS = Arrays.asList(
      "txt", "pdf", "docx", "xlsx", "pptx", "jpg", "png", "gif", "mp4", "mp3",
      "zip", "rar", "exe", "js", "java", "py", "html", "css", "json", "xml"
  );

  @JMockConstructor(descI18nKey = DOC_FILENAME_C1,
      example = "@FileName()",
      exampleValues = {"presentation_jan.xml", "report.pptx"})
  public MFileName() {
  }

  @Override
  public String mock() {
    return generateRandomFileName();
  }

  public static String generateRandomFileName() {
    String prefix = FILE_PREFIXES.get(random.nextInt(FILE_PREFIXES.size()));
    String suffix = "";
    String extension = FILE_EXTENSIONS.get(random.nextInt(FILE_EXTENSIONS.size()));

    // 50% chance to add a suffix
    if (random.nextBoolean()) {
      suffix = "_" + FILE_SUFFIXES.get(random.nextInt(FILE_SUFFIXES.size()));
    }

    // 30% chance to add a number
    if (random.nextDouble() < 0.3) {
      int num = random.nextInt(10) + 1;
      return prefix + num + suffix + "." + extension;
    } else {
      return prefix + suffix + "." + extension;
    }
  }
}
