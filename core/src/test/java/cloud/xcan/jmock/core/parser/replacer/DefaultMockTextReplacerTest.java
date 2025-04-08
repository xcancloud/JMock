package cloud.xcan.jmock.core.parser.replacer;

import org.junit.Assert;
import org.junit.Test;

public class DefaultMockTextReplacerTest {

  @Test
  public void replaceTest() throws Exception {
    String content = "This is random text: @String(10)";
    String result = new DefaultMockTextReplacer().replace(content);
    Assert.assertEquals(content.length() - "@String(10)".length() + 10, result.length());
  }

  @Test
  public void replaceOffsetTest() throws Exception {
    String content = "This is random text: @String(10) @String(1) @String(10) 1000";
    String result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assert.assertEquals(
        content.length() - "@String(10) @String(1) @String(10)".length() + 21 + 2 /*blank space*/,
        result.length());

    content = "@String(10) @String(1) @String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assert.assertEquals(21 + 2 /*blank space*/, result.length());

    content = "@String(10)@String(1)@String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assert.assertEquals(21, result.length());

    content = "111 @String(10)@String(1)@String(10)";
    result = new DefaultMockTextReplacer().replace(content);
    System.out.println(result);
    Assert.assertEquals(4 + 21, result.length());
  }

  @Test
  public void replaceJsonTest() throws Exception {
    try {
      String content = "{\n"
          + "  \"name\": \"@Name()\",\n"
          + "  \"email\": \"@Email()\",\n"
          + "  \"phone\": \"@Mobile()\",\n"
          + "  \"address\": {\n"
          + "    \"street\": \"123 Main St\",\n"
          + "    \"city\": \"@City()\",\n"
          + "    \"state\": \"@Province()\",\n"
          + "    \"zip\": \"@Zip()\"\n"
          + "  },\n"
          + "  \"interests\": [\n"
          + "    \"reading\",\n"
          + "    \"hiking\",\n"
          + "    \"cooking\"\n"
          + "  ]\n"
          + "}";
      String result = new DefaultMockTextReplacer().replace(content);
      System.out.println(result);
    } catch (Exception e) {
      Assert.fail(e.getMessage());
    }
  }

}
