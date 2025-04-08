package cloud.xcan.jmock.core.function.user.mage;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.function.user.MAge;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MAgeMockTest {

  @Test
  public void case1_defaultRangeTest() throws Exception {
    FunctionToken token = new FunctionToken("Age", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAge mock = (MAge) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Integer age = mock.mock();
      System.out.println("value:\t" + age);
      Assertions.assertTrue(1 <= age && age <= 100);
    }
  }

  @Test
  public void case2_customizeRangeTest() throws Exception {
    FunctionToken token = new FunctionToken("Age", new String[]{"2", "8"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MAge mock = (MAge) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Integer age = mock.mock();
      System.out.println("value:\t" + age);
      Assertions.assertTrue(2 <= age && age <= 8);
    }
  }
}
