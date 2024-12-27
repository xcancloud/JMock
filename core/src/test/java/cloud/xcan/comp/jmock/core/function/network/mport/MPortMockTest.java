package cloud.xcan.comp.jmock.core.function.network.mport;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.network.MPort;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MPortMockTest {

  @Test
  public void case1_Test() throws Exception {
    FunctionToken token = new FunctionToken("Port", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPort mock = (MPort) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      Integer port = mock.mock();
      System.out.println(port);
      Assertions.assertTrue(1024 < port && port < 65535);
    }
  }

  @Test
  public void case2_Test() throws Exception {
    FunctionToken token = new FunctionToken("Port", new String[]{"2000", "9000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPort mock = (MPort) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      Integer port = mock.mock();
      System.out.println(port);
      Assertions.assertTrue(2000 < port && port < 9000);
    }
  }
}
