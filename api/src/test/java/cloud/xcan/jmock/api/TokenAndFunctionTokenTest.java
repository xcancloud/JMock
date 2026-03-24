package cloud.xcan.jmock.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class TokenAndFunctionTokenTest {

  @Test
  void noopToken_values() {
    assertEquals("NOOP", Token.NOOP.name());
    assertEquals(-1, Token.NOOP.startPos());
    assertEquals(-1, Token.NOOP.endPos());
    assertEquals("@NOOP()", Token.NOOP.token());
  }

  @Test
  void functionToken_emptyVarargs_leavesParamsNull() {
    FunctionToken ft = new FunctionToken("Empty", new String[0]);
    assertEquals("Empty", ft.name());
  }

  @Test
  void functionToken_fromVarargs_buildsIndexedParams() {
    FunctionToken ft = new FunctionToken("Integer", new String[]{"1", "10"});
    assertEquals("Integer", ft.name());
    assertEquals("1", ft.getParams().get("1"));
    assertEquals("10", ft.getParams().get("2"));
    assertEquals('@', ft.getIdentifier());
    assertEquals("@Integer(", ft.prefixIdentifier());
  }

  @Test
  void functionToken_fromMap() {
    Map<String, String> m = new HashMap<>();
    m.put("a", "x");
    FunctionToken ft = new FunctionToken("Foo", m);
    assertSame(m, ft.getParams());
  }

  @Test
  void functionToken_fullConstructor() {
    Map<String, String> p = Map.of("1", "v");
    FunctionToken ft = new FunctionToken('$', "Bar", 1, 20, p, "$Bar(x)");
    assertEquals('$', ft.getIdentifier());
    assertEquals(1, ft.startPos());
    assertEquals(20, ft.endPos());
    assertEquals("$Bar(x)", ft.token());
    assertEquals("$Bar(", ft.prefixIdentifier());
  }

  @Test
  void functionToken_equalsAndHashCode() {
    FunctionToken a = new FunctionToken("X", new String[]{"a"});
    FunctionToken b = new FunctionToken("X", new String[]{"a"});
    assertEquals(a, b);
    assertEquals(a.hashCode(), b.hashCode());

    FunctionToken c = new FunctionToken("X", new String[]{"b"});
    assertNotEquals(a, c);
  }

  @Test
  void abstractToken_setters() {
    FunctionToken ft = new FunctionToken("N", (Map<String, String>) null);
    ft.setName("Z");
    ft.setStartPos(3);
    ft.setEndPos(9);
    ft.setToken("@Z()");
    assertEquals("Z", ft.name());
    assertEquals(3, ft.startPos());
    assertEquals(9, ft.endPos());
    assertEquals("@Z()", ft.token());
  }
}
