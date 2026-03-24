package cloud.xcan.jmock.core.parser.replacer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * String mock tokens are resolved via SPI ({@code MString} in basic-plugin); core {@code pom.xml}
 * declares {@code xcan-jmock.basic-plugin} as a test dependency so replacements run in this
 * module.
 */
public class DefaultMockTextReplacerTest {

  @Test
  public void replaceTest() throws Exception {
    String content = "This is random text: @String(10)";
    String result = new DefaultMockTextReplacer().replace(content);
    Assertions.assertFalse(result.contains("@String"), result);
    Assertions.assertTrue(result.startsWith("This is random text: "));
    int suffixLen = result.length() - "This is random text: ".length();
    Assertions.assertTrue(suffixLen >= 8 && suffixLen <= 32,
        "suffixLen=" + suffixLen + " result=" + result);
  }

  @Test
  public void replaceOffsetTest() throws Exception {
    String content = "This is random text: @String(10) @String(1) @String(10) 1000";
    String result = new DefaultMockTextReplacer().replace(content);
    Assertions.assertFalse(result.contains("@String"), result);
    Assertions.assertTrue(result.contains("1000"), result);
    Assertions.assertTrue(result.startsWith("This is random text: "), result);

    content = "@String(10) @String(1) @String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    Assertions.assertFalse(result.contains("@String"), result);
    Assertions.assertTrue(result.length() >= 12, "three replaced segments: " + result.length());

    content = "@String(10)@String(1)@String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    Assertions.assertFalse(result.contains("@String"), result);

    content = "111 @String(10)@String(1)@String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    Assertions.assertFalse(result.contains("@String"), result);
    Assertions.assertTrue(result.startsWith("111 "), result);
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
