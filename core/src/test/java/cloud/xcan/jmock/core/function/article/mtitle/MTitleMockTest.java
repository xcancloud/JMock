package cloud.xcan.jmock.core.function.article.mtitle;

import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.article.MTitle;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MTitleMockTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_TITLE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String str1 = "司马";
  ArrayList<String> dist1 = Lists.newArrayList(str1.split("\\|"));

  String englishLastName = MessageResources.getString(JMockMessage.FDATA_TITLE, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));


  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println(str);
      Assertions.assertTrue(china.contains(str));
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
      Assertions.assertTrue(dist.contains(str));
    }
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{str1});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertTrue(dist1.contains(str));
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MTitle", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTitle mock = (MTitle) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertTrue(english.contains(str));
    }
  }

}
