package cloud.xcan.jmock.api.i18n;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Locale;
import org.junit.jupiter.api.Test;

class MessageResourcesTest {

  @Test
  void getString_defaultLocaleLookup() {
    assertEquals("Input text is empty",
        MessageResources.getString("jmock.parser.input.text.is.empty"));
  }

  @Test
  void getString_withLocale() {
    assertEquals("Input text is empty",
        MessageResources.getString("jmock.parser.input.text.is.empty", Locale.ENGLISH));
  }

  @Test
  void getString_missingKey_returnsEmpty() {
    assertEquals("", MessageResources.getString("__missing__", Locale.ENGLISH));
  }

  @Test
  void getString_withArguments() {
    assertEquals("Hello world",
        MessageResources.getString("jmock.test.format", new Object[]{"world"}));
    assertEquals("Hello world",
        MessageResources.getString("jmock.test.format", new Object[]{"world"}, Locale.ENGLISH));
  }
}
