package cloud.xcan.jmock.core.environment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cloud.xcan.jmock.api.MockFunction;
import cloud.xcan.jmock.api.i18n.ThreadLocaleHolder;
import cloud.xcan.jmock.core.fixtures.MEcho;
import cloud.xcan.jmock.core.fixtures.PlainMockFunction;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class DefaultEvalEnvironmentUnitTest {

  @AfterEach
  void tearDown() {
    ThreadLocaleHolder.removeLocale();
  }

  @Test
  void register_requiresNonNullClass() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    assertThrows(NullPointerException.class, () -> env.registerMockClass(null));
  }

  @Test
  @SuppressWarnings("unchecked")
  void register_nonMockFunction_throws() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    Class<? extends MockFunction> bogus =
        (Class<? extends MockFunction>) (Class<?>) String.class;
    assertThrows(IllegalArgumentException.class, () -> env.registerMockClass(bogus));
  }

  @Test
  void register_plainMockFunction_ignored() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.registerMockClass(PlainMockFunction.class);
    assertNull(env.mockClass("PlainMockFunction"));
  }

  @Test
  void register_and_lookup_byName() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.registerMockClass(MEcho.class, "MEcho");
    assertEquals(MEcho.class, env.mockClass("Echo"));
    assertEquals(MEcho.class, env.mockClass("MEcho"));
  }

  @Test
  void unregister_removesCustomAlias() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.registerMockClass(MEcho.class, "CustomEcho");
    assertEquals(MEcho.class, env.mockClass("CustomEcho"));
    assertEquals(MEcho.class, env.unregisterMockClass("CustomEcho"));
    assertNull(env.mockClass("CustomEcho"));
  }

  @Test
  void locale_roundTrip() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.setLocale(Locale.GERMAN);
    assertEquals(Locale.GERMAN, env.getLocale());
    env.clearEnvironment();
  }

  @Test
  void reloadEnvironment_doesNotThrow() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.reloadEnvironment();
  }

  @Test
  void getMockClass_isMutableInternalMap() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    assertNotNull(env.getMockClass());
  }
}
