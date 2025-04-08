package cloud.xcan.jmock.core.environment;


import cloud.xcan.jmock.api.MockFunction;
import java.util.Locale;
import java.util.Map;

public interface Environment {

  void reloadEnvironment();

  void clearEnvironment();

  void setLocale(Locale locale);

  Locale getLocale();

  void registerMockClass(Class<? extends MockFunction> clazz);

  void registerMockClass(Class<? extends MockFunction> clazz, String alias);

  Class<? extends MockFunction> unregisterMockClass(String key);

  Class<? extends MockFunction> mockClass(String name);

  Map<String, Class<? extends MockFunction>> mockClass();

  Map<String, Class<? extends MockFunction>> getMockClass();
}
