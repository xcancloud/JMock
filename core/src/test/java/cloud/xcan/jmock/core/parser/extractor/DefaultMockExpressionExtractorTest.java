package cloud.xcan.jmock.core.parser.extractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.AbstractToken;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.FunctionStartException;
import cloud.xcan.jmock.core.exception.InvalidNameException;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultMockExpressionExtractorTest {

  @Test
  public void startCharExceptionTest() {
    String expression = " x @String()";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(expression);
    try {
      tokenizer.extract();
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(FunctionStartException.class, e.getClass());
    }
  }

  @Test
  public void funcExpressionTest() {
    String expression = "@String()";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(expression);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertNotNull(tokens);
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    Assertions.assertEquals(0, token.startPos());
    Assertions.assertEquals(expression.indexOf(")"), token.endPos() - 1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
  }

  @Test
  public void funcExpressionIgnoreEndTest() {
    String expression = "@String()boy";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(expression);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertNotNull(tokens);
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    Assertions.assertEquals(expression.indexOf("@"), token.startPos());
    Assertions.assertEquals(expression.indexOf(")"), token.endPos() - 1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
  }

  @Test
  public void funcExpressionSkipTest() {
    // Empty characters are not allowed in @ and function names
    String expression = "@ String ( )";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(expression);
    Assertions.assertThrows(InvalidNameException.class, tokenizer::extract);

    String expression2 = "@\n\tString() boy  !!!";
    DefaultMockExpressionExtractor tokenizer2 = new DefaultMockExpressionExtractor(expression2);
    Assertions.assertThrows(InvalidNameException.class, tokenizer2::extract);

    // There can be empty characters between the function name and '('
    String expression3 = "@String\n () boy  !!!";
    DefaultMockExpressionExtractor tokenizer3 = new DefaultMockExpressionExtractor(expression3);
    List<FunctionToken> tokens = tokenizer3.extract();
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    String expression4 = "@String\t ( , , \r \t , aaa) boy  !!!";
    DefaultMockExpressionExtractor tokenizer4 = new DefaultMockExpressionExtractor(expression4);
    tokens = tokenizer4.extract();
    Assertions.assertEquals(1, tokens.size());
    token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(4, ft.getParams().size());
    }
  }

  @Test
  public void funcExpressionEscapeTest() {
    String text = "@String(1,2,sss\\,s)";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(3, ft.getParams().size());
    }
  }

  @Test
  public void funcExpressionEmptyTest() {
    String text = "@String(1,null,)";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(2, ft.getParams().size());
      Assertions.assertEquals("null", ft.getParams().get("1"));
      Assertions.assertEquals(null, ft.getParams().get("2"));
    }
  }

  @Test
  public void tokenExtractTest() {
    String text = "@String(1,null,)";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals("@String(1,null,)", tokens.get(0).token());
  }

  @Test
  public void funcInnerTest() {
    String text = "@String(1,null,@Integer())";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    // Note: Nested functions not supported
    Assertions.assertNotEquals(2, tokens.size());
  }

  @Test
  public void nonMockFunctionTest() {
    String text = "@GetVariable(1,null,@GetVariable())";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    // Note: Nested functions not supported
    Assertions.assertNotEquals(2, tokens.size());
  }

  @Test
  public void shouldRequireFunctionAtStart() {
    DefaultMockExpressionExtractor extractor = new DefaultMockExpressionExtractor("  @Test()");
    try {
      extractor.extract();
    } catch (Exception e) {
      Assertions.fail("Unexpected exception: " + e.getMessage());
    }

    extractor = new DefaultMockExpressionExtractor("Test()");
    try {
      extractor.extract();
      Assertions.fail("Expected FunctionStartException but no exception was thrown");
    } catch (Exception e) {
      if (!(e instanceof FunctionStartException)) {
        Assertions.fail("Unexpected exception type: " + e.getClass().getName());
      }
    }
  }

  @Test
  public void shouldExtractSingleFunction() {
    DefaultMockExpressionExtractor extractor = new DefaultMockExpressionExtractor(
        "@Generate(1,2,3)");

    List<FunctionToken> tokens = extractor.extract();
    Assertions.assertEquals(1, tokens.size());

    FunctionToken token = tokens.get(0);
    Assertions.assertEquals("Generate", token.name());
    Assertions.assertEquals(Map.of("0", "1", "1", "2", "2", "3"), token.getParams());
  }

  @Test
  public void shouldHandleWhitespaceAroundExpression() {
    DefaultMockExpressionExtractor extractor = new DefaultMockExpressionExtractor(
        "  \t@Name()\n  ");

    List<FunctionToken> tokens = extractor.extract();
    Assertions.assertEquals(1, tokens.size());
    Assertions.assertEquals("Name", tokens.get(0).name());
  }

  @Test
  public void shouldRejectMultipleFunctions() {
    DefaultMockExpressionExtractor extractor = new DefaultMockExpressionExtractor(
        "@First() @Second()");
    List<FunctionToken> tokens = extractor.extract();
    Assertions.assertEquals(1, tokens.size());
    Assertions.assertEquals("@First()", tokens.get(0).token());
  }
}
