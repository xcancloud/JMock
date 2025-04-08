package cloud.xcan.jmock.core.function.geography.mlatitude;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MLatitude;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLatitudeMockConstructorTest {


  @Test
  public void MLatitude1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{});
    MLatitude mock = (MLatitude) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-90, mock.getMinLat());
    Assertions.assertEquals(90, mock.getMaxLat());
    Assertions.assertEquals("0.000000", mock.getScalePattern());
    Assertions.assertEquals(0.0, mock.getNullWeight());
  }

  @Test
  public void MLatitude2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Latitude",
        new String[]{"-80D", "75D", "3", "2:8"});
    MLatitude mock = (MLatitude) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-80, mock.getMinLat());
    Assertions.assertEquals(75, mock.getMaxLat());
    Assertions.assertEquals("0.000", mock.getScalePattern());
    Assertions.assertEquals(0.2, mock.getNullWeight());
  }

}
