package cloud.xcan.comp.jmock.core.function.geography.maddress;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.geography.MAddress;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MAddressMockTest {

  /**
   * No-parameter MockConstructor: @Address()
   */
  @Test
  public void MAddress1() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

  @Test
  public void MAddress2() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

  @Test
  public void MAddress3() throws Exception {
    FunctionToken token = new FunctionToken("Address", new String[]{"123|4537"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAddress mock = (MAddress) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

}
