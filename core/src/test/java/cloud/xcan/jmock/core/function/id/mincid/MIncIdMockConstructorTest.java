package cloud.xcan.jmock.core.function.id.mincid;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.id.MIncId;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MIncIdMockConstructorTest {


  @Test
  public void MIncId1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("IncId",
        new String[]{});
    MIncId mock = (MIncId) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(1, mock.getStep());
    Assertions.assertEquals(1, mock.getLongAdder().intValue());
  }

  @Test
  public void MIncId2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("IncId",
        new String[]{"2", "3"});
    MIncId mock = (MIncId) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(3, mock.getStep());
    Assertions.assertEquals(2, mock.getLongAdder().intValue());
  }

}
