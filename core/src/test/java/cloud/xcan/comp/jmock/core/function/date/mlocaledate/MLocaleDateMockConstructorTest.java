
package cloud.xcan.comp.jmock.core.function.date.mlocaledate;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.date.MLocaleDate;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MLocaleDateMockConstructorTest {

  /**
   * No-parameter MockConstructor: @String()
   */
  @Test
  public void MLocaleDate1() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  /**
   * Basic MockConstructor:  @String(length)
   */
  @Test
  public void MLocaleDate2() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate", new String[]{"yy-MM-dd"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  @Test
  public void MLocaleDate3() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate",
        new String[]{"yy-MM-dd", "America/Argentina/Buenos_Aires"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    Assert.assertNotNull(mock);
    System.out.println(mock.mock());
  }


}
