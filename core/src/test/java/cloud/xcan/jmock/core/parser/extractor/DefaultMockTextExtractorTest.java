package cloud.xcan.jmock.core.parser.extractor;

import cloud.xcan.jmock.api.AbstractToken;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.InvalidNameException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultMockTextExtractorTest {

  @Test
  public void funcOnlyExpressionTest() {
    String expression = "@String()";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(expression);
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
  public void funcTextTest() {
    String text = "hello@String()boy";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertNotNull(tokens);
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    Assertions.assertEquals(text.indexOf("@"), token.startPos());
    Assertions.assertEquals(text.indexOf(")"), token.endPos() - 1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
  }

  @Test
  public void funcTextBlankTest() {
    String text = " hello  @String() boy";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertNotNull(tokens);
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    Assertions.assertEquals(text.indexOf("@"), token.startPos());
    Assertions.assertEquals(text.indexOf(")"), token.endPos() - 1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
  }

  @Test
  public void funcTextSkipTest() {
    // Empty characters are not allowed in @ and function names
    String text = " hello  @  String() boy !!!";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    Assertions.assertThrows(InvalidNameException.class, tokenizer::extract);

    String text2 = " hello  @\n\tString() boy  !!!";
    DefaultMockTextExtractor tokenizer2 = new DefaultMockTextExtractor(text2);
    Assertions.assertThrows(InvalidNameException.class, tokenizer2::extract);

    // There can be empty characters between the function name and '('
    String text3 = " hello  @String\n () boy  !!!";
    DefaultMockTextExtractor tokenizer3 = new DefaultMockTextExtractor(text3);
    List<FunctionToken> tokens = tokenizer3.extract();
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assertions.assertEquals("String", token.name());
    String text4 = " hello  @String\t ( , , \r \t , aaa) boy  !!!";
    DefaultMockTextExtractor tokenizer4 = new DefaultMockTextExtractor(text4);
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
  public void funcTextEscapeTest() {
    String text = " hello \\ \\@String() @String(1,2,sss\\,s)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(3, ft.getParams().size());
    }
  }

  @Test
  public void funcTextEmptyTest() {
    String text = "@String(1,null,)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(2, ft.getParams().size());
      Assertions.assertEquals("1", ft.getParams().get("0"));
      Assertions.assertEquals("null", ft.getParams().get("1"));
    }

    text = "@String(1,null,,uu)";
    tokenizer = new DefaultMockTextExtractor(text);
    tokens = tokenizer.extract();
    Assertions.assertEquals(1, tokens.size());
    token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(4, ft.getParams().size());
      Assertions.assertEquals("1", ft.getParams().get("0"));
      Assertions.assertEquals("null", ft.getParams().get("1"));
      Assertions.assertEquals("", ft.getParams().get("2"));
      Assertions.assertEquals("uu", ft.getParams().get("3"));
    }
  }

  @Test
  public void funcTextMultiTokenTest() {
    String text = " MInteger \\ @MInteger() @String(1,2,sss\\\\,s) \t ab @MInteger(1)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(3, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
    token = tokens.get(1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertNotNull(ft.getParams());
      Assertions.assertEquals(4, ft.getParams().size());
    }
    token = tokens.get(2);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(1, ft.getParams().size());
    }
  }

  @Test
  public void tokenExtractTest() {
    String text = " MInteger \\ @MInteger() @String(1,2,sss\\\\,s) \t ab @MInteger(1)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals("@MInteger()", tokens.get(0).token());
    Assertions.assertEquals("@String(1,2,sss\\\\,s)", tokens.get(1).token());
    Assertions.assertEquals("@MInteger(1)", tokens.get(2).token());
  }

  @Test
  public void funcTextJsonTest() {
    String jsonText = """
        {
        "id": 2021199010310091,
        "no": "case0091",
        "name": "API Test",
        "api": "Add User",
        "url": "http://1270.0.0.1:1805/uc/api/v1/user",
        "METHOD": "POST",
        "Content-Type": "application/json",
        "body": "{ \\"id\\": @Integer(6,6) \\"name\\": @String() }"
        }""";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(jsonText);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(2, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(2, ft.getParams().size());
    }
    token = tokens.get(1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
  }

  @Test
  public void funcTextXmlTest() {
    String xmlText = """
        <?xml version="1.0" encoding="UTF-8" ?>
        \t<id>2021199010310091</id>
        \t<no>case0091</no>
        \t<name>API Test</name>
        \t<api>Add User</api>
        \t<url>http://1270.0.0.1:1805/uc/api/v1/user</url>
        \t<METHOD>POST</METHOD>
        \t<Content-Type>application/json</Content-Type>
        \t<body>{ &quot;id&quot;: @Integer(6,6) &quot;name&quot;: @String() }</body>""";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(xmlText);
    List<FunctionToken> tokens = tokenizer.extract();
    Assertions.assertEquals(2, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken ft) {
      Assertions.assertFalse(ft.getParams().isEmpty());
      Assertions.assertEquals(2, ft.getParams().size());
    }
    token = tokens.get(1);
    if (token instanceof FunctionToken ft) {
      Assertions.assertTrue(ft.getParams().isEmpty());
    }
  }

}
