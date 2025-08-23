package cloud.xcan.jmock.plugin.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MMarkdown;
import java.util.regex.Pattern;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class MMarkdownTest {

  @RepeatedTest(10)
  void testGenerateRandomMarkdown() {
    String markdown = MMarkdown.generateRandomMarkdown();

    assertNotNull(markdown);
    assertFalse(markdown.isEmpty());
    assertTrue(markdown.length() > 1000);

    assertTrue(markdown.startsWith("# "));
    assertTrue(markdown.contains("## Table of Contents"));
  }

  @RepeatedTest(10)
  void testGenerateTitle() {
    String title = MMarkdown.generateTitle();

    assertNotNull(title);
    assertTrue(title.startsWith("# "));
    assertTrue(title.contains("Project Overview"));
    assertTrue(title.length() > 10);
  }

  @RepeatedTest(10)
  void testGenerateBadges() {
    String badges = MMarkdown.generateBadges();

    assertNotNull(badges);
    assertTrue(badges.contains("!["));
    assertTrue(badges.contains("shields.io"));
  }

  @RepeatedTest(10)
  void testGenerateTableOfContents() {
    String toc = MMarkdown.generateTableOfContents();

    assertNotNull(toc);
    assertTrue(toc.startsWith("## Table of Contents"));
    assertTrue(toc.contains("- ["));
    assertTrue(toc.contains("](#"));
    assertTrue(toc.contains("Contributing"));
    assertTrue(toc.contains("License"));
  }

  @RepeatedTest(10)
  void testGenerateParagraph() {
    String paragraph = MMarkdown.generateParagraph(2, 4);

    assertNotNull(paragraph);
    assertFalse(paragraph.isEmpty());
    assertTrue(paragraph.split("\\. ").length >= 2);

    if (paragraph.contains("**")) {
      assertTrue(paragraph.contains("**"));
    }
    if (paragraph.contains("`")) {
      assertTrue(paragraph.contains("`"));
    }
  }

  @RepeatedTest(10)
  void testGenerateList() {
    String list = MMarkdown.generateList(3, 5);

    assertNotNull(list);
    assertTrue(list.startsWith("- ") || list.startsWith("1. "));
    assertTrue(list.split("\n").length >= 3);
  }

  @RepeatedTest(10)
  void testGenerateCodeBlock() {
    String codeBlock = MMarkdown.generateCodeBlock();

    assertNotNull(codeBlock);
    assertTrue(codeBlock.startsWith("```"));
    assertTrue(codeBlock.endsWith("```"));
    assertTrue(codeBlock.contains("\n"));
  }

  @RepeatedTest(10)
  void testGenerateTechStackTable() {
    String table = MMarkdown.generateTechStackTable();

    assertNotNull(table);
    assertTrue(table.startsWith("| Technology | Purpose | Maturity |"));
    assertTrue(table.contains("|----"));
    assertTrue(table.split("\n").length >= 6);
  }

  @RepeatedTest(10)
  void testGenerateConfigTable() {
    String table = MMarkdown.generateConfigTable();

    assertNotNull(table);
    assertTrue(table.contains("Parameter"));
    assertTrue(table.contains("Type"));
    assertTrue(table.contains("Default"));
    assertTrue(table.split("\n").length >= 7);
  }

  @RepeatedTest(10)
  void testGenerateIntroduction() {
    String intro = MMarkdown.generateIntroduction();

    assertNotNull(intro);
    assertTrue(intro.contains("Key Benefits"));
    assertTrue(intro.contains("Technology Stack"));
  }

  @RepeatedTest(10)
  void testGenerateInstallation() {
    String install = MMarkdown.generateInstallation();

    assertNotNull(install);
    assertTrue(install.contains("Installation Steps"));
    assertTrue(install.contains("```"));
  }

  @RepeatedTest(10)
  void testGenerateUsage() {
    String usage = MMarkdown.generateUsage();

    assertNotNull(usage);
    assertTrue(usage.contains("Basic Usage"));
    assertTrue(usage.contains("Advanced Features"));
    assertTrue(usage.contains("Common Patterns"));
  }

  @RepeatedTest(10)
  void testGenerateExamples() {
    String examples = MMarkdown.generateExamples();

    assertNotNull(examples);
    assertTrue(examples.contains("Example"));
    assertTrue(examples.contains("Explanation"));
    assertTrue(examples.contains("```"));
  }

  @Test
  void testMarkdownFormatting() {
    String markdown = MMarkdown.generateRandomMarkdown();

    assertTrue(Pattern.compile("(?m)^#\\s.+").matcher(markdown).find());
    assertTrue(Pattern.compile("(?m)^##\\s.+").matcher(markdown).find());

    if (markdown.contains("|")) {
      assertTrue(Pattern.compile("(?m)^\\|.*\\|$").matcher(markdown).find());
      assertTrue(Pattern.compile("(?m)^\\|-+\\|").matcher(markdown).find());
    }

    if (markdown.contains("```")) {
      long codeBlocks = Pattern.compile("(?m)^```").matcher(markdown).results().count();
      assertTrue(codeBlocks % 2 == 0, "Unclosed code blocks");
    }

    if (markdown.contains("- ")) {
      assertTrue(Pattern.compile("(?m)^-\\s.+").matcher(markdown).find());
    }
  }

}
