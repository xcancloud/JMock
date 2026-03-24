package cloud.xcan.jmock.core.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.core.fixtures.MEcho;
import cloud.xcan.jmock.core.fixtures.MStrict;
import cloud.xcan.jmock.core.testsupport.MutableFunctionRegistry;
import java.util.List;
import org.junit.jupiter.api.Test;

class MockEvaluatorUnitTest {

  @Test
  void textSegment_returnsText() {
    MockEvaluator ev = new MockEvaluator();
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    MockExpr.TextSegment ts = new MockExpr.TextSegment("ab", 0, 2);
    assertEquals("ab", ev.evaluate(ts, reg));
  }

  @Test
  void functionCall_strictConstructor_success() {
    MockEvaluator ev = new MockEvaluator();
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MStrict.class);
    MockExpr.FunctionCall fc =
        new MockExpr.FunctionCall("Strict", List.of(
            MockExpr.Argument.literal("0", "2"),
            MockExpr.Argument.literal("1", "3")), false, 0, 10);
    assertEquals(5, ev.evaluate(fc, reg));
  }

  @Test
  void functionCall_constructorFallback_reconstructsToken() {
    MockEvaluator ev = new MockEvaluator();
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MStrict.class);
    MockExpr.FunctionCall fc =
        new MockExpr.FunctionCall("Strict", List.of(
            MockExpr.Argument.literal("0", "bad"),
            MockExpr.Argument.literal("1", "1")), false, 0, 10);
    Object out = ev.evaluate(fc, reg);
    assertTrue(out instanceof String);
    assertTrue(out.toString().startsWith("@Strict("));
  }

  @Test
  void arrayExpr_evaluatesRepeatedly() {
    MockEvaluator ev = new MockEvaluator();
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MEcho.class);
    MockExpr.FunctionCall inner = new MockExpr.FunctionCall("Echo", List.of(
        MockExpr.Argument.literal("0", "z")), false, 0, 10);
    MockExpr.ArrayExpr arr = new MockExpr.ArrayExpr(inner, 2, 0, 20);
    @SuppressWarnings("unchecked")
    List<Object> list = (List<Object>) ev.evaluate(arr, reg);
    assertEquals(2, list.size());
    assertEquals("z", list.get(0));
    assertEquals("z", list.get(1));
  }
}
