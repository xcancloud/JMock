package cloud.xcan.jmock.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TokenCharsTest {

  @Test
  void constants() {
    assertEquals("M", TokenChars.FUNC_NAME_PREFIX);
    assertEquals('\\', TokenChars.DEFAULT_ESCAPE_CHAR);
    assertEquals('@', TokenChars.FUNC_IDENTIFIER);
    assertEquals('(', TokenChars.FUNC_PARAM_START);
    assertEquals(')', TokenChars.FUNC_PARAM_END);
    assertEquals(',', TokenChars.FUNC_PARAM_SEPARATOR);
    assertEquals('$', TokenChars.METHOD_CALL_IDENTIFIER);
    assertEquals('{', TokenChars.METHOD_CALL_PARAM_START);
    assertEquals('}', TokenChars.METHOD_CALL_PARAM_END);
    assertEquals('.', TokenChars.METHOD_CALL_REF_SEPARATOR);
  }
}
