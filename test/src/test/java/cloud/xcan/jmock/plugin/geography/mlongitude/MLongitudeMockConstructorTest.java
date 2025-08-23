package cloud.xcan.jmock.plugin.geography.mlongitude;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLongitude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLongitudeMockConstructorTest {


  @Test
  public void MLongitude1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Longitude",
        new String[]{});
    MLongitude mock = (MLongitude) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-180, mock.getMinLng());
    Assertions.assertEquals(180, mock.getMaxLng());
    Assertions.assertEquals("0.000000", mock.getScalePattern());
    Assertions.assertEquals(0.0D, mock.getNullWeight());
  }

  @Test
  public void MLongitude2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Longitude",
        new String[]{"-176D", "60D", "3", "2:8"});
    MLongitude mock = (MLongitude) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-176, mock.getMinLng());
    Assertions.assertEquals(60, mock.getMaxLng());
    Assertions.assertEquals("0.000", mock.getScalePattern());
    Assertions.assertEquals(0.2D, mock.getNullWeight());
  }

}
