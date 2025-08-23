package cloud.xcan.jmock.plugin.geography.mcountry;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.GeographyDocMessage;
import cloud.xcan.jmock.plugin.MCountry;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCountryMockTest {

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("Country", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCountry mock = (MCountry) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      String countryResources = MessageResources.getString(GeographyDocMessage.DATA_COUNTRY,
          Locale.CHINA);
      String country = new String(countryResources.getBytes(StandardCharsets.ISO_8859_1),
          StandardCharsets.UTF_8);
      Assertions.assertTrue(country.contains(str));
    }
  }

  @Test
  public void case2_localTest() throws Exception {
    FunctionToken token = new FunctionToken("Country",
        new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCountry mock = (MCountry) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      String countryResources = MessageResources.getString(GeographyDocMessage.DATA_COUNTRY,
          Locale.ENGLISH);
      String country = new String(countryResources.getBytes(StandardCharsets.ISO_8859_1),
          StandardCharsets.UTF_8);
      Assertions.assertTrue(country.contains(str));
    }
  }

  @Test
  public void case3_dictTest() throws Exception {
    String dict = "a,我的,usa";
    FunctionToken token = new FunctionToken("Country",
        new String[]{dict});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCountry mock = (MCountry) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertTrue(dict.contains(str));
    }
  }


}
