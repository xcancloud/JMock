
package cloud.xcan.jmock.core.function.network.mappversion;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.network.MAppVersion;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MAppVersionMockTest {

  @Test
  public void case1_defaultRangeTest() throws Exception {
    FunctionToken token = new FunctionToken("AppVersion", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppVersion mock = (MAppVersion) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String AppVersion = mock.mock();
      System.out.println("AppVersion = " + AppVersion);
    }
  }

  @Test
  public void case2_customizeRangeTest() throws Exception {
    FunctionToken token = new FunctionToken("AppVersion", new String[]{"v|m"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppVersion mock = (MAppVersion) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String AppVersion = mock.mock();
      System.out.println("AppVersion = " + AppVersion);
    }
  }

  @Test
  public void case3_customizeRangeTest() throws Exception {
    FunctionToken token = new FunctionToken("AppVersion", new String[]{"v|m","SNAPSHOT|BETA|RELEASE"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppVersion mock = (MAppVersion) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String AppVersion = mock.mock();
      System.out.println("AppVersion = " + AppVersion);
    }
  }


  @Test
  public void case4_customizeRangeTest() throws Exception {
    FunctionToken token = new FunctionToken("AppVersion", new String[]{"v|m","SNAPSHOT|BETA|RELEASE","build1|build2"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAppVersion mock = (MAppVersion) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String AppVersion = mock.mock();
      System.out.println("AppVersion = " + AppVersion);
    }
  }
}
