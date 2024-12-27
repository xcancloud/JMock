
package cloud.xcan.comp.jmock.core.function.article.mparagraph;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.function.article.MParagraph;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MParagraphMockConstructorTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_PARAGRAPH, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishLastName = MessageResources.getString(JMockMessage.FDATA_PARAGRAPH, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));

  @Test
  public void MParagraph() throws Exception {
    FunctionToken token = new FunctionToken("MParagraph", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MParagraph mock = (MParagraph) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MParagraph_1() throws Exception {
    FunctionToken token = new FunctionToken("MParagraph", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MParagraph mock = (MParagraph) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MParagraph_2() throws Exception {
    FunctionToken token = new FunctionToken("MParagraph", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MParagraph mock = (MParagraph) parser.parse(token);
    Assert.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
