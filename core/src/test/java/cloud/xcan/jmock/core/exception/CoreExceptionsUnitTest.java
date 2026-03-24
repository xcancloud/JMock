package cloud.xcan.jmock.core.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.ThreadLocaleHolder;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class CoreExceptionsUnitTest {

  @AfterEach
  void tearDown() {
    ThreadLocaleHolder.removeLocale();
  }

  @Test
  void tokenException_unEscape_and_getters() {
    TokenException ex = new TokenException("a\nb\tc", 2);
    assertEquals("a\\nb\\tc", ex.unEscapeString("a\nb\tc"));
    assertNotNull(ex.getName());
    assertEquals("", ex.getMessage());
  }

  @Test
  void expressionTokenException() {
    ExpressionTokenException ex = new ExpressionTokenException("n", 1, "e");
    assertEquals("", ex.getMessage());
    assertEquals("e", ex.expression);
  }

  @Test
  void functionStartException_messages() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    FunctionStartException a = FunctionStartException.of("fn", 3);
    assertNotNull(a.getMessage());
    assertEquals(JMockMessage.PARAM_START_NOT_FOUND_T, a.getMessageKey());

    FunctionStartException b = FunctionStartException.of('@', 5);
    assertNotNull(b.getMessage());
  }

  @Test
  void functionEndException_message() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    FunctionEndException ex = FunctionEndException.of("g", 2);
    assertNotNull(ex.getMessage());
  }

  @Test
  void invalidNameException_branches() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    InvalidNameException empty = InvalidNameException.of("", 1,
        JMockMessage.PARSER_FUNC_NAME_EMPTY);
    assertNotNull(empty.getMessage());
    InvalidNameException named =
        InvalidNameException.of("Bad", 2, JMockMessage.PARSER_FUNC_NAME_INVALID);
    assertNotNull(named.getMessage());
  }

  @Test
  void constructorMismatchException() {
    ThreadLocaleHolder.setLocale(Locale.ENGLISH);
    ConstructorMismatchException ex = ConstructorMismatchException.of("Fn");
    assertTrue(ex.getMessage().contains("Fn"));
    assertEquals("Fn", ex.getName());
  }
}
