package cloud.xcan.jmock.plugin.geography.mprovince;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.GeographyDocMessage;
import cloud.xcan.jmock.plugin.MProvince;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MProvinceMockConstructorTest {

  String chinaMessage = MessageResources.getString(GeographyDocMessage.DATA_PROVINCE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaMessage.split("\\|"));

  String str = "北京|山东";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishMessage = MessageResources.getString(GeographyDocMessage.DATA_PROVINCE, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishMessage.split("\\|"));


  @Test
  public void MProvince() throws Exception {
    FunctionToken token = new FunctionToken("MProvince", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProvince mock = (MProvince) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MProvince_1() throws Exception {
    FunctionToken token = new FunctionToken("MProvince", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProvince mock = (MProvince) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MProvince_2() throws Exception {
    FunctionToken token = new FunctionToken("MProvince", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProvince mock = (MProvince) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }
}
