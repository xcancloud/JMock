
package cloud.xcan.jmock.core.function.article.mtangpoetry;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.article.MTangPoetry;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MTangPoetryMockConstructorTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_TANG_POETRY, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishLastName = MessageResources.getString(JMockMessage.FDATA_TANG_POETRY, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));

  @Test
  public void MTangPoetry() throws Exception {
    FunctionToken token = new FunctionToken("MTangPoetry", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTangPoetry mock = (MTangPoetry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MTangPoetry_1() throws Exception {
    FunctionToken token = new FunctionToken("MTangPoetry", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTangPoetry mock = (MTangPoetry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MTangPoetry_2() throws Exception {
    FunctionToken token = new FunctionToken("MTangPoetry", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTangPoetry mock = (MTangPoetry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
