package cloud.xcan.jmock.plugin.user.mpassd;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MPassword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MPasswordMockConstructorTest {

  static char[] DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890`-=[];',./~!@#$%^&\\*()\\_+{}:\"<>?"
      .toCharArray();


  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("MPassword", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(20, mock.getMax());
    Assertions.assertEquals(6, mock.getMin());
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MPassword", new String[]{"1", "2000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(2000, mock.getMax());
    Assertions.assertEquals(1, mock.getMin());
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MPassword",
        new String[]{"1", "2000", "true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(2000, mock.getMax());
    Assertions.assertEquals(1, mock.getMin());
    Assertions.assertArrayEquals(DEFAULT_CHARS, mock.getChars());
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MPassword",
        new String[]{"true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassword mock = (MPassword) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(20, mock.getMax());
    Assertions.assertEquals(6, mock.getMin());
    Assertions.assertArrayEquals(DEFAULT_CHARS, mock.getChars());
  }

}
