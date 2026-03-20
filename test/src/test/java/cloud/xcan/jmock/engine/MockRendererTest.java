package cloud.xcan.jmock.engine;

import cloud.xcan.jmock.core.engine.MockExpr;
import cloud.xcan.jmock.core.engine.MockRenderer;
import cloud.xcan.jmock.core.registry.FunctionRegistry;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Unit tests for {@link MockRenderer}.
 */
public class MockRendererTest {

  private MockRenderer renderer;
  private StubRegistry registry;

  @BeforeEach
  void setUp() {
    renderer = new MockRenderer();
    registry = new StubRegistry();
  }

  // ----- render empty / null -----

  @Test
  public void renderNull_returnsEmpty() {
    String result = renderer.render(null, registry);
    Assertions.assertEquals("", result);
  }

  @Test
  public void renderEmptyList_returnsEmpty() {
    String result = renderer.render(Collections.emptyList(), registry);
    Assertions.assertEquals("", result);
  }

  // ----- render text segment -----

  @Test
  public void renderTextSegment_returnsText() {
    List<MockExpr> nodes = List.of(new MockExpr.TextSegment("hello", 0, 5));
    Assertions.assertEquals("hello", renderer.render(nodes, registry));
  }

  // ----- render null result from function -----

  @Test
  public void renderNullFunctionResult_producesNullString() {
    registry.register("NullFn", MockEvaluatorTest.NullReturnMock.class);
    List<MockExpr> nodes = List.of(
        new MockExpr.FunctionCall("NullFn", Collections.emptyList(), 0, 7)
    );
    String result = renderer.render(nodes, registry);
    Assertions.assertEquals("null", result);
  }

  // ----- render array (list of strings) → JSON array -----

  @Test
  public void renderArrayOfStrings_producesJsonArray() {
    // ArrayExpr with a text constant inner expr — we use ConstantStrMock
    registry.register("ConstantStr", MockEvaluatorTest.ConstantStrMock.class);
    MockExpr inner = new MockExpr.FunctionCall("ConstantStr", Collections.emptyList(), 0, 13);
    MockExpr.ArrayExpr ae = new MockExpr.ArrayExpr(inner, 3, 0, 20);
    String result = renderer.render(List.of(ae), registry);
    Assertions.assertEquals("[\"CONSTANT\",\"CONSTANT\",\"CONSTANT\"]", result);
  }

  // ----- renderRaw -----

  @Test
  public void renderRaw_returnsObjectList() {
    List<MockExpr> nodes = List.of(new MockExpr.TextSegment("hello", 0, 5));
    List<Object> raw = renderer.renderRaw(nodes, registry);
    Assertions.assertEquals(1, raw.size());
    Assertions.assertEquals("hello", raw.get(0));
  }

  @Test
  public void renderRaw_nullFromFunction_propagatesNull() {
    registry.register("NullFn", MockEvaluatorTest.NullReturnMock.class);
    List<MockExpr> nodes = List.of(
        new MockExpr.FunctionCall("NullFn", Collections.emptyList(), 0, 7)
    );
    List<Object> raw = renderer.renderRaw(nodes, registry);
    Assertions.assertEquals(1, raw.size());
    Assertions.assertNull(raw.get(0));
  }

  // ----- JSON string escaping -----

  @Test
  public void renderArrayWithSpecialCharsInStrings_escapesJson() {
    // Build an array containing a string with quotes and backslash
    registry.register("QuotedStr", QuotedStrMock.class);
    MockExpr inner = new MockExpr.FunctionCall("QuotedStr", Collections.emptyList(), 0, 10);
    MockExpr.ArrayExpr ae = new MockExpr.ArrayExpr(inner, 1, 0, 15);
    String result = renderer.render(List.of(ae), registry);
    // Expected: ["say \"hi\""]
    Assertions.assertTrue(result.contains("\\\""), "Quotes should be escaped: " + result);
  }

  private static class StubRegistry implements FunctionRegistry {
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
    public java.util.Collection<String> registeredNames() { return map.keySet(); }

    @Override
    public java.util.Collection<Class<? extends cloud.xcan.jmock.api.MockFunction>> registeredClasses() {
      return map.values();
    }

    @Override
    public void reload() {}

    @Override
    public void clear() { map.clear(); }
  }

  public static class QuotedStrMock extends cloud.xcan.jmock.api.AbstractMockFunction {
    public QuotedStrMock() {}
    @Override
    public Object mock() { return "say \"hi\""; }
  }
}
