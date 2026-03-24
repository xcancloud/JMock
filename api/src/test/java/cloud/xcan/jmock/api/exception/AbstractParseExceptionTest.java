package cloud.xcan.jmock.api.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AbstractParseExceptionTest {

  @Test
  void positionConstructors() {
    AbstractParseException ex = new AbstractParseException(12) {
      @Override
      public String getMessage() {
        return "m";
      }
    };
    assertEquals(12, ex.getPosition());

    AbstractParseException def = new AbstractParseException() {
      @Override
      public String getMessage() {
        return "x";
      }
    };
    assertEquals(-1, def.getPosition());
  }
}
