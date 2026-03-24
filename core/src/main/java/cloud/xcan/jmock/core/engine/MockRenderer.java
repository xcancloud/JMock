package cloud.xcan.jmock.core.engine;

import cloud.xcan.jmock.core.registry.FunctionRegistry;
import java.util.List;
import java.util.StringJoiner;

/**
 * Single-pass renderer that walks the AST list and builds the final output string.
 * <p>
 * Replaces the old O(n*m) offset-tracking approach in DefaultMockExpressionReplacer with an O(n)
 * StringBuilder assembly. Thread-safe when used with a thread-safe FunctionRegistry.
 *
 * @since 2.0.0
 */
public final class MockRenderer {

  private final MockEvaluator evaluator;

  public MockRenderer() {
    this.evaluator = new MockEvaluator();
  }

  public MockRenderer(MockEvaluator evaluator) {
    this.evaluator = evaluator;
  }

  /**
   * Render a list of AST nodes into a single output string.
   *
   * @param nodes    the parsed AST (from MockParser)
   * @param registry the function registry
   * @return the rendered mock text
   */
  public String render(List<MockExpr> nodes, FunctionRegistry registry) {
    if (nodes == null || nodes.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    for (MockExpr node : nodes) {
      Object result = evaluator.evaluate(node, registry);
      sb.append(formatResult(result));
    }
    return sb.toString();
  }

  /**
   * Render a list of AST nodes, returning the raw evaluated objects. Useful when the caller wants
   * typed results instead of stringified output.
   *
   * @param nodes    the parsed AST
   * @param registry the function registry
   * @return list of raw evaluation results
   */
  public List<Object> renderRaw(List<MockExpr> nodes, FunctionRegistry registry) {
    if (nodes == null || nodes.isEmpty()) {
      return List.of();
    }
    return nodes.stream()
        .map(node -> evaluator.evaluate(node, registry))
        .toList();
  }

  /**
   * Format a result value for string rendering. Lists (from ArrayExpr) are rendered as JSON-style
   * arrays.
   */
  @SuppressWarnings("unchecked")
  private String formatResult(Object result) {
    if (result == null) {
      return "null";
    }
    if (result instanceof List<?> list) {
      return formatList(list);
    }
    return result.toString();
  }

  private String formatList(List<?> list) {
    StringJoiner joiner = new StringJoiner(",", "[", "]");
    for (Object item : list) {
      if (item instanceof List<?> nested) {
        joiner.add(formatList(nested));
      } else if (item instanceof String s) {
        joiner.add("\"" + escapeJson(s) + "\"");
      } else {
        joiner.add(String.valueOf(item));
      }
    }
    return joiner.toString();
  }

  private static String escapeJson(String s) {
    StringBuilder sb = new StringBuilder(s.length());
    for (int i = 0; i < s.length(); i++) {
      char ch = s.charAt(i);
      switch (ch) {
        case '"' -> sb.append("\\\"");
        case '\\' -> sb.append("\\\\");
        case '\n' -> sb.append("\\n");
        case '\r' -> sb.append("\\r");
        case '\t' -> sb.append("\\t");
        default -> sb.append(ch);
      }
    }
    return sb.toString();
  }
}
