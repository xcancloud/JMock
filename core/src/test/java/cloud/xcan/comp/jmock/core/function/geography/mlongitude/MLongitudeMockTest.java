package cloud.xcan.comp.jmock.core.function.geography.mlongitude;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.geography.MLongitude;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLongitudeMockTest {

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("Longitude", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLongitude mock = (MLongitude) parser.parse(token);
    for (int i = 0; i < 100; i++) {
      String str = mock.mock();
      Assertions.assertTrue(
          mock.getMinLng() < Double.parseDouble(str) && Double.parseDouble(str) < mock.getMaxLng());
    }
  }
}
