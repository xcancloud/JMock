package cloud.xcan.jmock.core.spi;

import cloud.xcan.jmock.api.MockFunction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.ServiceConfigurationError;
import java.util.function.Consumer;

/**
 * Loads {@link MockFunction} implementation classes from {@code META-INF/services} without using
 * {@link java.util.ServiceLoader#load(Class)}, which requires a public no-arg constructor on every
 * listed class.
 */
public final class MockFunctionSpiClasses {

  public static final String RESOURCE_PATH =
      "META-INF/services/" + MockFunction.class.getName();

  private MockFunctionSpiClasses() {
  }

  public static ClassLoader resolveClassLoader() {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    return cl != null ? cl : MockFunction.class.getClassLoader();
  }

  public static void forEachClass(ClassLoader cl, Consumer<Class<? extends MockFunction>> action) {
    if (cl == null) {
      cl = MockFunction.class.getClassLoader();
    }
    try {
      Enumeration<URL> urls = cl.getResources(RESOURCE_PATH);
      while (urls.hasMoreElements()) {
        URL url = urls.nextElement();
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(url.openStream(), StandardCharsets.UTF_8))) {
          String line;
          while ((line = reader.readLine()) != null) {
            line = stripLine(line);
            if (line.isEmpty()) {
              continue;
            }
            Class<?> raw;
            try {
              raw = Class.forName(line, false, cl);
            } catch (ClassNotFoundException | NoClassDefFoundError e) {
              throw new ServiceConfigurationError("MockFunction SPI: " + line, e);
            }
            if (!MockFunction.class.isAssignableFrom(raw)) {
              throw new ServiceConfigurationError(
                  line + " is not a " + MockFunction.class.getName());
            }
            @SuppressWarnings("unchecked")
            Class<? extends MockFunction> clazz = (Class<? extends MockFunction>) raw;
            action.accept(clazz);
          }
        }
      }
    } catch (IOException e) {
      throw new UncheckedIOException("Failed to load " + RESOURCE_PATH, e);
    }
  }

  static String stripLine(String line) {
    line = line.trim();
    int hash = line.indexOf('#');
    if (hash >= 0) {
      line = line.substring(0, hash).trim();
    }
    return line;
  }
}
