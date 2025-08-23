package cloud.xcan.jmock.plugin.basic.mstring;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MString;
import org.junit.jupiter.api.Assertions;
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
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(MString.DEFAULT_LENGTH), mock.getLength());
    Assertions.assertEquals(MString.DEFAULT_LENGTH, mock.getFixedLength());
  }

  /**
   * Basic MockConstructor:  @String(length)
   */
  @Test
  public void MString2() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(8), mock.getLength());
    Assertions.assertEquals(8, mock.getFixedLength());
  }

  /**
   * Basic MockConstructor:  @String(length,nullWeight)
   */
  @Test
  public void MString3() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"10", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(10), mock.getLength());
    Assertions.assertEquals(10, mock.getFixedLength());
    Assertions.assertEquals(0.1d, mock.getNullWeight(), 2);
  }

  /**
   * Basic MockConstructor:  @String(length,nullWeight,chars)
   */
  @Test
  public void MString4() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"10", "1:9", "中英法德美"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(10), mock.getLength());
    Assertions.assertEquals(10, mock.getFixedLength());
    Assertions.assertEquals(0.1d, mock.getNullWeight(), 2);
    Assertions.assertEquals("中英法德美", new String(mock.getChars()));
  }

  /**
   * Basic MockConstructor:  @String(min,max)
   */
  @Test
  public void MString5() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"0", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNull(mock.getLength());
    Assertions.assertEquals(0, mock.getFixedLength());
    Assertions.assertEquals(Integer.valueOf(0), mock.getMin());
    Assertions.assertEquals(Integer.valueOf(10), mock.getMax());
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
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(MString.DEFAULT_LENGTH), mock.getLength());
    Assertions.assertEquals(MString.DEFAULT_LENGTH, mock.getFixedLength());
    Assertions.assertNull(mock.getMin());
    Assertions.assertNull(mock.getMax());

    token = new FunctionToken("String", new String[]{"8", null, null, "", "1:9"});
    mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(8), mock.getLength());
    Assertions.assertEquals(8, mock.getFixedLength());
    Assertions.assertNull(mock.getMin());
    Assertions.assertNull(mock.getMax());

    token = new FunctionToken("String", new String[]{"8", "11", "12", "", "1:9"});
    mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(8), mock.getLength());
    Assertions.assertEquals(8, mock.getFixedLength());
    Assertions.assertNull(mock.getMin());
    Assertions.assertNull(mock.getMax());

    token = new FunctionToken("String", new String[]{null, null, null, "", "1:9"});
    mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(MString.DEFAULT_LENGTH), mock.getLength());
    Assertions.assertEquals(MString.DEFAULT_LENGTH, mock.getFixedLength());
    Assertions.assertNull(mock.getMin());
    Assertions.assertNull(mock.getMax());
  }
}
