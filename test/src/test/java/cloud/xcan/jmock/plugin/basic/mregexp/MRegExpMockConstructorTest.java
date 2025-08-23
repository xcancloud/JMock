package cloud.xcan.jmock.plugin.basic.mregexp;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MRegExp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MRegExpMockConstructorTest {


  @Test
  public void MRegExp() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNull(mock.getRegexp());

  }

  @Test
  public void MRegExp_1() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{"[0-9]"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("[0-9]", mock.getRegexp());
  }

  @Test
  public void MRegExp_2() throws Exception {
    FunctionToken token = new FunctionToken("MRegExp", new String[]{"[0-9]", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MRegExp mock = (MRegExp) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals("[0-9]", mock.getRegexp());
    Assertions.assertEquals(0.5, mock.getNullWeight(), 1);
  }

}
