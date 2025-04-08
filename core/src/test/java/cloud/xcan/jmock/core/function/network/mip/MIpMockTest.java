package cloud.xcan.jmock.core.function.network.mip;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.network.MIPv4;
import cloud.xcan.jmock.core.function.network.MIPv6;
import cloud.xcan.jmock.core.function.network.MMac;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MIpMockTest {

  @Test
  public void ipv4() throws Exception {
    FunctionToken token = new FunctionToken("IPv4", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MIPv4 mock = (MIPv4) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String ipv4 = mock.mock();
      System.out.println("ipv4 = " + ipv4);
    }
  }

  @Test
  public void ipv6() throws Exception {
    FunctionToken token = new FunctionToken("IPv6", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MIPv6 mock = (MIPv6) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String ipv6 = mock.mock();
      System.out.println("ipv6 = " + ipv6);
    }
  }

  @Test
  public void mac() throws Exception {
    FunctionToken token = new FunctionToken("MMac", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MMac mock = (MMac) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String mac = mock.mock();
      System.out.println("mac = " + mac);
    }
  }

}
