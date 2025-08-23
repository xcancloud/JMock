package cloud.xcan.jmock.plugin.date.mlocaleDateTime;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocaleDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleDateTimeMockConstructorTest {

  /**
   * No-parameter MockConstructor: @String()
   */
  @Test
  public void MLocaleDateDateTime1() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  /**
   * Basic MockConstructor:  @String(length)
   */
  @Test
  public void MLocaleDateDateTime2() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime", new String[]{"yy-MM-dd"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MLocaleDateDateTime3() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime",
        new String[]{"yy-MM-dd HH:mm:ss", "Asia/Shanghai", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    Assertions.assertNotNull(mock);
    System.out.println(mock.mock());
  }


}
