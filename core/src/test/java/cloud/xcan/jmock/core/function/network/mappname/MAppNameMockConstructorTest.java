package cloud.xcan.jmock.core.function.network.mappname;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.network.MAppName;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import java.util.Locale;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MAppNameMockConstructorTest {

  String chinaMessage = MessageResources.getString(JMockMessage.FDATA_APP_NAME);
  ArrayList<String> china = Lists.newArrayList(chinaMessage.split("\\|"));

  String enMessage = MessageResources.getString(JMockMessage.FDATA_APP_NAME, Locale.ENGLISH);
  ArrayList<String> en = Lists.newArrayList(enMessage.split("\\|"));

  String str = "jndi|ldap";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  @Test
  public void MAppName() throws Exception {
    FunctionToken token = new FunctionToken("MAppName", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppName mock = (MAppName) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MAppName_1() throws Exception {
    FunctionToken token = new FunctionToken("MAppName", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppName mock = (MAppName) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(dist.contains(mock.mock()));
  }

//  @Test
//  public void MAppName_2() throws Exception {
//    FunctionToken token = new FunctionToken("MAppName", new String[]{"en"});
//    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
//    MAppName mock = (MAppName) parser.parse(token);
//    Assert.assertNotNull(mock);
//    System.out.println("mock = " + mock.mock());
//    Assert.assertTrue(en.contains(mock.mock()));
//  }

}
