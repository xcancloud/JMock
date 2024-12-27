
package cloud.xcan.comp.jmock.core.function.network.mappversion;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.network.MAppVersion;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MAppVersionMockConstructorTest {


  @Test
  public void MAppVersion1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  @Test
  public void MAppVersion2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{"v|wchat"});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertArrayEquals(new String[]{"v","wchat"},mock.getPrefixDictArray());
  }

  @Test
  public void MAppVersion3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{"v|wchat", "SNAPSHOT|BETA|RELEASE"});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertArrayEquals(new String[]{"v","wchat"},mock.getPrefixDictArray());
    Assert.assertArrayEquals(new String[]{"SNAPSHOT","BETA","RELEASE"},mock.getReleaseStateDictArray());
  }

  @Test
  public void MAppVersion4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("AppVersion",
        new String[]{"v|wchat", "SNAPSHOT|BETA|RELEASE","1|2"});
    MAppVersion mock = (MAppVersion) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertArrayEquals(new String[]{"v","wchat"},mock.getPrefixDictArray());
    Assert.assertArrayEquals(new String[]{"SNAPSHOT","BETA","RELEASE"},mock.getReleaseStateDictArray());
    Assert.assertArrayEquals(new String[]{"1","2"},mock.getBuildStateDictArray());
  }
}
