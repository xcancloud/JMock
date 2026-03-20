package cloud.xcan.jmock.plugin.article;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MArticle;
import cloud.xcan.jmock.plugin.MParagraph;
import cloud.xcan.jmock.plugin.MSentence;
import cloud.xcan.jmock.plugin.MTangPoetry;
import cloud.xcan.jmock.plugin.MTitle;
import cloud.xcan.jmock.plugin.MWord;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class ArticleDataGeneratorTest {

  @RepeatedTest(10)
  void testMWord_default() {
    String word = new MWord().mock();
    assertNotNull(word);
    assertFalse(word.isEmpty());
  }

  @Test
  void testMWord_variety() {
    Set<String> words = new HashSet<>();
    MWord gen = new MWord();
    for (int i = 0; i < 50; i++) {
      words.add(gen.mock());
    }
    assertTrue(words.size() >= 5, "Should generate varied words, got: " + words.size());
  }

  @RepeatedTest(10)
  void testMTitle_default() {
    String title = new MTitle().mock();
    assertNotNull(title);
    assertFalse(title.isEmpty());
  }

  @Test
  void testMTitle_variety() {
    Set<String> titles = new HashSet<>();
    MTitle gen = new MTitle();
    for (int i = 0; i < 30; i++) {
      titles.add(gen.mock());
    }
    assertTrue(titles.size() >= 5, "Should generate varied titles, got: " + titles.size());
  }

  @RepeatedTest(10)
  void testMSentence_default() {
    String sentence = new MSentence().mock();
    assertNotNull(sentence);
    assertTrue(sentence.length() >= 2, "Sentence too short: " + sentence);
  }

  @RepeatedTest(10)
  void testMParagraph_default() {
    String paragraph = new MParagraph().mock();
    assertNotNull(paragraph);
    assertTrue(paragraph.length() >= 10, "Paragraph too short: " + paragraph);
  }

  @RepeatedTest(5)
  void testMArticle_default() {
    String article = new MArticle().mock();
    assertNotNull(article);
    assertTrue(article.length() >= 50, "Article too short: " + article);
  }

  @RepeatedTest(5)
  void testMTangPoetry_default() {
    String poetry = new MTangPoetry().mock();
    assertNotNull(poetry);
    assertFalse(poetry.isEmpty());
  }

  @Test
  void testMTangPoetry_variety() {
    Set<String> poems = new HashSet<>();
    MTangPoetry gen = new MTangPoetry();
    for (int i = 0; i < 30; i++) {
      poems.add(gen.mock());
    }
    assertTrue(poems.size() >= 3, "Should generate varied poems, got: " + poems.size());
  }
}
