package cloud.xcan.jmock.core.engine;

import cloud.xcan.jmock.api.MockFunction;
import cloud.xcan.jmock.api.support.utils.StringToTypeUtils;
import cloud.xcan.jmock.core.registry.FunctionRegistry;
import java.lang.reflect.Constructor;
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
    return result;
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
    if (args.isEmpty()) {
      try {
        return ConstructorUtils.invokeConstructor(clz);
      } catch (Exception e) {
        Throwable cause = e.getCause() != null ? e.getCause() : e;
        System.err.println("[JMock] Failed to instantiate @" + name + " with 0 arg(s): "
            + cause.getClass().getSimpleName() + ": " + cause.getMessage());
        return null;
      }
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

    try {
      return ConstructorUtils.invokeConstructor(clz, params);
    } catch (NoSuchMethodException e) {
      // Direct match failed — the parsed arg types (e.g. Integer from "5") may not
      // match the constructor's declared types (e.g. Float). Try numeric coercion
      // across all public constructors of the same arity.
      // across all public constructors of the same arity.
      try {
        return invokeWithNumericCoercion(clz, params);
      } catch (Exception coerceEx) {
        Throwable cause = coerceEx.getCause() != null ? coerceEx.getCause() : coerceEx;
        System.err.println("[JMock] Failed to instantiate @" + name + " with "
            + args.size() + " arg(s): " + cause.getClass().getSimpleName()
            + ": " + cause.getMessage());
        return null;
      }
    } catch (Exception e) {
      // Do NOT silently fall back to the no-arg constructor when the user provided
      // arguments — that hides real configuration errors (wrong arg count, bad weight
      // format, invalid charset, etc.) behind a fake-looking default output.
      // Return null so the caller can reconstruct the original token text and the
      // user can see their expression unchanged instead of misleading random data.
      Throwable cause = e.getCause() != null ? e.getCause() : e;
      System.err.println("[JMock] Failed to instantiate @" + name + " with "
          + args.size() + " arg(s): " + cause.getClass().getSimpleName()
          + ": " + cause.getMessage());
      return null;
    }
  }

  /**
   * Scan all public constructors of {@code clz} with the same arity as {@code params},
   * coerce each numeric argument to the declared parameter type, and invoke the first
   * constructor for which every argument can be coerced successfully.
   *
   * <p>This handles the common case where the expression parser yields {@code Integer}
   * for a plain numeric token such as {@code "5"} or {@code "15"}, but the target
   * constructor declares {@code Float}, {@code Double}, or {@code Long} parameters.
   */
  private MockFunction invokeWithNumericCoercion(
      Class<? extends MockFunction> clz, Object[] params) throws Exception {
    for (Constructor<?> ctor : clz.getConstructors()) {
      Class<?>[] types = ctor.getParameterTypes();
      if (types.length != params.length) {
        continue;
      }
      Object[] coerced = tryCoerce(params, types);
      if (coerced != null) {
        return (MockFunction) ctor.newInstance(coerced);
      }
    }
    throw new NoSuchMethodException(
        "No compatible constructor on " + clz.getSimpleName()
            + " for " + params.length + " coercible arg(s)");
  }

  /**
   * Attempt to coerce each element of {@code params} to the corresponding {@code targetTypes}.
   *
   * @return a new array with coerced values, or {@code null} if any element cannot be coerced.
   */
  private Object[] tryCoerce(Object[] params, Class<?>[] targetTypes) {
    Object[] result = new Object[params.length];
    for (int i = 0; i < params.length; i++) {
      Object coerced = coerceValue(params[i], targetTypes[i]);
      if (coerced == COERCE_FAIL) {
        return null;
      }
      result[i] = coerced;
    }
    return result;
  }

  /** Sentinel — cannot use null because null is a valid coerced value. */
  private static final Object COERCE_FAIL = new Object();

  private Object coerceValue(Object val, Class<?> target) {
    if (val == null) {
      // null is acceptable for any reference type; reject for primitives
      return target.isPrimitive() ? COERCE_FAIL : null;
    }
    // Already assignable (exact match or subtype)
    Class<?> boxed = boxed(target);
    if (boxed.isInstance(val)) {
      return val;
    }
    // Numeric widening / narrowing between Number subclasses and their primitives
    if (val instanceof Number n) {
      if (target == float.class  || target == Float.class)   return n.floatValue();
      if (target == double.class || target == Double.class)  return n.doubleValue();
      if (target == long.class   || target == Long.class)    return n.longValue();
      if (target == int.class    || target == Integer.class) return n.intValue();
      if (target == short.class  || target == Short.class)   return n.shortValue();
      if (target == byte.class   || target == Byte.class)    return n.byteValue();
    }
    // String target — toString any value
    if (target == String.class) {
      return val.toString();
    }
    return COERCE_FAIL;
  }

  private static Class<?> boxed(Class<?> t) {
    if (t == float.class)   return Float.class;
    if (t == double.class)  return Double.class;
    if (t == long.class)    return Long.class;
    if (t == int.class)     return Integer.class;
    if (t == short.class)   return Short.class;
    if (t == byte.class)    return Byte.class;
    if (t == boolean.class) return Boolean.class;
    if (t == char.class)    return Character.class;
    return t;
  }

  /**
   * Reconstruct the original token text from a FunctionCall (for non-mock tokens).
   */
  private String reconstructToken(MockExpr.FunctionCall fc) {
    if (fc.args().isEmpty()) {
      if (fc.explicitEmptyParentheses()) {
        return "@" + fc.name() + "()";
      }
      return "@" + fc.name();
    }
    StringBuilder sb = new StringBuilder("@").append(fc.name()).append("(");
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
    return sb.toString();
  }

  private String reconstructExpr(MockExpr expr) {
    return switch (expr) {
      case MockExpr.TextSegment ts -> ts.text();
      case MockExpr.FunctionCall fc -> reconstructToken(fc);
      case MockExpr.ArrayExpr ae ->
          "@Repeat(" + reconstructExpr(ae.itemExpr()) + "," + ae.count() + ")";
    };
  }
}
