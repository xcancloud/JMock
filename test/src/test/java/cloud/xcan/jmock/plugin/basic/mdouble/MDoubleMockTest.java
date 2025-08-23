package cloud.xcan.jmock.plugin.basic.mdouble;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MDouble;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MDoubleMockTest {

  // * Basic MockConstructor: @Double(nullWeight)
  @Test
  public void case1_nullWeightTest() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 10; i++) {
      Double num = mock.mock();
      if (null == num) {
        hasNull = true;
      }
    }
//    Assertions.assertTrue(hasNull);
  }

  // * Basic MockConstructor: @Double(min,max,scale)
  @Test
  public void case2_rangeAndScaleTest() throws Exception {
    FunctionToken token = new FunctionToken("Double",
        new String[]{"5D", "10D", "3"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Double num = mock.mock();
      Assertions.assertTrue(BigDecimal.valueOf(num).scale() <= 3 && num < 10D && num > 5D);
    }
  }

  @Test
  public void case3_rangeAndScaleAndNullTest() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"5D", "10D", "4", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      Double num = mock.mock();
      if (null == num) {
        hasNull = true;
      } else {
        Assertions.assertTrue(num < 10D && num > 5D);
      }
    }

    Assertions.assertTrue(hasNull);
  }
}
