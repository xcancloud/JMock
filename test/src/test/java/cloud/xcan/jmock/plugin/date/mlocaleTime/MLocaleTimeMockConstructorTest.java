package cloud.xcan.jmock.plugin.date.mlocaleTime;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocaleTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleTimeMockConstructorTest {

  /**
   * No-parameter MockConstructor: @String()
   */
  @Test
  public void MLocaleTime1() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MLocaleTime2() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime", new String[]{"yy-MM-dd"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MLocaleTime3() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime",
        new String[]{"HH:mm:ss", "Asia/Tokyo", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    Assertions.assertNotNull(mock);
  }


}
