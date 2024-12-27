
package cloud.xcan.comp.jmock.core.function.date.mtimestamp;

import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.core.function.date.MTimestamp;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MTimestampTest {

  @Test
  public void case1() throws Exception {
    FunctionToken token = new FunctionToken("MTimestamp", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MTimestamp mock = (MTimestamp) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      Long value = mock.mock();
      System.out.println("value = " + value);
    }
  }
}
