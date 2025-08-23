package cloud.xcan.jmock.plugin.user.mfirstname;

import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MFirstname;
import cloud.xcan.jmock.plugin.UserDocMessage;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MFirstnameMockTest {

  String chinaFirstname = MessageResources.getString(UserDocMessage.DATA_FIRSTNAME, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaFirstname.split("\\|"));

  String str = "上官|慕容";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  String englishFirstname = MessageResources.getString(UserDocMessage.DATA_FIRSTNAME, ENGLISH);
  ArrayList<String> english = Lists.newArrayList(englishFirstname.split("\\|"));


  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MFirstname", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFirstname mock = (MFirstname) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MFirstname", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFirstname mock = (MFirstname) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("value: " + str);
      Assertions.assertTrue(dist.contains(str));
    }
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MFirstname", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFirstname mock = (MFirstname) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      String str = mock.mock();
      System.out.println("value: " + str);
      Assertions.assertTrue(english.contains(str));
    }
  }

}
