package cloud.xcan.jmock.plugin.date.mlocaledate;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLocaleDate;
import org.junit.jupiter.api.Test;

public class MLocaleDateMockTest {

  @Test
  public void case1_fixedLengthTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case2_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate",
        new String[]{"yy-MM-dd"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case4_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate",
        new String[]{"yyyy yy y MM M dd d"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }

  @Test
  public void case3_rangeLengthAndCustomCharsTest() throws Exception {
    FunctionToken token = new FunctionToken("MLocaleDate",
        new String[]{"yy-MM-dd", "Asia/Shanghai", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLocaleDate mock = (MLocaleDate) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println("str = " + str);
    }
  }
}
