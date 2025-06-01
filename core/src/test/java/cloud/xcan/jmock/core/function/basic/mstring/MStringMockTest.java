package cloud.xcan.jmock.core.function.basic.mstring;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MString;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MStringMockTest {

  @Test
  public void case1_fixedLengthTest() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertEquals(6, str.length());
    }
  }

  // * Full MockConstructor: @String(length,min,max,chars,nullWeight)
  @Test
  public void case2_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("String",
        new String[]{null, "5", "10", "123456ABCDEF", null});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("value:\t" + str);
      Assertions.assertTrue(str.length() >= 5 && str.length() <= 10);
    }
  }

  @Test
  public void case3_rangeLengthAndNullTest() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{null, "5", "10", null, "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (null == str) {
        hasNull = true;
      } else {
        Assertions.assertTrue(str.length() >= 5 && str.length() <= 10);
      }
    }
    Assertions.assertTrue(hasNull);
  }

  @Test
  public void case4_fixedLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"5", null, "123456ABCDEF"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      Assertions.assertEquals(5, str.length());
    }
  }

  @Test
  public void case5_fixLengthAndNullTest() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"5", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (null == str) {
        hasNull = true;
      } else {
        Assertions.assertEquals(5, str.length());
      }
    }
    Assertions.assertTrue(hasNull);
  }

  @Test
  public void case6_rangeLengthAndNullAndCustomCharsTest() throws Exception {
    // SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser<>();

    // SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("String", new String[]{null, "5", "5", "AAA", "1:9"});
    MString mock = (MString) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (null == str) {
        hasNull = true;
      } else {
        Assertions.assertEquals("AAAAA", str);
      }
    }
    Assertions.assertTrue(hasNull);
    token = new FunctionToken("String", new String[]{null, "1", "5", "AAA", "1:9"});
    mock = (MString) parser.parse(token);
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (null != str) {
        Assertions.assertTrue(str.length() >= 1 && str.length() <= 5);
      }
    }
  }

  @Test
  public void case7_rangeLengthTest() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"5", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      Assertions.assertTrue(str.length() >= 5 && str.length() <= 10);
    }
    token = new FunctionToken("String", new String[]{"null", "10"});
    mock = (MString) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(Integer.valueOf(0), mock.getMin());
    Assertions.assertEquals(Integer.valueOf(10), mock.getMax());
  }

  @Test
  public void case8_fixLengthAndNullAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("String", new String[]{"5", "1:9", "AAA"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MString mock = (MString) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (null == str) {
        hasNull = true;
      } else {
        Assertions.assertEquals("AAAAA", str);
      }
    }
    Assertions.assertTrue(hasNull);
  }
}
