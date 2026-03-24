package cloud.xcan.jmock.engine;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.core.engine.MockEvaluator;
import cloud.xcan.jmock.core.engine.MockExpr;
import cloud.xcan.jmock.core.registry.FunctionRegistry;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link MockEvaluator}. Uses a hand-crafted stub registry to isolate the
 * evaluator.
 */
public class MockEvaluatorTest {

  private MockEvaluator evaluator;
  private StubRegistry registry;

  @BeforeEach
  void setUp() {
    evaluator = new MockEvaluator();
    registry = new StubRegistry();
  }

  // ----- TextSegment -----

  @Test
  public void textSegment_returnsLiteralText() {
    MockExpr ts = new MockExpr.TextSegment("hello", 0, 5);
    Object result = evaluator.evaluate(ts, registry);
    Assertions.assertEquals("hello", result);
  }

  // ----- FunctionCall — unknown function -----

  @Test
  public void unknownFunction_returnsOriginalToken() {
    MockExpr.FunctionCall fc = new MockExpr.FunctionCall("Unknown",
        Collections.emptyList(), false, 0, 9);
    Object result = evaluator.evaluate(fc, registry);
    // reconstructed token: @Unknown
    Assertions.assertEquals("@Unknown", result.toString());
  }

  // ----- FunctionCall — known function, no args -----

  @Test
  public void knownFunction_noArgs_returnsMockResult() {
    registry.register("ConstantStr", ConstantStrMock.class);
    MockExpr.FunctionCall fc = new MockExpr.FunctionCall("ConstantStr",
        Collections.emptyList(), false, 0, 14);
    Object result = evaluator.evaluate(fc, registry);
    Assertions.assertEquals("CONSTANT", result);
  }

  // ----- FunctionCall — known function returning null -----

  @Test
  public void knownFunction_returningNull_returnsNullNotString() {
    registry.register("NullReturn", NullReturnMock.class);
    MockExpr.FunctionCall fc = new MockExpr.FunctionCall("NullReturn",
        Collections.emptyList(), false, 0, 11);
    Object result = evaluator.evaluate(fc, registry);
    Assertions.assertNull(result, "Evaluator must return actual null, not the String \"null\"");
  }

  // ----- FunctionCall — with literal args -----

  @Test
  public void knownFunction_withLiteralArgs_passesArgs() {
    registry.register("EchoFirst", EchoFirstArgMock.class);
    List<MockExpr.Argument> args = List.of(
        MockExpr.Argument.literal("0", "hello"),
        MockExpr.Argument.literal("1", "world")
    );
    MockExpr.FunctionCall fc = new MockExpr.FunctionCall("EchoFirst", args, false, 0, 20);
    Object result = evaluator.evaluate(fc, registry);
    Assertions.assertEquals("hello", result);
  }

  // ----- ArrayExpr -----

  @Test
  public void arrayExpr_evaluatesItemNTimes() {
    registry.register("Counter", CounterMock.class);
    MockExpr.FunctionCall inner = new MockExpr.FunctionCall("Counter",
        Collections.emptyList(), false, 0, 9);
    CounterMock.COUNTER.set(0);
    MockExpr.ArrayExpr ae = new MockExpr.ArrayExpr(inner, 3, 0, 20);
    Object result = evaluator.evaluate(ae, registry);
    List<?> list = (List<?>) result;
    Assertions.assertEquals(3, list.size());
    // Each call should increment counter, so values should be 1, 2, 3
    for (int i = 0; i < 3; i++) {
      Assertions.assertEquals(i + 1, ((Number) list.get(i)).intValue());
    }
  }

  // ----- Stub registry -----

  static class StubRegistry implements FunctionRegistry {

    private final java.util.Map<String, Class<? extends cloud.xcan.jmock.api.MockFunction>> map =
        new java.util.HashMap<>();

    @Override
    public void register(Class<? extends cloud.xcan.jmock.api.MockFunction> clazz) {
      map.put(clazz.getSimpleName(), clazz);
    }

    @Override
    public void register(String alias, Class<? extends cloud.xcan.jmock.api.MockFunction> clazz) {
      map.put(alias, clazz);
    }

    @Override
    public Class<? extends cloud.xcan.jmock.api.MockFunction> unregister(String name) {
      return map.remove(name);
    }

    @Override
    public Optional<Class<? extends cloud.xcan.jmock.api.MockFunction>> lookup(String name) {
      return Optional.ofNullable(map.get(name));
    }

    @Override
    public java.util.Collection<String> registeredNames() {
      return map.keySet();
    }

    @Override
    public java.util.Collection<Class<? extends cloud.xcan.jmock.api.MockFunction>> registeredClasses() {
      return map.values();
    }

    @Override
    public void reload() {
    }

    @Override
    public void clear() {
      map.clear();
    }
  }

  // ----- Mock function stubs -----

  public static class ConstantStrMock extends AbstractMockFunction {

    public ConstantStrMock() {
    }

    @Override
    public Object mock() {
      return "CONSTANT";
    }
  }

  public static class NullReturnMock extends AbstractMockFunction {

    public NullReturnMock() {
    }

    @Override
    public Object mock() {
      return null;
    }
  }

  public static class EchoFirstArgMock extends AbstractMockFunction {

    private final String first;

    public EchoFirstArgMock(String first, String second) {
      this.first = first;
    }

    @Override
    public Object mock() {
      return first;
    }
  }

  public static class CounterMock extends AbstractMockFunction {

    static final AtomicInteger COUNTER = new AtomicInteger(0);

    public CounterMock() {
    }

    @Override
    public Object mock() {
      return COUNTER.incrementAndGet();
    }
  }
}
