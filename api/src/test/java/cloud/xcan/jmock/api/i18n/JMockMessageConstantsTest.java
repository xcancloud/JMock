package cloud.xcan.jmock.api.i18n;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Loads doc / message constant interfaces for classpath sanity.
 */
class JMockMessageConstantsTest {

  @Test
  void jMockMessage_keysNonEmpty() {
    assertFalse(JMockMessage.PARSER_TEXT_IS_EMPTY.isEmpty());
    assertFalse(JMockFuncDocMessage.DOC_PARAMETER_LOCALE.isEmpty());
  }

  @Test
  void registerDocMessage_canImplement() {
    RegisterDocMessage r = () -> {
    };
    assertTrue(r instanceof RegisterDocMessage);
  }
}
