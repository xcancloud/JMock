package cloud.xcan.comp.jmock.core.function.geography.maddress;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.geography.MAddress;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MAddressMockConstructorTest {

  @Test
  public void MAddress1() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    Assert.assertNotNull(mock);
  }


  @Test
  public void MAddress2() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{"北京海淀|日本东京"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(new String[]{"北京海淀", "日本东京"}, mock.getDictArray());
  }

}
