package cloud.xcan.jmock.engine;

import cloud.xcan.jmock.api.SupportedLanguage;
import cloud.xcan.jmock.core.environment.DefaultEvalEnvironment;
import cloud.xcan.jmock.core.parser.MockFunctionDocParser;
import cloud.xcan.jmock.core.parser.docs.model.MockFunction;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class ApiDocGenerate {

  /**
   * Generates JSON docs for all {@link cloud.xcan.jmock.api.MockFunction} types discovered via SPI
   * ({@code META-INF/services/cloud.xcan.jmock.api.MockFunction}).
   * <p>
   * If you run this {@code main} with only the {@code core} module on the classpath, SPI finds no
   * plugin entries and the output will be {@code []}. Use a classpath that includes all mock
   * plugins (e.g. {@code xcan-jmock.all-plugin}) or run from the reactor with dependencies.
   * <p>
   * Usage: {@code MockFunctionDocParser [outputPath] [en|zh_CN]}
   */
  public static void main(String[] args) throws IOException {
    for (SupportedLanguage lang : SupportedLanguage.values()) {
      Locale docLocale = lang.toLocale();
      DefaultEvalEnvironment environment = new DefaultEvalEnvironment(docLocale);
      MockFunctionDocParser generator =
          new MockFunctionDocParser(environment, 1, TimeUnit.HOURS);

      List<MockFunction> mockFunctions = generator.parse(lang);

      Path out = Paths.get(args.length > 0 ? args[0] : "docs/JMockFunction-" + lang + ".json")
          .toAbsolutePath()
          .normalize();
      if (out.getParent() != null) {
        Files.createDirectories(out.getParent());
      }

      ObjectMapper mapper = new ObjectMapper();
      byte[] json = mapper.writerWithDefaultPrettyPrinter().writeValueAsBytes(mockFunctions);
      Files.write(out, json);

      System.out.println("Wrote " + mockFunctions.size() + " function doc(s) -> " + out);
      if (mockFunctions.isEmpty()) {
        System.err.println(
            "WARNING: No MockFunction classes were loaded from SPI (empty classpath or core-only). "
                + "Add plugin modules / xcan-jmock.all-plugin to the runtime classpath, then re-run.");
      }
    }

  }
}
