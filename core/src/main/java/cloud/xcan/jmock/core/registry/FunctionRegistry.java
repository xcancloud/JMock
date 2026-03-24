package cloud.xcan.jmock.core.registry;

import cloud.xcan.jmock.api.MockFunction;
import java.util.Collection;
import java.util.Optional;

/**
 * Registry for looking up MockFunction implementations by name or alias.
 * <p>
 * Replaces the old {@code Environment} interface with a cleaner, more focused API. Uses
 * ConcurrentHashMap internally for thread safety without synchronized methods.
 *
 * @since 2.0.0
 */
public interface FunctionRegistry {

  /**
   * Register a MockFunction class using its simple name (minus 'M' prefix).
   */
  void register(Class<? extends MockFunction> clazz);

  /**
   * Register a MockFunction class under a specific alias.
   */
  void register(String alias, Class<? extends MockFunction> clazz);

  /**
   * Unregister a MockFunction by name.
   */
  Class<? extends MockFunction> unregister(String name);

  /**
   * Look up a MockFunction class by name.
   */
  Optional<Class<? extends MockFunction>> lookup(String name);

  /**
   * Returns all registered function names.
   */
  Collection<String> registeredNames();

  /**
   * Returns all registered function classes.
   */
  Collection<Class<? extends MockFunction>> registeredClasses();

  /**
   * Reload all SPI-discovered functions.
   */
  void reload();

  /**
   * Clear all registrations.
   */
  void clear();
}
