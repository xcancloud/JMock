package cloud.xcan.jmock.core.function.basic.mdouble;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MDouble;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MDoubleMockConstructorTest {

  /**
   * No-parameter MockConstructor: @Double()
   */
  @Test
  public void MDouble1() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(MDouble.DEFAULT_SCALE_VALUE, mock.getScale());
    Assert.assertEquals(MDouble.DEFAULT_MAX_VALUE, mock.getMax(), 1);
    Assert.assertEquals(0D, mock.getMin(), 1);
    Assert.assertEquals(0.0D, mock.getNullWeight(), 1);
  }

  /**
   * Basic MockConstructor:  @Double(scale)
   */
  @Test
  public void MDouble2() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"3"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(3, mock.getScale());
    Assert.assertEquals(MDouble.DEFAULT_MAX_VALUE, mock.getMax(), 1);
    Assert.assertEquals(0D, mock.getMin(), 1);
    Assert.assertEquals(0.0D, mock.getNullWeight(), 1);
  }


  /**
   * Basic MockConstructor:  @Double(nullWeight)
   */
  @Test
  public void MDouble3() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(MDouble.DEFAULT_SCALE_VALUE, mock.getScale());
    Assert.assertEquals(MDouble.DEFAULT_MAX_VALUE, mock.getMax(), 1);
    Assert.assertEquals(0, mock.getMin(), 1);
    Assert.assertEquals(0.1D, mock.getNullWeight(), 1);
  }

  /**
   * Basic MockConstructor:  @Double(min,max)
   */
  @Test
  public void MDouble4() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"200D", "300D"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(MDouble.DEFAULT_SCALE_VALUE, mock.getScale());
    Assert.assertEquals(300D, mock.getMax(), 1);
    Assert.assertEquals(200D, mock.getMin(), 1);
    Assert.assertEquals(0.0D, mock.getNullWeight(), 1);
  }

  /**
   * Basic MockConstructor:  @Double(min,max,scale)
   */
  @Test
  public void MDouble5() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"100D", "200D", "5"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(5, mock.getScale());
    Assert.assertEquals(200D, mock.getMax(), 1);
    Assert.assertEquals(100D, mock.getMin(), 1);
    Assert.assertEquals(0.0D, mock.getNullWeight(), 1);
  }


  /**
   * Full MockConstructor:  @Double(min,max,scale,nullWeight)
   */
  @Test
  public void MDouble6() throws Exception {
    FunctionToken token = new FunctionToken("Double", new String[]{"100D", "200D", "5", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MDouble mock = (MDouble) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(5, mock.getScale());
    Assert.assertEquals(200D, mock.getMax(), 1);
    Assert.assertEquals(100D, mock.getMin(), 1);
    Assert.assertEquals(0.1D, mock.getNullWeight(), 1);
  }
}
