
package cloud.xcan.jmock.core.function.geography.mcoordinates;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MCoordinates;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCoordinatesMockConstructorTest {


  @Test
  public void MCoordinates1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{});
    MCoordinates mock = (MCoordinates)parser.parse(token);
    Assert.assertNotNull(mock);
    Assertions.assertEquals(-180, mock.getMinLng());
    Assertions.assertEquals(180, mock.getMaxLng());
    Assertions.assertEquals(-90, mock.getMinLat());
    Assertions.assertEquals(90, mock.getMaxLat());
    Assertions.assertEquals("0.000000", mock.getScalePattern());
    Assertions.assertEquals(0.0, mock.getNullWeight());
  }

  /**
   * Full MockConstructor: @MCoordinates(minLng,maxLng,minLat,maxLat,scale,nullWeight)
   */
  @Test
  public void MCoordinates2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"-170D", "150D", "-80D", "70D", "6", "2:8"});
    MCoordinates mock = (MCoordinates) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-170, mock.getMinLng());
    Assertions.assertEquals(150, mock.getMaxLng());
    Assertions.assertEquals(-80, mock.getMinLat());
    Assertions.assertEquals(70, mock.getMaxLat());
    Assertions.assertEquals("0.000000", mock.getScalePattern());
    Assertions.assertEquals(0.2, mock.getNullWeight());
  }
}
