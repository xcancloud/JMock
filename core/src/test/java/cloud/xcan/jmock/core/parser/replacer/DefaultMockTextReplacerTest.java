package cloud.xcan.jmock.core.parser.replacer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DefaultMockTextReplacerTest {

  @Test
  public void replaceTest() throws Exception {
    String content = "This is random text: @String(10)";
    String result = new DefaultMockTextReplacer().replace(content);
    Assertions.assertEquals(content.length() - "@String(10)".length() + 10, result.length());
  }

  @Test
  public void replaceOffsetTest() throws Exception {
    String content = "This is random text: @String(10) @String(1) @String(10) 1000";
    String result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assertions.assertEquals(
        content.length() - "@String(10) @String(1) @String(10)".length() + 21 + 2 /*blank space*/,
        result.length());

    content = "@String(10) @String(1) @String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assertions.assertEquals(21 + 2 /*blank space*/, result.length());

    content = "@String(10)@String(1)@String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assertions.assertEquals(21, result.length());

    content = "111 @String(10)@String(1)@String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assertions.assertEquals(4 + 21, result.length());
  }

  @Test
  public void replaceJsonTest() throws Exception {
    try {
      String content = """
          {
            "name": "@Name(en)",
            "email": "@Email()",
            "phone": "@Mobile()",
            "address": "@Address(en)",
            "hobbies": [ "reading", "hiking",  "cooking" ]
          }""";
      String result = new DefaultMockTextReplacer().replace(content);
      System.out.println(result);
    } catch (Exception e) {
      Assertions.fail(e.getMessage());
    }
  }

}
