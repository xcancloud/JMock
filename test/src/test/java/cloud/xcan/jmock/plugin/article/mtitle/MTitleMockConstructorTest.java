package cloud.xcan.jmock.plugin.article.mtitle;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.ArticleDocMessage;
import cloud.xcan.jmock.plugin.MTitle;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MTitleMockConstructorTest {

  String chinaLastName = MessageResources.getString(ArticleDocMessage.DATA_TITLE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishLastName = MessageResources.getString(ArticleDocMessage.DATA_TITLE, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));

  @Test
  public void MTitle() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MTitle_1() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MTitle_2() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
