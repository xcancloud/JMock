package cloud.xcan.jmock.plugin.geography.mcoordinates;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MCoordinates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCoordinatesMockTest {

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("Coordinates", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCoordinates mock = (MCoordinates) parser.parse(token);
    for (int i = 0; i < 20; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      String[] parts = str.split(",");
      Assertions.assertEquals(2, parts.length, "Coordinates should have lat,lng format");
      double lat = Double.parseDouble(parts[0].trim());
      double lng = Double.parseDouble(parts[1].trim());
      Assertions.assertTrue(lat >= -90 && lat <= 90, "Latitude out of range: " + lat);
      Assertions.assertTrue(lng >= -180 && lng <= 180, "Longitude out of range: " + lng);
    }
  }

  @Test
  public void case2_nullWeightTest() throws Exception {
    FunctionToken token = new FunctionToken("Coordinates", new String[]{"2:8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCoordinates mock = (MCoordinates) parser.parse(token);
    boolean hasNull = false;
    boolean hasValue = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (str == null) {
        hasNull = true;
      } else {
        hasValue = true;
        String[] parts = str.split(",");
        Assertions.assertEquals(2, parts.length);
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 2:8 nullWeight");
    Assertions.assertTrue(hasValue, "Should produce non-null values too");
  }
}
