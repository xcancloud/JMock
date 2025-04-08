package cloud.xcan.jmock.core.parser.extractor;

import cloud.xcan.jmock.api.AbstractToken;
import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.exception.FunctionNameException;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.Assert;
import org.junit.Test;

public class DefaultMockTextExtractorTest {

  @Test
  public void splitTest() {
    String str = "aa,bb,cc,,ee,ff\\,,gg\\,,,\\aa";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(str);
    Map<String, String> params = tokenizer.splitAndEscape(str, ',');
    Assert.assertEquals(9, params.size());

    String str2 = "1,2,sss\\,s";
    DefaultMockTextExtractor tokenizer2 = new DefaultMockTextExtractor(str);
    Map<String, String> params2 = tokenizer2.splitAndEscape(str2, ',');
    Assert.assertEquals(3, params2.size());
  }

  @Test
  public void splitNamingParameterTest() {
    String str = "param1=1,param2=2,cc,,ee,ff\\,,gg\\,,,\\aa";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(str);
    Map<String, String> params = tokenizer.splitAndEscape(str, ',');
    Assert.assertEquals(9, params.size());
    Assert.assertEquals(9, params.keySet().stream().filter(ObjectUtils::isNotEmpty).count());
  }

  @Test
  public void funcOnlyExpressionTest() {
    String expression = "@String()";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(expression);
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
  public void funcTextTest() {
    String text = "hello@String()boy";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertNotNull(tokens);
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    Assert.assertEquals(text.indexOf("@"), token.startPos());
    Assert.assertEquals(text.indexOf(")"), token.endPos());
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
  }

  @Test
  public void funcTextBlankTest() {
    String text = " hello  @String() boy";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertNotNull(tokens);
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    Assert.assertEquals(text.indexOf("@"), token.startPos());
    Assert.assertEquals(text.indexOf(")"), token.endPos());
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
  }

  @Test
  public void funcTextSkipTest() {
    // Empty characters are not allowed in @ and function names
    String text = " hello  @  String() boy !!!";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    Assert.assertThrows(FunctionNameException.class, tokenizer::extract);

    String text2 = " hello  @\n\tString() boy  !!!";
    DefaultMockTextExtractor tokenizer2 = new DefaultMockTextExtractor(text2);
    Assert.assertThrows(FunctionNameException.class, tokenizer2::extract);

    // There can be empty characters between the function name and '('
    String text3 = " hello  @String\n () boy  !!!";
    DefaultMockTextExtractor tokenizer3 = new DefaultMockTextExtractor(text3);
    List<FunctionToken> tokens = tokenizer3.extract();
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    Assert.assertEquals("String", token.name());
    String text4 = " hello  @String\t ( , , \r \t , aaa) boy  !!!";
    DefaultMockTextExtractor tokenizer4 = new DefaultMockTextExtractor(text4);
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
  public void funcTextEscapeTest() {
    String text = " hello \\ \\@String() @String(1,2,sss\\,s)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals(1, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(3, ((FunctionToken) token).getParams().size());
    }
  }

  @Test
  public void funcTextEmptyTest() {
    String text = "@String(1,null,)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
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
  public void funcTextMultiTokenTest() {
    String text = " MInteger \\ @MInteger() @String(1,2,sss\\\\,s) \t ab @MInteger(1)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals(3, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
    token = tokens.get(1);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(4, ((FunctionToken) token).getParams().size());
    }
    token = tokens.get(2);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(1, ((FunctionToken) token).getParams().size());
    }
  }

  @Test
  public void tokenExtractTest() {
    String text = " MInteger \\ @MInteger() @String(1,2,sss\\\\,s) \t ab @MInteger(1)";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(text);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals("@MInteger()", tokens.get(0).token());
    Assert.assertEquals("@String(1,2,sss\\\\,s)", tokens.get(1).token());
    Assert.assertEquals("@MInteger(1)", tokens.get(2).token());
  }

  @Test
  public void funcTextJsonTest() {
    String jsonText = "{\n"
        + "\"id\": 2021199010310091,\n"
        + "\"no\": \"case0091\",\n"
        + "\"name\": \"API Test\",\n"
        + "\"api\": \"Add User\",\n"
        + "\"url\": \"http://1270.0.0.1:1805/uc/api/v1/user\",\n"
        + "\"METHOD\": \"POST\",\n"
        + "\"Content-Type\": \"application/json\",\n"
        + "\"body\": \"{ \\\"id\\\": @Integer(6,6) \\\"name\\\": @String() }\"\n"
        + "}";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(jsonText);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals(2, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(2, ((FunctionToken) token).getParams().size());
    }
    token = tokens.get(1);
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
  }

  @Test
  public void funcTextXmlTest() {
    String xmlText = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n"
        + "\t<id>2021199010310091</id>\n"
        + "\t<no>case0091</no>\n"
        + "\t<name>API Test</name>\n"
        + "\t<api>Add User</api>\n"
        + "\t<url>http://1270.0.0.1:1805/uc/api/v1/user</url>\n"
        + "\t<METHOD>POST</METHOD>\n"
        + "\t<Content-Type>application/json</Content-Type>\n"
        + "\t<body>{ &quot;id&quot;: @Integer(6,6) &quot;name&quot;: @String() }</body>";
    DefaultMockTextExtractor tokenizer = new DefaultMockTextExtractor(xmlText);
    List<FunctionToken> tokens = tokenizer.extract();
    Assert.assertEquals(2, tokens.size());
    AbstractToken token = tokens.get(0);
    if (token instanceof FunctionToken) {
      Assert.assertNotNull(((FunctionToken) token).getParams());
      Assert.assertEquals(2, ((FunctionToken) token).getParams().size());
    }
    token = tokens.get(1);
    if (token instanceof FunctionToken) {
      Assert.assertNull(((FunctionToken) token).getParams());
    }
  }

}
