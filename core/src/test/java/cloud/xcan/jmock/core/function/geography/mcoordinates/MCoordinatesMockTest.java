
package cloud.xcan.jmock.core.function.geography.mcoordinates;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MCoordinates;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MCoordinatesMockTest {

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("Coordinates", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MCoordinates mock = (MCoordinates) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertEquals(2, str.split(",").length);
    }
  }


}
