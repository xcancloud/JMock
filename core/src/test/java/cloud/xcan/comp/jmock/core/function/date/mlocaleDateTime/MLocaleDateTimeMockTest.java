
package cloud.xcan.comp.jmock.core.function.date.mlocaleDateTime;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.date.MLocaleDateTime;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MLocaleDateTimeMockTest {

  @Test
  public void case1_fixedLengthTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime",
        new String[]{"yy-MM-dd a HH:mm:ss"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case4_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime",
        new String[]{"yyyy yy y MM M dd d HH H hh h mm m ss s SS S a"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime",
        new String[]{"yy-MM-dd HH:mm:ss", "Asia/Shanghai"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case5() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDateTime",
        new String[]{"yy-MM-dd HH:mm:ss", "America/Argentina/Buenos_Aires"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDateTime mock = (MLocaleDateTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }
}
