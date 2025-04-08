package cloud.xcan.jmock.core.function.basic.minteger;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MInteger;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
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
    Assert.assertNotNull(mock);
    Assert.assertEquals(0L, mock.getMin());
    Assert.assertEquals(Integer.MAX_VALUE, mock.getMax());
  }

  /**
   * parameter MockConstructor: @Integer(Integer min, Integer max)
   */
  @Test
  public void MInteger2() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"1", "10000000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(1L, mock.getMin());
    Assert.assertEquals(10000000L, mock.getMax());
  }


  /**
   * parameter MockConstructor: @Integer(String nullWeight)
   */
  @Test
  public void MInteger3() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(0.5, mock.getNullWeight(), 1);
  }

  /**
   * parameter MockConstructor: @Integer(Integer min, Integer max,String nullWeight)
   */
  @Test
  public void MInteger4() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"-1000", "100000", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(-1000, mock.getMin());
    Assert.assertEquals(100000, mock.getMax());
    Assert.assertEquals(0.5, mock.getNullWeight(), 1);
  }
}
