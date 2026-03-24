package cloud.xcan.jmock.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.ThreadLocaleHolder;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ParamParseExceptionTest {

  @AfterEach
  void tearDown() {
    ThreadLocaleHolder.removeLocale();
  }

  @Test
  void getMessage_usesThreadLocaleAndBundle() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    ParamParseException ex = ParamParseException.of("jmock.parser.input.text.is.empty");
    assertEquals("Input text is empty", ex.getMessage());
    assertEquals("jmock.parser.input.text.is.empty", ex.getMessageKey());
    assertEquals(-1, ex.getPosition());
  }

  @Test
  void getMessage_withParams_usesMessageFormat() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    ParamParseException ex = ParamParseException.of("jmock.test.format", new Object[]{"world"});
    assertEquals("Hello world", ex.getMessage());
  }

  @Test
  void factoryAndThrow0() {
    assertThrows(ParamParseException.class,
        () -> ParamParseException.throw0(JMockMessage.PARAM_NOT_NULL_T));
    assertThrows(ParamParseException.class,
        () -> ParamParseException.throw0("jmock.test.format", new Object[]{"x"}));
  }

  @Test
  void missingKey_returnsEmptyMessage() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    ParamParseException ex = ParamParseException.of("no.such.key.ever", new Object[]{1});
    assertEquals("", ex.getMessage());
  }
}
