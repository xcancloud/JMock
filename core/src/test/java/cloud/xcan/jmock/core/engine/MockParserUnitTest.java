package cloud.xcan.jmock.core.engine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;

class MockParserUnitTest {

  private final MockParser parser = new MockParser();

  @Test
  void parse_nullOrEmpty_returnsEmpty() {
    assertTrue(parser.parse(null).isEmpty());
    assertTrue(parser.parse("").isEmpty());
  }

  @Test
  void parse_plainText_singleSegment() {
    List<MockExpr> nodes = parser.parse("hello");
    assertEquals(1, nodes.size());
    assertTrue(nodes.get(0) instanceof MockExpr.TextSegment);
    assertEquals("hello", ((MockExpr.TextSegment) nodes.get(0)).text());
  }

  @Test
  void parse_escapeCommaPipeBackslash() {
    MockParser p = new MockParser();
    List<MockExpr> nodes = p.parse("a\\,b\\|c\\\\d");
    assertEquals(1, nodes.size());
    String t = ((MockExpr.TextSegment) nodes.get(0)).text();
    assertTrue(t.contains(",") && t.contains("|") && t.contains("\\"));
  }

  @Test
  void parseExpression_requiresSingleFunction() {
    assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("plain"));
    assertThrows(IllegalArgumentException.class, () -> parser.parseExpression("a@Echo()"));
  }

  @Test
  void parse_customEscapeAndIdentifier() {
    MockParser p = new MockParser('#', '$');
    List<MockExpr> nodes = p.parse("x$Foo()y");
    assertEquals(3, nodes.size());
  }

  @Test
  void parse_invalidFunctionName_becomesText() {
    List<MockExpr> nodes = parser.parse("@integer()");
    assertEquals(1, nodes.size());
    assertTrue(nodes.get(0) instanceof MockExpr.TextSegment);
  }

  @Test
  void parse_functionWithoutParens() {
    List<MockExpr> nodes = parser.parse("@Echo");
    assertEquals(1, nodes.size());
    MockExpr.FunctionCall fc = (MockExpr.FunctionCall) nodes.get(0);
    assertEquals("Echo", fc.name());
    assertTrue(fc.args().isEmpty());
    assertFalse(fc.explicitEmptyParentheses());
  }

  @Test
  void parse_functionWithEmptyParens_setsExplicitFlag() {
    List<MockExpr> nodes = parser.parse("@Echo()");
    assertEquals(1, nodes.size());
    MockExpr.FunctionCall fc = (MockExpr.FunctionCall) nodes.get(0);
    assertEquals("Echo", fc.name());
    assertTrue(fc.args().isEmpty());
    assertTrue(fc.explicitEmptyParentheses());
  }

  @Test
  void parse_repeat_toArrayExpr() {
    List<MockExpr> nodes = parser.parse("@Repeat(@Echo(x),2)");
    assertEquals(1, nodes.size());
    MockExpr.ArrayExpr ae = (MockExpr.ArrayExpr) nodes.get(0);
    assertEquals(2, ae.count());
  }

  @Test
  void parse_repeat_nonExpressionFirst_staysFunctionCall() {
    List<MockExpr> nodes = parser.parse("@Repeat(hello,3)");
    assertTrue(nodes.get(0) instanceof MockExpr.FunctionCall);
  }

  @Test
  void parse_repeat_badCount_staysFunctionCall() {
    List<MockExpr> nodes = parser.parse("@Repeat(@Echo(),nope)");
    assertTrue(nodes.get(0) instanceof MockExpr.FunctionCall);
  }

  @Test
  void mockExpr_argumentFactories() {
    MockExpr.Argument lit = MockExpr.Argument.literal("0", "v");
    assertTrue(!lit.isExpression() && "v".equals(lit.literalValue()));

    MockExpr.FunctionCall inner = new MockExpr.FunctionCall("X", List.of(), false, 0, 1);
    MockExpr.Argument ex = MockExpr.Argument.expr("0", inner);
    assertTrue(ex.isExpression());
    assertEquals(inner, ex.exprValue());
  }
}
