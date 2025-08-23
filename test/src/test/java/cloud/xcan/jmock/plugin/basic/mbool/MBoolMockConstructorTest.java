package cloud.xcan.jmock.plugin.basic.mbool;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MBool;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MBoolMockConstructorTest {

  /**
   * No-parameter MockConstructor: @Bool()
   */
  @Test
  public void MString1() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0.0, mock.getTrueWeight(), 1);
    Assertions.assertEquals(0.0, mock.getNullWeight(), 1);
  }

  /**
   * Basic MockConstructor:  @Bool(length)
   */
  @Test
  public void MString2() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0.5, mock.getTrueWeight(), 1);
  }

  /**
   * Basic MockConstructor:  @Bool(String trueWeight, String nullWeight)
   */
  @Test
  public void MString3() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0.5, mock.getTrueWeight(), 1);
    Assertions.assertEquals(0.5, mock.getNullWeight(), 1);
  }

  /**
   * Basic MockConstructor:  @Bool(String trueWeight, String nullWeight, String dict)
   */
  @Test
  public void MString4() throws Exception {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:4", "是|否"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MBool mock = (MBool) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(0.5, mock.getTrueWeight(), 1);
    Assertions.assertEquals(0.2, mock.getNullWeight(), 1);
    Assertions.assertEquals(Arrays.asList("是", "否"), mock.getDictArray());
  }


}
