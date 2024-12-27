
package cloud.xcan.comp.jmock.core.function.date.mlocaleDateTime;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.date.MLocaleDateTime;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
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
    Assert.assertNotNull(mock);
  }

  /**
   * Basic MockConstructor:  @String(length)
   */
  @Test
  public void MLocaleDateDateTime2() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime", new String[]{"yy-MM-dd"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  @Test
  public void MLocaleDateDateTime3() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime",
        new String[]{"yy-MM-dd HH:mm:ss", "Asia/Shanghai"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    Assert.assertNotNull(mock);
    System.out.println(mock.mock());
  }


}
