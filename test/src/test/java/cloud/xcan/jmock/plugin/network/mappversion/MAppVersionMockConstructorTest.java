package cloud.xcan.jmock.plugin.network.mappversion;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MAppVersion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAppVersionMockConstructorTest {


  @Test
  public void MAppVersion1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MAppVersion2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{"v|wchat"});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertArrayEquals(new String[]{"v", "wchat"}, mock.getPrefixDictArray());
  }

  @Test
  public void MAppVersion3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{"v|wchat", "SNAPSHOT|BETA|RELEASE"});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertArrayEquals(new String[]{"v", "wchat"}, mock.getPrefixDictArray());
    Assertions.assertArrayEquals(new String[]{"SNAPSHOT", "BETA", "RELEASE"},
        mock.getReleaseStateDictArray());
  }

  @Test
  public void MAppVersion4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{"v|wchat", "SNAPSHOT|BETA|RELEASE", "1|2"});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertArrayEquals(new String[]{"v", "wchat"}, mock.getPrefixDictArray());
    Assertions.assertArrayEquals(new String[]{"SNAPSHOT", "BETA", "RELEASE"},
        mock.getReleaseStateDictArray());
    Assertions.assertArrayEquals(new String[]{"1", "2"}, mock.getBuildStateDictArray());
  }
}
