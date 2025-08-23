package cloud.xcan.jmock.plugin.geography.maddress;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MAddress;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAddressMockConstructorTest {

  @Test
  public void MAddress1() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    Assertions.assertNotNull(mock);
  }


  @Test
  public void MAddress2() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{"北京海淀|日本东京"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertArrayEquals(new String[]{"北京海淀", "日本东京"}, mock.getDictArray());
  }

}
