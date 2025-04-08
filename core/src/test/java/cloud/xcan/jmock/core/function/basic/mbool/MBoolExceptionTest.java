package cloud.xcan.jmock.core.function.basic.mbool;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MBoolExceptionTest {

  @BeforeAll
  public static void setLocale() {
    Locale.setDefault(Locale.CHINA);
  }

  @Test
  public void exceptionTest_1() {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      System.out.println("e = " + e.getCause().getMessage());
      assertEquals("参数trueWeight不是一个有效的值", e.getCause().getMessage());
    }
  }

  @Test
  public void exceptionTest_2() {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      System.out.println("e = " + e.getCause().getMessage());
      assertEquals("参数nullWeight不是一个有效的权重值", e.getCause().getMessage());
    }
  }

  @Test
  public void exceptionTest_3() {
    FunctionToken token = new FunctionToken("MBool", new String[]{"1:1", "1:1", "1:0:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      System.out.println("e = " + e.getCause().getMessage());
      assertEquals("参数dict不是一个有效的值", e.getCause().getMessage());
    }
  }
}
