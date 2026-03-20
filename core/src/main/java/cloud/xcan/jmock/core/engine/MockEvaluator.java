package cloud.xcan.jmock.core.engine;

import cloud.xcan.jmock.api.MockFunction;
import cloud.xcan.jmock.api.support.utils.StringToTypeUtils;
import cloud.xcan.jmock.core.registry.FunctionRegistry;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang3.reflect.ConstructorUtils;

/**
 * Evaluator: resolves and executes MockExpr AST nodes against a FunctionRegistry.
 * <p>
 * Stateless — each call to {@link #evaluate(MockExpr, FunctionRegistry)} is independent.
 * Thread-safe when used with a thread-safe FunctionRegistry.
 *
 * @since 2.0.0
 */
public final class MockEvaluator {

  /**
   * Evaluate a single AST node and return the result.
   *
   * @param expr     the expression node to evaluate
   * @param registry the function registry for lookups
   * @return the mock result (String for text, List for arrays)
   */
  public Object evaluate(MockExpr expr, FunctionRegistry registry) {
    return switch (expr) {
      case MockExpr.TextSegment ts -> ts.text();
      case MockExpr.FunctionCall fc -> evaluateFunction(fc, registry);
      case MockExpr.ArrayExpr ae -> evaluateArray(ae, registry);
    };
  }

  private Object evaluateFunction(MockExpr.FunctionCall fc, FunctionRegistry registry) {
    Optional<Class<? extends MockFunction>> clzOpt = registry.lookup(fc.name());
    if (clzOpt.isEmpty()) {
      // Not a registered function — return the original token text
      return reconstructToken(fc);
    }

    Class<? extends MockFunction> clz = clzOpt.get();
    MockFunction instance = instantiate(clz, fc.name(), fc.args(), registry);
    if (instance == null) {
      return reconstructToken(fc);
    }

    Object result = instance.mock();
    return result != null ? result : "null";
  }

  private Object evaluateArray(MockExpr.ArrayExpr ae, FunctionRegistry registry) {
    List<Object> results = new ArrayList<>(ae.count());
    for (int i = 0; i < ae.count(); i++) {
      results.add(evaluate(ae.itemExpr(), registry));
    }
    return results;
  }

  private MockFunction instantiate(Class<? extends MockFunction> clz, String name,
      List<MockExpr.Argument> args, FunctionRegistry registry) {
    try {
      if (args.isEmpty()) {
        return ConstructorUtils.invokeConstructor(clz);
      }

      // Resolve arguments: literal values get type-coerced, expression values get evaluated
      Object[] params = new Object[args.size()];
      for (int i = 0; i < args.size(); i++) {
        MockExpr.Argument arg = args.get(i);
        if (arg.isExpression()) {
          // Nested function: evaluate it to get a MockFunction instance for composition
          Object evaluated = evaluate(arg.exprValue(), registry);
          params[i] = evaluated;
        } else {
          params[i] = StringToTypeUtils.checkAndGet(arg.literalValue());
        }
      }

      return ConstructorUtils.invokeConstructor(clz, params);
    } catch (Exception e) {
      // Try no-arg constructor as fallback
      try {
        return ConstructorUtils.invokeConstructor(clz);
      } catch (Exception ex) {
        return null;
      }
    }
  }

  /**
   * Reconstruct the original token text from a FunctionCall (for non-mock tokens).
   */
  private String reconstructToken(MockExpr.FunctionCall fc) {
    StringBuilder sb = new StringBuilder("@").append(fc.name());
    if (!fc.args().isEmpty()) {
      sb.append("(");
      for (int i = 0; i < fc.args().size(); i++) {
        if (i > 0) {
          sb.append(",");
        }
        MockExpr.Argument arg = fc.args().get(i);
        if (arg.isExpression()) {
          sb.append(reconstructExpr(arg.exprValue()));
        } else {
          sb.append(arg.literalValue());
        }
      }
      sb.append(")");
    }
    return sb.toString();
  }

  private String reconstructExpr(MockExpr expr) {
    return switch (expr) {
      case MockExpr.TextSegment ts -> ts.text();
      case MockExpr.FunctionCall fc -> reconstructToken(fc);
      case MockExpr.ArrayExpr ae -> "@Repeat(" + reconstructExpr(ae.itemExpr()) + "," + ae.count() + ")";
    };
  }
}
