package cloud.xcan.jmock.plugin.basic.mlong;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MLong;
import org.junit.jupiter.api.Test;

public class MLongMockTest {

  /**
   * No-parameter MockConstructor: @Long()
   */
  @Test
  public void MLong1() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }


  /**
   * parameter MockConstructor: @Long(Long min, Long max)
   */
  @Test
  public void MLong2() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"-100L", "100L"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }


  /**
   * parameter MockConstructor: @Long(String nullWeight)
   */
  @Test
  public void MLong3() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

  /**
   * parameter MockConstructor: @Long(Long min, Long max,String nullWeight)
   */
  @Test
  public void MLong4() throws Exception {
    FunctionToken token = new FunctionToken("Long", new String[]{"-100L", "100L", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MLong mock = (MLong) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

}
