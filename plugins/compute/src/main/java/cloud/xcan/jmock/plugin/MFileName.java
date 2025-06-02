package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

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
