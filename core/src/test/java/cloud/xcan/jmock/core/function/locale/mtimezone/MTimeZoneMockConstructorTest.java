package cloud.xcan.jmock.core.function.locale.mtimezone;


import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.locale.MTimeZone;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MTimeZoneMockConstructorTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_TIMEZONE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));


  @Test
  public void MTimeZone() throws Exception {
    FunctionToken token = new FunctionToken("MTimeZone", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTimeZone mock = (MTimeZone) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MTimeZone_2() throws Exception {
    FunctionToken token = new FunctionToken("MTimeZone", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTimeZone mock = (MTimeZone) parser.parse(token);
    Assert.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
