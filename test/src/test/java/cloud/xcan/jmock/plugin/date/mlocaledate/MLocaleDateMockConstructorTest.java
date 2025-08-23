package cloud.xcan.jmock.plugin.date.mlocaledate;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocaleDate;
import org.junit.jupiter.api.Assertions;
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
    Assertions.assertNotNull(mock);
  }

  /**
   * Basic MockConstructor:  @String(length)
   */
  @Test
  public void MLocaleDate2() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate", new String[]{"yy-MM-dd"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MLocaleDate3() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate",
        new String[]{"yy-MM-dd", "America/Argentina/Buenos_Aires", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    Assertions.assertNotNull(mock);
    System.out.println(mock.mock());
  }


}
