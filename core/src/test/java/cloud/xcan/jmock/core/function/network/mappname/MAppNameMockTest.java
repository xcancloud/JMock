package cloud.xcan.jmock.core.function.network.mappname;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.network.MAppName;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import java.util.Locale;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAppNameMockTest {

  String chinaLastName = MessageResources.getString(JMockMessage.FDATA_APP_NAME);
  ArrayList<String> china = Lists.newArrayList(chinaLastName.split("\\|"));

  String enMessage = MessageResources.getString(JMockMessage.FDATA_APP_NAME, Locale.ENGLISH);
  ArrayList<String> en = Lists.newArrayList(enMessage.split("\\|"));

  String str = "QQ|微信";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MAppName", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppName mock = (MAppName) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
      Assertions.assertTrue(china.contains(str));
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MAppName", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppName mock = (MAppName) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
      Assertions.assertTrue(dist.contains(str));
    }
  }

//  @Test
//  public void case3() throws Exception {
//    FunctionToken token = new FunctionToken("MAppName", new String[]{"en"});
//    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
//    MAppName mock = (MAppName) parser.parse(token);
//    for (int i = 0; i < 10; i++) {
//      String str = mock.mock();
//      System.out.println("str = " + str);
//      Assertions.assertTrue(en.contains(str));
//    }
//  }

}
