package cloud.xcan.jmock.core.function.basic.mlong;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MLong;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MLongMockConstructorTest {

  /**
   * No-parameter MockConstructor: @Long()
   */
  @Test
  public void MLong1() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0L, mock.getMin());
    Assertions.assertEquals(Long.MAX_VALUE, mock.getMax());
  }


  /**
   * parameter MockConstructor: @Long(Long min, Long max)
   */
  @Test
  public void MLong2() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"1L", "10000000L"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1L, mock.getMin());
    Assertions.assertEquals(10000000L, mock.getMax());
  }


  /**
   * parameter MockConstructor: @Long(String nullWeight)
   */
  @Test
  public void MLong3() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0.5, mock.getNullWeight(), 1);
  }

  /**
   * parameter MockConstructor: @Long(Long min, Long max,String nullWeight)
   */
  @Test
  public void MLong4() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"-1000L", "100000L", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-1000L, mock.getMin());
    Assertions.assertEquals(100000L, mock.getMax());
    Assertions.assertEquals(0.5, mock.getNullWeight(), 1);
  }
}
