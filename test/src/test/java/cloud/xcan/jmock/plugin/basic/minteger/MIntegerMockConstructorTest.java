package cloud.xcan.jmock.plugin.basic.minteger;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MInteger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MIntegerMockConstructorTest {

  /**
   * No-parameter MockConstructor: @Integer()
   */
  @Test
  public void MInteger1() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0L, mock.getMin());
    Assertions.assertEquals(Integer.MAX_VALUE, mock.getMax());
  }

  /**
   * parameter MockConstructor: @Integer(Integer min, Integer max)
   */
  @Test
  public void MInteger2() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"1", "10000000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1L, mock.getMin());
    Assertions.assertEquals(10000000L, mock.getMax());
  }


  /**
   * parameter MockConstructor: @Integer(String nullWeight)
   */
  @Test
  public void MInteger3() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0.5, mock.getNullWeight(), 1);
  }

  /**
   * parameter MockConstructor: @Integer(Integer min, Integer max,String nullWeight)
   */
  @Test
  public void MInteger4() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"-1000", "100000", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(-1000, mock.getMin());
    Assertions.assertEquals(100000, mock.getMax());
    Assertions.assertEquals(0.5, mock.getNullWeight(), 1);
  }
}
