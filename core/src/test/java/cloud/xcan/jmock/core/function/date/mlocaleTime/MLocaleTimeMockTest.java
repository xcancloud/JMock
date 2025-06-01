
package cloud.xcan.jmock.core.function.date.mlocaleTime;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.date.MLocaleTime;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MLocaleTimeMockTest {

  @Test
  public void case1_fixedLengthTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime",
        new String[]{"a HH:mm:ss"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case4_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime",
        new String[]{"HH H hh h mm m ss s SS S a"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleTime",
        new String[]{"HH:mm:ss","Asia/Ho_Chi_Minh", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleTime mock = (MLocaleTime) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }
}
