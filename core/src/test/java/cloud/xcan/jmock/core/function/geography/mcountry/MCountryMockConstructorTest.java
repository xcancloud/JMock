package cloud.xcan.jmock.core.function.geography.mcountry;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MCountry;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCountryMockConstructorTest {


  @Test
  public void MCountry1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Country",
        new String[]{});
    MCountry mock = (MCountry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MCountry2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Country",
        new String[]{"en"});
    MCountry mock = (MCountry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MCountry3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Country",
        new String[]{"等我,耳机,佛山"});
    MCountry mock = (MCountry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MCountry4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Country",
        new String[]{"zh_CN", "等我,耳机,佛山"});
    MCountry mock = (MCountry) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }
}
