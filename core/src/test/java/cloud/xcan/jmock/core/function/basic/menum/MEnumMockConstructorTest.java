package cloud.xcan.jmock.core.function.basic.menum;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.basic.MEnum;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MEnumMockConstructorTest {


  @Test
  public void MEnum_1() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    Assert.assertNotNull(mock);
  }

  @Test
  public void MEnum_2() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{"DocGenerator|B"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    Assert.assertNotNull(mock);
  }


  @Test
  public void MEnum_3() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{"DocGenerator|B", "1:2"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    Assert.assertNotNull(mock);
  }
}
