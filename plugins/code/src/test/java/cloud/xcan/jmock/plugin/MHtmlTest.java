package cloud.xcan.jmock.plugin;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class MHtmlTest {

  @RepeatedTest(10)
  void testGenerateRandomHtmlDocument() {
    String html = MHtml.generateRandomHtmlDocument();

    assertNotNull(html);
    assertFalse(html.isEmpty());
    assertTrue(html.length() > 500);

    assertTrue(html.contains("<!DOCTYPE html>"));
    assertTrue(html.contains("<html"));
    assertTrue(html.contains("<head>"));
    assertTrue(html.contains("<title>"));
    assertTrue(html.contains("<style>"));
    assertTrue(html.contains("<body>"));
    assertTrue(html.contains("</html>"));

    assertTrue(html.contains("font-family"));
    assertTrue(html.contains("background-color"));

    assertTrue(html.contains("<p>"));
    assertTrue(html.contains("</p>"));
  }

  @RepeatedTest(10)
  void testGenerateRandomTitle() {
    String title = MHtml.generateRandomTitle();
    assertNotNull(title);
    assertFalse(title.isEmpty());
    assertTrue(title.split(" ").length >= 2);
  }

  @RepeatedTest(10)
  void testGenerateRandomCss() {
    String css = MHtml.generateRandomCss();

    assertNotNull(css);
    assertFalse(css.isEmpty());

    assertTrue(css.contains("body {"));
    assertTrue(css.contains(".container {"));
    assertTrue(css.contains(".btn {"));
    assertTrue(css.contains("table {"));
  }

  @RepeatedTest(10)
  void testGenerateRandomHeading() {
    String heading = MHtml.generateRandomHeading();

    assertNotNull(heading);
    assertFalse(heading.isEmpty());
    assertTrue(heading.split(" ").length >= 3);
  }

  @RepeatedTest(10)
  void testGenerateRandomParagraph() {
    String paragraph = MHtml.generateRandomParagraph();

    assertNotNull(paragraph);
    assertFalse(paragraph.isEmpty());
    assertTrue(paragraph.length() > 50);

    if (paragraph.contains("<")) {
      assertTrue(paragraph.contains("</"));
    }
  }

  @RepeatedTest(10)
  void testGenerateRandomList() {
    String list = MHtml.generateRandomList();

    assertNotNull(list);
    assertFalse(list.isEmpty());

    assertTrue(list.contains("<ul>") || list.contains("<ol>"));
    assertTrue(list.contains("<li>"));
    assertTrue(list.contains("</li>"));
  }

  @RepeatedTest(10)
  void testGenerateRandomTable() {
    String table = MHtml.generateRandomTable();

    assertNotNull(table);
    assertFalse(table.isEmpty());

    assertTrue(table.contains("<table>"));
    assertTrue(table.contains("<thead>"));
    assertTrue(table.contains("<tbody>"));
    assertTrue(table.contains("<tr>"));
    assertTrue(table.contains("<th>"));
    assertTrue(table.contains("<td>"));
  }

  @RepeatedTest(10)
  void testGenerateRandomForm() {
    String form = MHtml.generateRandomForm();

    assertNotNull(form);
    assertFalse(form.isEmpty());

    assertTrue(form.contains("<form>"));
    assertTrue(form.contains("</form>"));
    assertTrue(form.contains("<label"));
    assertTrue(form.contains("<input") || form.contains("<textarea"));
    assertTrue(form.contains("<button"));
  }

  @RepeatedTest(10)
  void testGenerateRandomImage() {
    String img = MHtml.generateRandomImage();

    assertNotNull(img);
    assertFalse(img.isEmpty());

    assertTrue(img.contains("<img"));
    assertTrue(img.contains("src=\""));
    assertTrue(img.contains("alt=\""));
  }

  @RepeatedTest(10)
  void testGenerateRandomButton() {
    String button = MHtml.generateRandomButton();

    assertNotNull(button);
    assertFalse(button.isEmpty());
    assertTrue(button.contains("<button"));
    assertTrue(button.contains("</button>"));
    assertTrue(button.contains("class=\"btn\""));
  }

  @Test
  void testContentDiversity() {
    Set<String> titles = new HashSet<>();
    Set<String> buttons = new HashSet<>();

    for (int i = 0; i < 50; i++) {
      titles.add(MHtml.generateRandomTitle());
      buttons.add(MHtml.generateRandomButton());
    }

    assertTrue(titles.size() > 30, "Title diversity");
    assertTrue(buttons.size() > 5, "Button text diversity");
  }

  @Test
  void testHtmlFormatting() {
    String html = MHtml.generateRandomHtmlDocument();

    assertTrue(countOccurrences(html, "<div") >= countOccurrences(html, "</div>"));
    assertTrue(countOccurrences(html, "<p") >= countOccurrences(html, "</p>"));
    assertTrue(countOccurrences(html, "<li") == countOccurrences(html, "</li>"));

    assertFalse(html.contains("= ") || html.contains("=\" ") || html.contains("= \""));
  }

  private int countOccurrences(String str, String substr) {
    int count = 0;
    int idx = 0;

    while ((idx = str.indexOf(substr, idx)) != -1) {
      count++;
      idx += substr.length();
    }

    return count;
  }
}
