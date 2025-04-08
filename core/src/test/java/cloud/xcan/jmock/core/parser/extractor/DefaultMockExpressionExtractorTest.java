package cloud.xcan.jmock.core.parser.extractor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.AbstractToken;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.FunctionNameException;
import cloud.xcan.jmock.core.exception.FunctionStartException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

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
    Assert.assertNotNull(tokens);
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    Assert.assertEquals(0, token.startPos());
    Assert.assertEquals(expression.indexOf(")"), token.endPos());
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
  }

  @Test
  public void funcExpressionIgnoreEndTest() {
    String expression = "@String()boy";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(expression);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertNotNull(tokens);
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    Assert.assertEquals(expression.indexOf("@"), token.startPos());
    Assert.assertEquals(expression.indexOf(")"), token.endPos());
    Assert.assertEquals(expression.indexOf(")"), tokenizer.getPos());
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
  }

  @Test
  public void funcExpressionSkipTest() {
    // Empty characters are not allowed in @ and function names
    String expression = "@ String ( )";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(expression);
    Assert.assertThrows(FunctionNameException.class, tokenizer::extract);

    String expression2 = "@\n\tString() boy  !!!";
    DefaultMockExpressionExtractor tokenizer2 = new DefaultMockExpressionExtractor(expression2);
    Assert.assertThrows(FunctionNameException.class, tokenizer2::extract);

    // There can be empty characters between the function name and '('
    String expression3 = "@String\n () boy  !!!";
    DefaultMockExpressionExtractor tokenizer3 = new DefaultMockExpressionExtractor(expression3);
    List<FunctionToken> tokens = tokenizer3.extract();
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    String expression4 = "@String\t ( , , \r \t , aaa) boy  !!!";
    DefaultMockExpressionExtractor tokenizer4 = new DefaultMockExpressionExtractor(expression4);
    tokens = tokenizer4.extract();
    Assert.assertEquals(1, tokens.size());
    token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(4, ((FunctionToken) token).getParams().size());
    }
  }

  @Test
  public void funcExpressionEscapeTest() {
    String text = "@String(1,2,sss\\,s)";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(3, ((FunctionToken) token).getParams().size());
    }
  }

  @Test
  public void funcExpressionEmptyTest() {
    String text = "@String(1,null,)";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(3, ((FunctionToken) token).getParams().size());
      Assert.assertEquals("null", ((FunctionToken) token).getParams().get("2"));
      Assert.assertEquals("", ((FunctionToken) token).getParams().get("3"));
    }
  }

  @Test
  public void tokenExtractTest() {
    String text = "@String(1,null,)";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals("@String(1,null,)", tokens.get(0).token());
  }

  @Test
  public void funcInnerTest() {
    String text = "@String(1,null,@Integer())";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    // Note: Nested functions not supported
    Assert.assertNotEquals(2, tokens.size());
  }

  @Test
  public void nonMockFunctionTest() {
    String text = "@GetVariable(1,null,@GetVariable())";
    DefaultMockExpressionExtractor tokenizer = new DefaultMockExpressionExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    // Note: Nested functions not supported
    Assert.assertNotEquals(2, tokens.size());
  }
}
