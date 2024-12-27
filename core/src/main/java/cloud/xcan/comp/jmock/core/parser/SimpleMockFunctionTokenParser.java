package cloud.xcan.comp.jmock.core.parser;

import static java.util.Objects.isNull;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.MockFunction;
import cloud.xcan.comp.jmock.core.environment.DefaultEvalEnvironment;
import cloud.xcan.comp.jmock.core.environment.Environment;
import cloud.xcan.comp.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;

/**
 * Parse {@link FunctionToken} into MockFunction function.
 *
 * @author xiaolong.liu
 */
public class SimpleMockFunctionTokenParser implements MockFunctionTokenParser {

  private final Environment environment;

  public SimpleMockFunctionTokenParser() {
    this(new DefaultEvalEnvironment());
  }

  public SimpleMockFunctionTokenParser(Environment environment) {
    this.environment = environment;
  }

  @Override
  public synchronized MockFunction parse(FunctionToken token) throws Exception {
    Class<? extends MockFunction> clz = environment.mockClass(token.name());
    // Non mock function
    if (isNull(clz)) {
      return null;
    }
    if (hasParams(token)) {
      return invokeConstructor(clz, token.name(), buildConstructorParams(token));
    }
    return invokeConstructor(environment.mockClass(token.name()), token.name());
  }

  public MockFunction invokeConstructor(final Class<? extends MockFunction> cls, String name,
      Object... args) throws Exception {
    try {
      return ConstructorUtils.invokeConstructor(cls, args);
    } catch (NoSuchMethodException e) {
      throw ConstructorMismatchException.of(name);
    }
  }

  public MockFunction invokeConstructor(final Class<? extends MockFunction> cls, String name,
      Object[] args, Class<?>[] parameterTypes) throws Exception {
    try {
      return ConstructorUtils.invokeConstructor(cls, args, parameterTypes);
    } catch (NoSuchMethodException e) {
      throw ConstructorMismatchException.of(name);
    }
  }

  public static Object[] buildConstructorParams(FunctionToken token) {
    Object[] constructorParams = new Object[token.getParams().size()];
    String[] params = token.getParams().values().toArray(new String[0]);
    for (int i = 0, paramsLength = params.length; i < paramsLength; i++) {
      constructorParams[i] = StringToTypeUtils.checkAndGet(params[i]);
    }
    return constructorParams;
  }

  public boolean hasParams(FunctionToken token) {
    return token != null && token.getParams() != null && !token.getParams().isEmpty();
  }
}
