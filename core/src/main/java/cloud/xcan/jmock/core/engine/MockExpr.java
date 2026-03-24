package cloud.xcan.jmock.core.engine;

/**
 * AST node types for JMock expression parsing.
 * <p>
 * Supports text segments, function calls (with nesting), and array expressions.
 *
 * @since 2.0.0
 */
public sealed interface MockExpr {

  /**
   * A plain text segment (not a function call).
   */
  record TextSegment(String text, int start, int end) implements MockExpr {

  }

  /**
   * A function call expression, e.g., @Email() or @String(6). Arguments can themselves be MockExpr
   * (supporting nesting like @Repeat(@Email(), 3)).
   */
  record FunctionCall(String name, java.util.List<Argument> args,
                      int start, int end) implements MockExpr {

  }

  /**
   * An array expression — wraps a function call to produce multiple values. Generated when
   *
   * @Repeat(innerFunc, count) is parsed.
   */
  record ArrayExpr(MockExpr itemExpr, int count, int start, int end) implements MockExpr {

  }

  /**
   * A single argument to a function call. Value can be a literal string or a nested MockExpr (for
   * function-in-function).
   */
  record Argument(String name, String literalValue, MockExpr exprValue) {

    /**
     * Create a literal argument.
     */
    public static Argument literal(String name, String value) {
      return new Argument(name, value, null);
    }

    /**
     * Create an expression argument (nested function call).
     */
    public static Argument expr(String name, MockExpr expr) {
      return new Argument(name, null, expr);
    }

    public boolean isExpression() {
      return exprValue != null;
    }
  }
}
