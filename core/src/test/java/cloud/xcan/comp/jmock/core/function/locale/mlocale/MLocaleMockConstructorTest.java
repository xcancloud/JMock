package cloud.xcan.comp.jmock.core.function.locale.mlocale;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.locale.MLocale;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
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
    Assert.assertNotNull(mock);
    Assert.assertEquals("_", mock.getJoiner());
  }


  /**
   * Full MockConstructor : @Locale(joiner)
   */
  @Test
  public void MLocale2() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{"-"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals("-", mock.getJoiner());
  }

  /**
   * Full MockConstructor : @Locale(joiner)
   */
  @Test
  public void MLocale3() throws Exception {
    FunctionToken token = new FunctionToken("Locale", new String[]{"null"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocale mock = (MLocale) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals("_", mock.getJoiner());
  }
}
