package cloud.xcan.jmock.core.function.user.mfirstname;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MFirstname;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MFirstNameMockConstructorTest {

  @Test
  public void MFirstname() throws Exception {
    FunctionToken token = new FunctionToken("MFirstname", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFirstname mock = (MFirstname) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertNotNull(mock.mock());
  }

  @Test
  public void MFirstname_1() throws Exception {
    FunctionToken token = new FunctionToken("MFirstname", new String[]{"James|Jordon"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFirstname mock = (MFirstname) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(List.of("James", "Jordon").contains(mock.mock()));
  }

  @Test
  public void MFirstname2() throws Exception {
    FunctionToken token = new FunctionToken("MFirstname", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MFirstname mock = (MFirstname) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertNotNull(mock.mock());
  }

}
