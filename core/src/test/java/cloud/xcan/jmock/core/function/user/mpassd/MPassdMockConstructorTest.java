package cloud.xcan.jmock.core.function.user.mpassd;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MPassd;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MPassdMockConstructorTest {

  static char[] DEFAULT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890`-=[];',./~!@#$%^&\\*()\\_+{}:\"<>?"
      .toCharArray();


  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("MPassd", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(20, mock.getMax());
    Assert.assertEquals(6, mock.getMin());
  }

  @Test
  public void case2() throws Exception {
    FunctionToken token = new FunctionToken("MPassd", new String[]{"1", "2000"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(2000, mock.getMax());
    Assert.assertEquals(1, mock.getMin());
  }

  @Test
  public void case3() throws Exception {
    FunctionToken token = new FunctionToken("MPassd",
        new String[]{"1", "2000", "true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(2000, mock.getMax());
    Assert.assertEquals(1, mock.getMin());
    Assert.assertArrayEquals(DEFAULT_CHARS, mock.getChars());
  }

  @Test
  public void case4() throws Exception {
    FunctionToken token = new FunctionToken("MPassd", new String[]{"true", "true", "true", "true"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MPassd mock = (MPassd) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(20, mock.getMax());
    Assert.assertEquals(6, mock.getMin());
    Assert.assertArrayEquals(DEFAULT_CHARS, mock.getChars());
  }

}
