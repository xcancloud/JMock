package cloud.xcan.comp.jmock.core.function.article.mword;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.function.article.MWord;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MWordMockConstructorTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_WORD, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishLastName = MessageResources.getString(JMockMessage.FDATA_WORD, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));

  @Test
  public void MWord() throws Exception {
    FunctionToken token = new FunctionToken("MWord", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MWord mock = (MWord) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MWord_1() throws Exception {
    FunctionToken token = new FunctionToken("MWord", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MWord mock = (MWord) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MWord_2() throws Exception {
    FunctionToken token = new FunctionToken("MWord", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MWord mock = (MWord) parser.parse(token);
    Assert.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
