package cloud.xcan.jmock.core.function.geography.mlatitude;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.geography.MLatitude;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
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
      Assertions.assertTrue(
          mock.getMinLat() < Double.parseDouble(str) && Double.parseDouble(str) < mock.getMaxLat());
    }
  }
}
