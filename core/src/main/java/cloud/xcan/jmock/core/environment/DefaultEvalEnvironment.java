package cloud.xcan.jmock.core.environment;


import static cloud.xcan.angus.spec.utils.ReflectionUtils.isAbstractClass;
import static cloud.xcan.jmock.api.TokenChars.FUNC_NAME_PREFIX;
import static cloud.xcan.jmock.api.i18n.ThreadLocaleHolder.removeLocale;
import static java.util.Objects.nonNull;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.MockFunction;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;
import cloud.xcan.jmock.api.i18n.ThreadLocaleHolder;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;

public class DefaultEvalEnvironment implements Environment {

  private final Map<String, Class<? extends MockFunction>> mockClass = new HashMap<>();

  private final ServiceLoader<MockFunction> mockLoader = ServiceLoader.load(MockFunction.class);

  public DefaultEvalEnvironment() {
    this(Locale.getDefault());
  }

  public DefaultEvalEnvironment(Locale locale) {
    loadServiceMockRegister();
    loadServiceMessageRegister();
    ThreadLocaleHolder.setLocale(locale);
  }

  @Override
  public void registerMockClass(Class<? extends MockFunction> clazz) {
    Objects.requireNonNull(clazz);
    if (!MockFunction.class.isAssignableFrom(clazz)) {
      throw new IllegalArgumentException(
          clazz + " is not an implementation of MockFunction interface");
    }
    registerMockClass(clazz, clazz.getSimpleName());
  }

  @Override
  public void registerMockClass(Class<? extends MockFunction> clazz, String alias) {
    // Is the class abstract? (This includes interfaces.)
    if (isAbstractClass(clazz)) {
      return;
    }
    if (AbstractMockFunction.class.isAssignableFrom(clazz)) {
      Objects.requireNonNull(clazz);
      Objects.requireNonNull(alias);
      if (!MockFunction.class.isAssignableFrom(clazz)) {
        throw new IllegalArgumentException(
            clazz + " is not an implementation of MockFunction interface");
      }
      mockClass.put(alias, clazz);
    }
  }

  @Override
  public Class<? extends MockFunction> unregisterMockClass(String key) {
    return mockClass.remove(key);
  }

  @Override
  public Class<? extends MockFunction> mockClass(String name) {
    Class<? extends MockFunction> clz = mockClass.get(FUNC_NAME_PREFIX + name);
    return nonNull(clz) ? clz : mockClass.get(name);
  }

  @Override
  public Map<String, Class<? extends MockFunction>> mockClass() {
    return Collections.unmodifiableMap(mockClass);
  }

  @Override
  public void reloadEnvironment() {
    mockLoader.reload();
    loadServiceMockRegister();
    loadServiceMessageRegister();
  }

  private void loadServiceMockRegister() {
    ServiceLoader<MockFunction> loader = ServiceLoader.load(MockFunction.class);
    loader.forEach(mock -> {
      registerMockClass(mock.getClass(), mock.getClass().getSimpleName());
    });
  }

  private void loadServiceMessageRegister() {
    ServiceLoader<RegisterDocMessage> loader = ServiceLoader.load(RegisterDocMessage.class);
    loader.forEach(RegisterDocMessage::register);
  }

  @Override
  public void clearEnvironment() {
    removeLocale();
  }

  @Override
  public void setLocale(Locale locale) {
    ThreadLocaleHolder.setLocale(locale);
  }

  @Override
  public Locale getLocale() {
    return ThreadLocaleHolder.getLocale();
  }

  @Override
  public Map<String, Class<? extends MockFunction>> getMockClass() {
    return mockClass;
  }
}

