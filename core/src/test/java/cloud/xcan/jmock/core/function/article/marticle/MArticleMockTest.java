package cloud.xcan.jmock.core.function.article.marticle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.core.function.article.MArticle;
import java.util.Locale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MArticleMockTest {

  private MArticle enGenerator;
  private MArticle chineseGenerator;

  @BeforeEach
  void setUp() {
    enGenerator = new MArticle(Locale.ENGLISH);
    chineseGenerator = new MArticle(Locale.CHINESE);
  }

  @Test
  void generateArticleWithExactParameters() {
    String article = enGenerator.generateArticle(3, 100);
    assertNotNull(article);

    // Count paragraphs
    String[] paragraphs = article.split("\n\n");
    assertEquals(3, paragraphs.length);

    // Count words
    int wordCount = article.split("\\s+").length;
    assertTrue(wordCount >= 95 && wordCount <= 105,
        "Word count should be approximately 100. Actual: " + wordCount);
  }

  @Test
  void generateChineseArticle() {
    String article = chineseGenerator.generateArticle(2, 50);
    assertNotNull(article);

    // Count paragraphs
    String[] paragraphs = article.split("\n\n");
    assertEquals(2, paragraphs.length);

    // Count words (Chinese words are not space-separated, so we count characters)
    int charCount = article.replaceAll("\\s", "").length();
    assertTrue(charCount >= 45 && charCount <= 55,
        "Character count should be approximately 50. Actual: " + charCount);
  }

  @Test
  void generateArticleWithMoreWordsThanSentences() {
    String article = enGenerator.generateArticle(1, 500);
    assertNotNull(article);

    int wordCount = article.split("\\s+").length;
    assertTrue(wordCount >= 495 && wordCount <= 505,
        "Word count should be approximately 500. Actual: " + wordCount);
  }

  @Test
  void invalidParametersShouldThrowException() {
    assertThrows(IllegalArgumentException.class,
        () -> enGenerator.generateArticle(0, 100));

    assertThrows(IllegalArgumentException.class,
        () -> enGenerator.generateArticle(3, -10));
  }

  @Test
  void paragraphAdjustmentWorksCorrectly() {
    String article = enGenerator.generateArticle(1, 10);
    int wordCount = article.split("\\s+").length;
    assertTrue(wordCount >= 8 && wordCount <= 12,
        "Word count should be approximately 10. Actual: " + wordCount);
  }

  @Test
  void multipleParagraphsWithExactWordCount() {
    String article = enGenerator.generateArticle(4, 200);
    int wordCount = article.split("\\s+").length;
    assertTrue(wordCount >= 195 && wordCount <= 205,
        "Word count should be approximately 200. Actual: " + wordCount);
  }
}
