package cloud.xcan.comp.jmock.core.function.basic.mstring;


import static cloud.xcan.comp.jmock.core.function.basic.MString.DEFAULT_LENGTH;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.basic.MString;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MStringMockConstructorTest {

  /**
   * No-parameter MockConstructor: @String()
   */
  @Test
  public void MString1() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(DEFAULT_LENGTH), mock.getLength());
    Assert.assertEquals(DEFAULT_LENGTH, mock.getFixedLength());
  }

  /**
   * Basic MockConstructor:  @String(length)
   */
  @Test
  public void MString2() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(8), mock.getLength());
    Assert.assertEquals(8, mock.getFixedLength());
  }

  /**
   * Basic MockConstructor:  @String(length,nullWeight)
   */
  @Test
  public void MString3() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"10", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(10), mock.getLength());
    Assert.assertEquals(10, mock.getFixedLength());
    Assert.assertEquals(0.1d, mock.getNullWeight(), 2);
  }

  /**
   * Basic MockConstructor:  @String(length,nullWeight,chars)
   */
  @Test
  public void MString4() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"10", "1:9", "中英法德美"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(10), mock.getLength());
    Assert.assertEquals(10, mock.getFixedLength());
    Assert.assertEquals(0.1d, mock.getNullWeight(), 2);
    Assert.assertEquals("中英法德美", new String(mock.getChars()));
  }

  /**
   * Basic MockConstructor:  @String(min,max)
   */
  @Test
  public void MString5() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"0", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertNull(mock.getLength());
    Assert.assertEquals(0, mock.getFixedLength());
    Assert.assertEquals(Integer.valueOf(0), mock.getMin());
    Assert.assertEquals(Integer.valueOf(10), mock.getMax());
  }

  /**
   * Full MockConstructor: @String(length,min,max,chars,nullWeight)
   */
  @Test
  public void MString7() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("String",
        new String[]{null, null, null, null, null});
    MString mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(DEFAULT_LENGTH), mock.getLength());
    Assert.assertEquals(DEFAULT_LENGTH, mock.getFixedLength());
    Assert.assertNull(mock.getMin());
    Assert.assertNull(mock.getMax());

    token = new FunctionToken("String", new String[]{"8", null, null, "", "1:9"});
    mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(8), mock.getLength());
    Assert.assertEquals(8, mock.getFixedLength());
    Assert.assertNull(mock.getMin());
    Assert.assertNull(mock.getMax());

    token = new FunctionToken("String", new String[]{"8", "11", "12", "", "1:9"});
    mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(8), mock.getLength());
    Assert.assertEquals(8, mock.getFixedLength());
    Assert.assertNull(mock.getMin());
    Assert.assertNull(mock.getMax());

    token = new FunctionToken("String", new String[]{null, null, null, "", "1:9"});
    mock = (MString) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(Integer.valueOf(DEFAULT_LENGTH), mock.getLength());
    Assert.assertEquals(DEFAULT_LENGTH, mock.getFixedLength());
    Assert.assertNull(mock.getMin());
    Assert.assertNull(mock.getMax());
  }
}
