package cloud.xcan.comp.jmock.core.function.basic.mdouble;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MDoubleExceptionTest {

  @BeforeAll
  public static void setLocale() {
    Locale.setDefault(Locale.CHINA);
  }

  @Test
  public void minValueTest() {
    FunctionToken token = new FunctionToken("Double", new String[]{"-1D", "10D"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("参数min不能小于0", e.getCause().getMessage());
    }
  }

  @Test
  public void maxValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Double", new String[]{"1D", "-1D"});
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("参数max不能小于0", e.getCause().getMessage());
    }
  }

  @Test
  public void minAndMaxValueTest() {
    FunctionToken token = new FunctionToken("Double", new String[]{"10D", "9D"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("参数min不能大于max", e.getCause().getMessage());
    }
  }

  @Test
  public void nullWeightValueTest() {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    FunctionToken token = new FunctionToken("Double", new String[]{"a"});
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals("参数nullWeight不是一个有效的权重值", e.getCause().getMessage());
    }
  }

}
