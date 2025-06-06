package cloud.xcan.jmock.core.function.user.mname;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MName;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MNameMockConstructorTest {


  @Test
  public void MFirstname() throws Exception {
    FunctionToken token = new FunctionToken("MName", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MName mock = (MName) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

  @Test
  public void MFirstname_1() throws Exception {
    FunctionToken token = new FunctionToken("MName", new String[]{"cn"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MName mock = (MName) parser.parse(token);
    Assertions.assertNotNull(mock);
  }

}
