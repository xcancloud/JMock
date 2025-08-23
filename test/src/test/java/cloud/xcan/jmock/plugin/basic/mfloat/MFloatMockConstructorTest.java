package cloud.xcan.jmock.plugin.basic.mfloat;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MFloat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MFloatMockConstructorTest {

  @Test
  public void MFloat1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{});
    MFloat mock = (MFloat) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0f, mock.getMin(), 0);
    Assertions.assertEquals(Float.MAX_VALUE, mock.getMax(), 0);
    Assertions.assertEquals(2, mock.getScale());
    Assertions.assertEquals(1D, mock.getNullWeight(), 1);
  }

  @Test
  public void MFloat2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"1:1"});
    MFloat mock = (MFloat) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0, mock.getMin(), 0);
    Assertions.assertEquals(Float.MAX_VALUE, mock.getMax(), 0);
    Assertions.assertEquals(2, mock.getScale());
    Assertions.assertEquals(1D, mock.getNullWeight(), 1);
  }

  @Test
  public void MFloat3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"1f", "6f", "5"});
    MFloat mock = (MFloat) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1, mock.getMin(), 0);
    Assertions.assertEquals(6, mock.getMax(), 0);
    Assertions.assertEquals(5, mock.getScale());
    Assertions.assertEquals(1D, mock.getNullWeight(), 1);
  }

  @Test
  public void MFloat4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken extractor = new FunctionToken("Float", new String[]{"5"});
    MFloat mock = (MFloat) parser.parse(extractor);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0, mock.getMin(), 0);
    Assertions.assertEquals(Float.MAX_VALUE, mock.getMax(), 0);
    Assertions.assertEquals(5, mock.getScale());
    Assertions.assertEquals(0.5D, mock.getNullWeight(), 1);
  }

  @Test
  public void MFloat5() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Float",
        new String[]{"1:8"});
    MFloat mock = (MFloat) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0, mock.getMin(), 0);
    Assertions.assertEquals(Float.MAX_VALUE, mock.getMax(), 0);
    Assertions.assertEquals(2, mock.getScale());
    Assertions.assertEquals(0.2D, mock.getNullWeight(), 1);
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
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(104, mock.getMin(), 0);
    Assertions.assertEquals(10000, mock.getMax(), 0);
    Assertions.assertEquals(2, mock.getScale());
    Assertions.assertEquals(0.1D, mock.getNullWeight(), 1);
  }
}
