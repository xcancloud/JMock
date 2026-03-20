package cloud.xcan.jmock.plugin.geography.mlatitude;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLatitude;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLatitudeMockTest {

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("Latitude", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLatitude mock = (MLatitude) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      double val = Double.parseDouble(str);
      Assertions.assertTrue(
          mock.getMinLat() < val && val < mock.getMaxLat());
    }
  }

  @Test
  public void case2_nullWeightTest() throws Exception {
    FunctionToken token = new FunctionToken("Latitude", new String[]{"2:8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLatitude mock = (MLatitude) parser.parse(token);
    boolean hasNull = false;
    boolean hasValue = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (str == null) {
        hasNull = true;
      } else {
        hasValue = true;
        double val = Double.parseDouble(str);
        Assertions.assertTrue(
            mock.getMinLat() < val && val < mock.getMaxLat(),
            "Latitude out of range: " + val);
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null values with 2:8 nullWeight");
    Assertions.assertTrue(hasValue, "Should produce non-null values too");
  }
}
