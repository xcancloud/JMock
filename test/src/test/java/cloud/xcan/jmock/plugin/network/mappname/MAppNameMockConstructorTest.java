package cloud.xcan.jmock.plugin.network.mappname;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MAppName;
import cloud.xcan.jmock.plugin.NetworkDocMessage;
import java.util.ArrayList;
import java.util.Locale;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAppNameMockConstructorTest {

  String chinaMessage = MessageResources.getString(NetworkDocMessage.DATA_APP_NAME);
  ArrayList<String> china = Lists.newArrayList(chinaMessage.split("\\|"));

  String enMessage = MessageResources.getString(NetworkDocMessage.DATA_APP_NAME, Locale.ENGLISH);
  ArrayList<String> en = Lists.newArrayList(enMessage.split("\\|"));

  String str = "jndi|ldap";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  @Test
  public void MAppName() throws Exception {
    FunctionToken token = new FunctionToken("MAppName", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppName mock = (MAppName) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MAppName_1() throws Exception {
    FunctionToken token = new FunctionToken("MAppName", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppName mock = (MAppName) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

//  @Test
//  public void MAppName_2() throws Exception {
//    FunctionToken token = new FunctionToken("MAppName", new String[]{"en"});
//    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
//    MAppName mock = (MAppName) parser.parse(token);
//    Assertions.assertNotNull(mock);
//    System.out.println("mock = " + mock.mock());
//    Assertions.assertTrue(en.contains(mock.mock()));
//  }

}
