package cloud.xcan.jmock.plugin.basic.menum;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MEnum;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MEnumMockTest {

  @Test
  public void case1_defaultTest() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{"DocGenerator|B"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    Set<String> expected = new HashSet<>(Arrays.asList("DocGenerator", "B"));
    for (int i = 0; i < 20; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      Assertions.assertTrue(expected.contains(str), "Unexpected value: " + str);
    }
  }

  @Test
  public void case2_withWeight() throws Exception {
    FunctionToken token = new FunctionToken("MEnum", new String[]{"DocGenerator|B", "2:1"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    Set<String> expected = new HashSet<>(Arrays.asList("DocGenerator", "B"));
    int aCount = 0;
    for (int i = 0; i < 300; i++) {
      String str = mock.mock();
      Assertions.assertNotNull(str);
      Assertions.assertTrue(expected.contains(str), "Unexpected value: " + str);
      if ("DocGenerator".equals(str)) {
        aCount++;
      }
    }
    // With 2:1 weight, DocGenerator should appear roughly 2/3 of the time
    Assertions.assertTrue(aCount > 100,
        "DocGenerator should appear frequently with 2:1 weight, got: " + aCount);
  }

  @Test
  public void case3_withNullWeight() throws Exception {
    FunctionToken token = new FunctionToken("MEnum",
        new String[]{"DocGenerator|B|C", "1:2:3", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEnum mock = (MEnum) parser.parse(token);
    Set<String> expected = new HashSet<>(Arrays.asList("DocGenerator", "B", "C"));
    boolean hasNull = false;
    for (int i = 0; i < 1000; i++) {
      String str = mock.mock();
      if (str == null) {
        hasNull = true;
      } else {
        Assertions.assertTrue(expected.contains(str), "Unexpected value: " + str);
      }
    }
    Assertions.assertTrue(hasNull, "Should produce null with 1:9 nullWeight");
  }
}
