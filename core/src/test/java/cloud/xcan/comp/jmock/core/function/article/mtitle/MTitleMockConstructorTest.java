package cloud.xcan.comp.jmock.core.function.article.mtitle;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.function.article.MTitle;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MTitleMockConstructorTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_TITLE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishLastName = MessageResources.getString(JMockMessage.FDATA_TITLE, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));

  @Test
  public void MTitle() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MTitle_1() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MTitle_2() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    Assert.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
