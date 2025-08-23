package cloud.xcan.jmock.plugin.locale.mtimezone;

import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.LocaleDocMessage;
import cloud.xcan.jmock.plugin.MTimeZone;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MTimeZoneMockTest {

  String chinaLastName = MessageResources.getString(LocaleDocMessage.DATA_TIMEZONE, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String str = "Pacific/Guadalcanal|Asia/Ho_Chi_Minh";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MTimeZone", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTimeZone mock = (MTimeZone) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println(str);
      Assertions.assertTrue(china.contains(str));
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MTimeZone", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTimeZone mock = (MTimeZone) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
      Assertions.assertTrue(dist.contains(str));
    }
  }

}
