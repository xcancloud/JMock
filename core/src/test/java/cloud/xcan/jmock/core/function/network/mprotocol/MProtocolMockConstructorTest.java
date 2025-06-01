package cloud.xcan.jmock.core.function.network.mprotocol;


import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.network.MProtocol;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MProtocolMockConstructorTest {

  String chinaMessage = MessageResources.getString(JMockMessage.FDATA_PROTOCOL, CHINA);
  ArrayList<String> china = Lists.newArrayList(chinaMessage.split("\\|"));

  String str = "jndi|ldap";
  ArrayList<String> dist = Lists.newArrayList(str.split("\\|"));

  @Test
  public void MProtocol() throws Exception {
    FunctionToken token = new FunctionToken("MProtocol", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProtocol mock = (MProtocol) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MProtocol_1() throws Exception {
    FunctionToken token = new FunctionToken("MProtocol", new String[]{str});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MProtocol mock = (MProtocol) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertTrue(dist.contains(mock.mock()));
  }

}
