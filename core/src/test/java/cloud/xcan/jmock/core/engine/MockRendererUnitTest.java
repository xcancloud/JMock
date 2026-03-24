package cloud.xcan.jmock.core.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.core.fixtures.MEcho;
import cloud.xcan.jmock.core.fixtures.MListJson;
import cloud.xcan.jmock.core.fixtures.MNestedList;
import cloud.xcan.jmock.core.testsupport.MutableFunctionRegistry;
import java.util.List;
import org.junit.jupiter.api.Test;

class MockRendererUnitTest {

  @Test
  void render_emptyAndNullNodes() {
    MockRenderer renderer = new MockRenderer();
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    assertEquals("", renderer.render(null, reg));
    assertEquals("", renderer.render(List.of(), reg));
  }

  @Test
  void renderRaw_parallelToRender() {
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MEcho.class);
    MockParser parser = new MockParser();
    List<MockExpr> ast = parser.parse("@Echo(a)");
    MockRenderer renderer = new MockRenderer();
    assertEquals("a", renderer.render(ast, reg));
    List<Object> raw = renderer.renderRaw(ast, reg);
    assertEquals(1, raw.size());
    assertEquals("a", raw.get(0));
  }

  @Test
  void nestedList_inJsonFormatting() {
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MListJson.class);
    MockParser parser = new MockParser();
    List<MockExpr> ast = parser.parse("@ListJson()");
    String json = new MockRenderer().render(ast, reg);
    assertTrue(json.contains("99"));
  }

  @Test
  void nestedList_innerArrayBranch() {
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MNestedList.class);
    String json = new MockRenderer().render(new MockParser().parse("@NestedList()"), reg);
    assertTrue(json.contains("inner"));
  }

  @Test
  void renderer_customEvaluator() {
    MockRenderer renderer = new MockRenderer(new MockEvaluator());
    MutableFunctionRegistry reg = new MutableFunctionRegistry();
    reg.register(MEcho.class);
    assertEquals("q", renderer.render(new MockParser().parse("@Echo(q)"), reg));
  }
}
