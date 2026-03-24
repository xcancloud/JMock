package cloud.xcan.jmock.plugin.array;

import cloud.xcan.jmock.core.engine.MockEngine;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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

  /**
   * Complex-structure demo: double-nested {@code @Repeat} (matrix-shaped {@link List}),
   * then a long template mixing nested arrays, {@code Sample}, {@code Sequence}, and scalars.
   * Output is printed to {@code System.out} for manual inspection in the IDE test runner.
   */
  @Test
  public void complexStructuredData_example_printsToConsole() {
    String nestedRepeatExpr = "@Repeat(@Repeat(@Integer(10,99),3),2)";
    Object matrix = engine.evaluate(nestedRepeatExpr);
    String matrixRendered = engine.render(nestedRepeatExpr);

    System.out.println("========== 1) 双层 Repeat：evaluate() 原始结构 ==========");
    System.out.println("expression : " + nestedRepeatExpr);
    System.out.println("raw object : " + matrix);
    System.out.println("raw class  : " + matrix.getClass().getName());
    if (matrix instanceof List<?> outer) {
      System.out.println("outer size : " + outer.size());
      for (int i = 0; i < outer.size(); i++) {
        Object row = outer.get(i);
        System.out.println(
            "  row[" + i + "] " + row + " → " + row.getClass().getSimpleName());
      }
    }
    System.out.println("render     : " + matrixRendered);

    String template =
        "ctx=nested-demo | matrix="
            + nestedRepeatExpr
            + " | tags=@Repeat(@OneOf(red|green|blue),4)"
            + " | sample=@Sample(north|south|east|west,2)"
            + " | seq=@Sequence(1,1,5)"
            + " | id=@Integer(10000,99999)";
    String fullRendered = engine.render(template);

    System.out.println("========== 2) 混合模板：render 整段（含多种数组与标量）==========");
    System.out.println(fullRendered);
    System.out.println("========== 3) 结构摘要（按 '|' 分段）==========");
    for (String segment : fullRendered.split("\\|")) {
      System.out.println("  • " + segment.trim());
    }

    Assertions.assertNotNull(matrix);
    Assertions.assertTrue(matrix instanceof List, "matrix should be List");
    @SuppressWarnings("unchecked")
    List<Object> rows = (List<Object>) matrix;
    Assertions.assertEquals(2, rows.size());
    for (Object row : rows) {
      Assertions.assertTrue(row instanceof List);
      Assertions.assertEquals(3, ((List<?>) row).size());
    }
    Assertions.assertTrue(matrixRendered.startsWith("[[") && matrixRendered.endsWith("]]"),
        "nested lists should render as [[..],[..]]: " + matrixRendered);

    Assertions.assertTrue(fullRendered.startsWith("ctx=nested-demo | matrix="));
    Assertions.assertTrue(fullRendered.contains("| tags=["));
    Assertions.assertTrue(fullRendered.contains("| sample=["));
    Assertions.assertTrue(fullRendered.contains("| seq=[1,2,3,4,5]"));
    Assertions.assertTrue(fullRendered.contains("| id="));

    List<String> tagArrays =
        java.util.regex.Pattern.compile("tags=(\\[[^]]*])")
            .matcher(fullRendered)
            .results()
            .map(m -> m.group(1))
            .collect(Collectors.toList());
    Assertions.assertEquals(1, tagArrays.size());
    String tagJson = tagArrays.get(0);
    Assertions.assertEquals(4, tagJson.split(",").length, "tags should have 4 JSON elements");
  }

  /**
   * 部门维度外层 {@link List}，每个元素对应一个用户子 {@link List}（姓氏 {@code @Lastname()}），
   * 另用 {@code @Repeat(@Department())} 生成与行对齐的部门名，控制台打印合并结构。
   */
  @Test
  public void nestedUserListsUnderDepartments_example_printsToConsole() {
    final int deptCount = 2;
    final int usersPerDept = 3;

    String usersNestedExpr =
        "@Repeat(@Repeat(@Lastname()," + usersPerDept + ")," + deptCount + ")";
    String deptNamesExpr = "@Repeat(@Department()," + deptCount + ")";

    Object usersRaw = engine.evaluate(usersNestedExpr);
    Object deptRaw = engine.evaluate(deptNamesExpr);
    String usersJson = engine.render(usersNestedExpr);
    String deptJson = engine.render(deptNamesExpr);

    System.out.println("========== 部门列表 + 每部门内嵌用户 List ==========");
    System.out.println("说明: 外层 Repeat = 部门个数；内层 Repeat = 该部门下用户列表");
    System.out.println("users 表达式: " + usersNestedExpr);
    System.out.println("dept  表达式: " + deptNamesExpr);
    System.out.println("部门名 JSON : " + deptJson);
    System.out.println("用户矩阵 JSON: " + usersJson);
    System.out.println("evaluate(users) 类型: " + usersRaw.getClass().getName());

    Assertions.assertTrue(usersRaw instanceof List, "users root should be List");
    @SuppressWarnings("unchecked")
    List<Object> userRows = (List<Object>) usersRaw;
    Assertions.assertEquals(deptCount, userRows.size(), "outer list = department slots");
    for (Object row : userRows) {
      Assertions.assertTrue(row instanceof List, "each dept slot holds a user list");
      Assertions.assertEquals(usersPerDept, ((List<?>) row).size());
    }

    Assertions.assertTrue(deptRaw instanceof List);
    @SuppressWarnings("unchecked")
    List<Object> deptNames = (List<Object>) deptRaw;
    Assertions.assertEquals(deptCount, deptNames.size());

    System.out.println("---------- 按部门索引合并（部门名 → 用户子列表）----------");
    for (int d = 0; d < deptCount; d++) {
      Object name = deptNames.get(d);
      Object users = userRows.get(d);
      System.out.println("  部门[" + d + "] " + name + " → 用户 " + users);
    }

    Assertions.assertTrue(usersJson.startsWith("[[") && usersJson.endsWith("]]"),
        "nested user lists: " + usersJson);
    Assertions.assertTrue(deptJson.startsWith("[") && deptJson.endsWith("]"));
  }
}
