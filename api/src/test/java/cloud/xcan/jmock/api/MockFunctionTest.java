package cloud.xcan.jmock.api;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class MockFunctionTest {

  @Test
  void functionalInterface_lambda() {
    MockFunction f = () -> 42;
    assertEquals(42, f.mock());
  }

  @Test
  void abstractMockFunction_subclass() {
    AbstractMockFunction fn = new AbstractMockFunction() {
      @Override
      public Object mock() {
        return "ok";
      }
    };
    assertEquals("ok", fn.mock());
  }
}
