package cloud.xcan.jmock.plugin.basic.minteger;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MInteger;
import org.junit.jupiter.api.Test;

public class MIntegerMockTest {

  /**
   * No-parameter MockConstructor: @Integer()
   */
  @Test
  public void MInteger1() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Integer l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }


  /**
   * parameter MockConstructor: @Integer(Integer min, Integer max)
   */
  @Test
  public void MInteger2() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"-100", "100"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Integer l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }


  /**
   * parameter MockConstructor: @Integer(String nullWeight)
   */
  @Test
  public void MInteger3() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Integer l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

  /**
   * parameter MockConstructor: @Integer(Integer min, Integer max,String nullWeight)
   */
  @Test
  public void MInteger4() throws Exception {
    FunctionToken token = new FunctionToken("Integer", new String[]{"-100", "100", "1:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MInteger mock = (MInteger) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Integer l1 = mock.mock();
      System.out.println("l1 = " + l1);
    }
  }

}
