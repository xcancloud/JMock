package cloud.xcan.jmock.core.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.api.SupportedLanguage;
import cloud.xcan.jmock.api.i18n.ThreadLocaleHolder;
import cloud.xcan.jmock.core.environment.DefaultEvalEnvironment;
import cloud.xcan.jmock.core.fixtures.MDocFixture;
import cloud.xcan.jmock.core.parser.docs.model.MockFunction;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MockFunctionDocParserUnitTest {

  @AfterEach
  void tearDown() {
    ThreadLocaleHolder.removeLocale();
  }

  @Test
  void parse_emptyEnvironment_returnsEmpty() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.getMockClass().clear();
    MockFunctionDocParser parser = new MockFunctionDocParser(env, 1, TimeUnit.HOURS);
    assertTrue(parser.parse(SupportedLanguage.en).isEmpty());
  }

  @Test
  void parse_annotatedFixture() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.getMockClass().clear();
    env.registerMockClass(MDocFixture.class);
    MockFunctionDocParser parser = new MockFunctionDocParser(env, 1, TimeUnit.HOURS);
    List<MockFunction> docs = parser.parse(SupportedLanguage.en);
    assertEquals(1, docs.size());
    assertTrue(docs.get(0).getName().contains("DocFixture"));
    assertFalse(docs.get(0).getConstructors().isEmpty());
  }

  @Test
  void load_cached_reload_refreshes() {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.getMockClass().clear();
    env.registerMockClass(MDocFixture.class);
    MockFunctionDocParser parser = new MockFunctionDocParser(env, 1, TimeUnit.HOURS);
    List<MockFunction> first = parser.load(SupportedLanguage.en);
    assertSame(first, parser.load(SupportedLanguage.en));
    List<MockFunction> afterReload = parser.reload(SupportedLanguage.en);
    assertEquals(first.size(), afterReload.size());
  }

  @Test
  void defaultConstructor_usesDefaultEnvironment() {
    ThreadLocaleHolder.removeLocale();
    assertNotNull(new MockFunctionDocParser());
  }
}
