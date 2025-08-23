package cloud.xcan.jmock.plugin.geography.mcity;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.GeographyDocMessage;
import cloud.xcan.jmock.plugin.MCity;
import java.util.ArrayList;
import java.util.Locale;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCityMockTest {

  String cnMessage = MessageResources.getString(GeographyDocMessage.DATA_CITY, Locale.CHINA);
  ArrayList<String> cn = Lists.newArrayList(cnMessage.split("\\|"));

  String enMessage = MessageResources.getString(GeographyDocMessage.DATA_CITY, Locale.ENGLISH);
  ArrayList<String> en = Lists.newArrayList(enMessage.split("\\|"));

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("City", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCity mock = (MCity) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertTrue(cn.contains(str));
    }
  }

  @Test
  public void case2_localTest() throws Exception {
    FunctionToken token = new FunctionToken("City",
        new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCity mock = (MCity) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertTrue(en.contains(str));
    }
  }

  @Test
  public void case3_dictTest() throws Exception {
    String dict = "a|我的|usa";
    FunctionToken token = new FunctionToken("City",
        new String[]{dict});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCity mock = (MCity) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertTrue(dict.contains(str));
    }
  }


}
