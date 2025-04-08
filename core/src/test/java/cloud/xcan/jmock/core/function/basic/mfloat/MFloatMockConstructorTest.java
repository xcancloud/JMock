package cloud.xcan.jmock.core.function.basic.mfloat;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MFloat;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MFloatMockConstructorTest {

  @Test
  public void MFloat1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{});
    MFloat mock = (MFloat) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(0f, mock.getMin(), 0);
    Assert.assertEquals(Float.MAX_VALUE, mock.getMax(), 0);
    Assert.assertEquals(2, mock.getScale());
    Assert.assertEquals(1D, mock.getNullWeight(), 1);
  }

  @Test
  public void MFloat2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"1:1"});
    MFloat mock = (MFloat) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(0, mock.getMin(), 0);
    Assert.assertEquals(Float.MAX_VALUE, mock.getMax(), 0);
    Assert.assertEquals(2, mock.getScale());
    Assert.assertEquals(1D, mock.getNullWeight(), 1);
  }

  @Test
  public void MFloat3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"1f", "6f", "5"});
    MFloat mock = (MFloat) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(1, mock.getMin(), 0);
    Assert.assertEquals(6, mock.getMax(), 0);
    Assert.assertEquals(5, mock.getScale());
    Assert.assertEquals(1D, mock.getNullWeight(), 1);
  }

    @Test
  public void MFloat4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken extractor = new FunctionToken("Float", new String[]{"5"});
    MFloat mock = (MFloat) parser.parse(extractor);
    Assert.assertNotNull(mock);
    Assert.assertEquals(0, mock.getMin(),0);
    Assert.assertEquals(Float.MAX_VALUE, mock.getMax(),0);
    Assert.assertEquals(5, mock.getScale());
    Assert.assertEquals(0.5D, mock.getNullWeight(), 1);
  }
  @Test
  public void MFloat5() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"1:8"});
    MFloat mock = (MFloat) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(0, mock.getMin(),0);
    Assert.assertEquals(Float.MAX_VALUE, mock.getMax(),0);
    Assert.assertEquals(2, mock.getScale());
    Assert.assertEquals(0.2D, mock.getNullWeight(), 1);
  }

  /**
   * Full MockConstructor: @Float(min,max,scale,nullWeight)
   */
  @Test
  public void MFloat6() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"104f", "10000f", "2", "1:9"});
    MFloat mock = (MFloat) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(104, mock.getMin(),0);
    Assert.assertEquals(10000, mock.getMax(),0);
    Assert.assertEquals(2, mock.getScale());
    Assert.assertEquals(0.1D, mock.getNullWeight(), 1);
  }
}
