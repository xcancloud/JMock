package cloud.xcan.jmock.plugin.basic.mfloat;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MFloat;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MFloatMockTest {

  @Test
  public void case1DefaultTest() throws Exception {
    FunctionToken token = new FunctionToken("Float", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFloat mock = (MFloat) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Float value = mock.mock();
      System.out.println(value);
      Assertions.assertNotNull(value);
      String str = new DecimalFormat("#.##").format(value);
      if (str.indexOf(".") > 0) {
        Assertions.assertTrue(str.length() - str.indexOf(".") - 1 <= 2);
      }
    }
  }

  // * Full MockConstructor: @String(length,min,max,chars,nullWeight)
  @Test
  public void case2RangeSizeTest() throws Exception {
    FunctionToken token = new FunctionToken("Float",
        new String[]{"-10000000F", "10000000F"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFloat mock = (MFloat) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Float value = mock.mock();
      Assertions.assertNotNull(value);
      System.out.println(value);
      Assertions.assertTrue(value >= -10000000F && value <= 10000000F);
    }
  }

  @Test
  public void case3FixScaleTest() throws Exception {
    FunctionToken token = new FunctionToken("Float", new String[]{"5"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFloat mock = (MFloat) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      Float value = mock.mock();
      Assertions.assertNotNull(value);
      System.out.println(value);
      String str = new DecimalFormat("#.#####").format(value);
      if (str.indexOf(".") > 0) {
        Assertions.assertTrue(str.length() - str.indexOf(".") - 1 <= 5);
      }
    }
  }

  @Test
  public void case4RangeSizeAndFixScaleTest() throws Exception {
    FunctionToken token = new FunctionToken("Float", new String[]{"100F", "1000F", "3"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFloat mock = (MFloat) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Float value = mock.mock();
      Assertions.assertNotNull(value);
      System.out.println(value);
      String str = new DecimalFormat("#.###").format(value);
      if (str.indexOf(".") > 0) {
        Assertions.assertTrue(str.length() - str.indexOf(".") - 1 <= 3);
      }
      Assertions.assertTrue(value >= 100F && value <= 1000F);
    }
  }

  @Test
  public void case5_fixLengthAndNullTest() throws Exception {
    FunctionToken token = new FunctionToken("Float", new String[]{"1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFloat mock = (MFloat) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      Float value = mock.mock();
      System.out.println(value);
      if (null == value) {
        hasNull = true;
      }
    }
    Assertions.assertTrue(hasNull);
  }

  @Test
  public void case6_rangeLengthAndNullAndCustomCharsTest() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Float",
        new String[]{"100.900F", "10000000.456F", "3", "1:3"});
    MFloat mock = (MFloat) parser.parse(token);
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      Float value = mock.mock();
      System.out.println(value);
      if (null == value) {
        hasNull = true;
      } else {
        String str = new DecimalFormat("#.###").format(value);
        if (str.indexOf(".") > 0) {
          Assertions.assertTrue(str.length() - str.indexOf(".") - 1 <= 3);
        }
        Assertions.assertTrue(value >= 100.900F && value <= 10000000.456F);
      }
    }
    Assertions.assertTrue(hasNull);
  }
}
