package cloud.xcan.jmock.core.testsupport;

import cloud.xcan.jmock.api.MockFunction;
import cloud.xcan.jmock.core.registry.FunctionRegistry;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Minimal registry for unit tests (no SPI).
 */
public class MutableFunctionRegistry implements FunctionRegistry {

  private final ConcurrentHashMap<String, Class<? extends MockFunction>> map =
      new ConcurrentHashMap<>();

  @Override
  public void register(Class<? extends MockFunction> clazz) {
    if (clazz != null) {
      map.put(clazz.getSimpleName(), clazz);
    }
  }

  @Override
  public void register(String alias, Class<? extends MockFunction> clazz) {
    if (alias != null && clazz != null) {
      map.put(alias, clazz);
    }
  }

  @Override
  public Class<? extends MockFunction> unregister(String name) {
    return map.remove(name);
  }

  @Override
  public Optional<Class<? extends MockFunction>> lookup(String name) {
    Class<? extends MockFunction> clz = map.get("M" + name);
    if (clz != null) {
      return Optional.of(clz);
    }
    clz = map.get(name);
    return Optional.ofNullable(clz);
  }

  @Override
  public Collection<String> registeredNames() {
    return Collections.unmodifiableCollection(map.keySet());
  }

  @Override
  public Collection<Class<? extends MockFunction>> registeredClasses() {
    return Collections.unmodifiableCollection(map.values());
  }

  @Override
  public void reload() {
    map.clear();
  }

  @Override
  public void clear() {
    map.clear();
  }
}
