package cloud.xcan.jmock.plugin.network.mappname;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MAppName;
import cloud.xcan.jmock.plugin.NetworkDocMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MAppNameMockTest {

  static ArrayList<String> china;

  @BeforeAll
  static void registerNetworkBundleAndLoadExpected() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-network-plugin-messages");
    String zh = MessageResources.getString(NetworkDocMessage.DATA_APP_NAME);
    china = Arrays.stream(zh.split("\\|", -1))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .collect(Collectors.toCollection(ArrayList::new));
  }

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
