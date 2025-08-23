package cloud.xcan.jmock.plugin.locale.mlocale;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLocaleMockConstructorTest {

  /**
   * No-parameter MockConstructor: @Locale()
   */
  @Test
  public void MLocale1() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("_", mock.getJoiner());
  }


  /**
   * Full MockConstructor : @Locale(joiner)
   */
  @Test
  public void MLocale2() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{"-"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("-", mock.getJoiner());
  }

  /**
   * Full MockConstructor : @Locale(joiner)
   */
  @Test
  public void MLocale3() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{"null"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("_", mock.getJoiner());
  }
}
