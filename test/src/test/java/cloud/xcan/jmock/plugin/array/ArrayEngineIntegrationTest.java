package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.core.engine.MockEngine;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Integration tests for array-plugin functions via the full {@link MockEngine}. Verifies: 1. JSON
 * array output (rendered as ["a","b","c"] or [1,2,3]) 2. Function nesting via @Repeat(@SomeFunc(),
 * N) → ArrayExpr
 */
public class ArrayEngineIntegrationTest {

  private MockEngine engine;

  @BeforeEach
  void setUp() {
    engine = MockEngine.defaultEngine();
  }

  // ----- @Repeat(literal, N) → FunctionCall (static string repeat) -----

  @Test
  public void repeat_staticString_producesJsonArrayOfStrings() {
    String result = engine.render("@Repeat(hello,3)");
    // MRepeat returns List<String> → rendered as ["hello","hello","hello"]
    Assertions.assertEquals("[\"hello\",\"hello\",\"hello\"]", result);
  }

  @Test
  public void repeat_singleArg_producesOneElementArray() {
    String result = engine.render("@Repeat(item)");
    Assertions.assertEquals("[\"item\"]", result);
  }

  // ----- @Repeat(@Func(), N) → ArrayExpr (function nesting) -----

  @Test
  public void repeat_nestedIntegerFunction_producesJsonArrayOfNumbers() {
    String result = engine.render("@Repeat(@Integer(1,9),4)");
    Assertions.assertNotNull(result);
    // Should be a JSON array like [3,7,1,5]
    Assertions.assertTrue(result.startsWith("["), "Expected JSON array: " + result);
    Assertions.assertTrue(result.endsWith("]"), "Expected JSON array: " + result);
    String inner = result.substring(1, result.length() - 1);
    String[] parts = inner.split(",");
    Assertions.assertEquals(4, parts.length, "Expected 4 items: " + result);
    for (String part : parts) {
      int val = Integer.parseInt(part.trim());
      Assertions.assertTrue(val >= 1 && val <= 9, "Out of range [1,9]: " + val);
    }
  }

  @Test
  public void repeat_nestedFunction_eachIterationIsIndependent() {
    // Over 50 batch renders using @Repeat(@Integer(1,1000), 5), verify not all items are same
    List<String> batch = engine.renderBatch("@Repeat(@Integer(1,1000000),5)", 50);
    Assertions.assertEquals(50, batch.size());
    // At least one batch result should have non-identical values
    boolean seenVariation = false;
    for (String rendered : batch) {
      String inner = rendered.substring(1, rendered.length() - 1);
      String[] parts = inner.split(",");
      Set<String> unique = Set.of(parts);
      if (unique.size() > 1) {
        seenVariation = true;
        break;
      }
    }
    Assertions.assertTrue(seenVariation,
        "Nested function should produce different values per array element");
  }

  // ----- @Sequence -----

  @Test
  public void sequence_producesJsonArrayOfNumbers() {
    String result = engine.render("@Sequence(1,1,5)");
    Assertions.assertEquals("[1,2,3,4,5]", result);
  }

  @Test
  public void sequence_defaultStartStep_fromCount() {
    String result = engine.render("@Sequence(3)");
    Assertions.assertEquals("[0,1,2]", result);
  }

  @Test
  public void sequence_withStartAndCount() {
    String result = engine.render("@Sequence(5,3)");
    Assertions.assertEquals("[5,6,7]", result);
  }

  // ----- @Shuffle -----

  @Test
  public void shuffle_producesJsonArrayWithAllItems() {
    String result = engine.render("@Shuffle(a|b|c)");
    Assertions.assertTrue(result.startsWith("["), "Expected JSON array: " + result);
    Assertions.assertTrue(result.contains("\"a\""), "Should contain 'a': " + result);
    Assertions.assertTrue(result.contains("\"b\""), "Should contain 'b': " + result);
    Assertions.assertTrue(result.contains("\"c\""), "Should contain 'c': " + result);
    // 3 items (count commas between items)
    String inner = result.substring(1, result.length() - 1);
    Assertions.assertEquals(3, inner.split(",").length);
  }

  // ----- @Sample -----

  @Test
  public void sample_producesJsonArrayOfCorrectSize() {
    String result = engine.render("@Sample(a|b|c|d|e,3)");
    Assertions.assertTrue(result.startsWith("["), "Expected JSON array: " + result);
    String inner = result.substring(1, result.length() - 1);
    Assertions.assertEquals(3, inner.split(",").length, "Expected 3 items: " + result);
  }

  // ----- @OneOf -----

  @Test
  public void oneOf_returnsOneStringValue() {
    for (int i = 0; i < 30; i++) {
      String result = engine.render("@OneOf(cat|dog|bird)");
      Assertions.assertTrue(
          "cat".equals(result) || "dog".equals(result) || "bird".equals(result),
          "Unexpected result: " + result);
    }
  }

  // ----- Array embedded in larger template -----

  @Test
  public void arrayInTemplate_renderedCorrectly() {
    String result = engine.render("tags:@Sequence(1,3)");
    Assertions.assertTrue(result.startsWith("tags:["), "Expected 'tags:[...]': " + result);
    Assertions.assertEquals("tags:[1,2,3]", result);
  }

  // ----- renderBatch with array functions -----

  @Test
  public void renderBatch_withSequence_allResultsCorrect() {
    List<String> results = engine.renderBatch("@Sequence(0,1,3)", 10);
    Assertions.assertEquals(10, results.size());
    results.forEach(r -> Assertions.assertEquals("[0,1,2]", r));
  }
}
