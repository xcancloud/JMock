package cloud.xcan.jmock.core.function.article.msentence;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.article.MSentence;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MSentenceMockConstructorTest {


  @Test
  public void MSentence1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Sentence",
        new String[]{});
    MSentence mock = (MSentence) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MSentence2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Sentence",
        new String[]{"en"});
    MSentence mock = (MSentence) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MSentence3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Sentence",
        new String[]{"等我,耳机,佛山"});
    MSentence mock = (MSentence) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MSentence4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Sentence",
        new String[]{"zh_CN", "等我,耳机,佛山"});
    MSentence mock = (MSentence) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }
}
