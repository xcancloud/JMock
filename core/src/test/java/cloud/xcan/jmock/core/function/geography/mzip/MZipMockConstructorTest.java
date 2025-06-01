package cloud.xcan.jmock.core.function.geography.mzip;


import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.geography.MZip;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MZipMockConstructorTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_ZIP, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishLastName = MessageResources.getString(JMockMessage.FDATA_ZIP, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishLastName.split("\\|"));

  @Test
  public void MZip() throws Exception {
    FunctionToken token = new FunctionToken("MZip", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MZip mock = (MZip) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MZip_1() throws Exception {
    FunctionToken token = new FunctionToken("MZip", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MZip mock = (MZip) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(english.contains(mock.mock()));
  }

  @Test
  public void MZip_2() throws Exception {
    FunctionToken token = new FunctionToken("MZip", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MZip mock = (MZip) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
