package cloud.xcan.jmock.core.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.ThreadLocaleHolder;
import cloud.xcan.jmock.core.environment.DefaultEvalEnvironment;
import cloud.xcan.jmock.core.exception.ConstructorMismatchException;
import cloud.xcan.jmock.core.fixtures.MEcho;
import java.util.Locale;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class SimpleMockFunctionTokenParserUnitTest {

  @AfterEach
  void tearDown() {
    ThreadLocaleHolder.removeLocale();
  }

  @Test
  void parse_noArg_and_withParams() throws Exception {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    env.registerMockClass(MEcho.class);
    SimpleMockFunctionTokenParser p = new SimpleMockFunctionTokenParser(env);

    assertNotNull(p.parse(new FunctionToken("Echo", new String[0])));
    assertNotNull(p.parse(new FunctionToken("Echo", new String[]{"z"})));
  }

  @Test
  void parse_unknown_returnsNull() throws Exception {
    DefaultEvalEnvironment env = new DefaultEvalEnvironment(Locale.ENGLISH);
    SimpleMockFunctionTokenParser p = new SimpleMockFunctionTokenParser(env);
    assertNull(p.parse(new FunctionToken("Missing", new String[0])));
  }

  @Test
  void hasParams() {
    SimpleMockFunctionTokenParser p = new SimpleMockFunctionTokenParser();
    assertTrue(p.hasParams(new FunctionToken("X", new String[]{"1"})));
    assertTrue(!p.hasParams(new FunctionToken("X", new String[0])));
    assertTrue(!p.hasParams(new FunctionToken("X", (Map<String, String>) null)));
    assertTrue(!p.hasParams(null));
  }

  @Test
  void buildConstructorParams() {
    FunctionToken t = new FunctionToken("E", new String[]{"hello"});
    Object[] args = SimpleMockFunctionTokenParser.buildConstructorParams(t);
    assertEquals(1, args.length);
    assertEquals("hello", args[0]);
  }

  @Test
  void invokeConstructor_mismatch_wraps() {
    SimpleMockFunctionTokenParser p = new SimpleMockFunctionTokenParser();
    assertThrows(ConstructorMismatchException.class,
        () -> p.invokeConstructor(MEcho.class, "Echo", new Object[]{1, 2}));
  }
}
