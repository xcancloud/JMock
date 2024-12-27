package cloud.xcan.comp.jmock.core.function.geography.mprovince;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.function.geography.MProvince;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MProvinceMockConstructorTest {

  String chinaMessage = MessageResources.getString(JMockMessage.FDATA_PROVINCE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaMessage.split("\\|"));

  String str = "北京|山东";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishMessage = MessageResources.getString(JMockMessage.FDATA_PROVINCE, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishMessage.split("\\|"));


  @Test
  public void MProvince() throws Exception {
    FunctionToken token = new FunctionToken("MProvince", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProvince mock = (MProvince) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MProvince_1() throws Exception {
    FunctionToken token = new FunctionToken("MProvince", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProvince mock = (MProvince) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MProvince_2() throws Exception {
    FunctionToken token = new FunctionToken("MProvince", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProvince mock = (MProvince) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(dist.contains(mock.mock()));
  }
}
