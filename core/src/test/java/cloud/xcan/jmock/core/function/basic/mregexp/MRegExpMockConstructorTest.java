package cloud.xcan.jmock.core.function.basic.mregexp;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MRegExp;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MRegExpMockConstructorTest {


  @Test
  public void MRegExp() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertNull(mock.getRegexp());

  }

  @Test
  public void MRegExp_1() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{"[0-9]"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals("[0-9]", mock.getRegexp());
  }

  @Test
  public void MRegExp_2() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{"[0-9]", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals("[0-9]", mock.getRegexp());
    Assert.assertEquals(0.5, mock.getNullWeight(), 1);
  }

}
