package cloud.xcan.jmock.plugin;

import static cloud.xcan.angus.spec.utils.ObjectUtils.nullSafe;
import static cloud.xcan.angus.spec.utils.ObjectUtils.stringSafe;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_C1;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_C2;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_C3;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_C4;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_DESC;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_PARAMETER_PARAGRAPH_COUNT;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_ARTICLE_PARAMETER_WORD_COUNT;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_CATEGORY_ARTICLE;
import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_ARTICLE_DESC, categoryI18nKey = {
    DOC_CATEGORY_ARTICLE}, order = 406)
public class MArticle extends AbstractMockFunction {

  public static final int DEFAULT_PARAGRAPH_COUNT = 3;
  public static final int DEFAULT_WORD_COUNT = 800;

  @JMockParameter(descI18nKey = DOC_ARTICLE_PARAMETER_PARAGRAPH_COUNT)
  private int paragraphCount = DEFAULT_PARAGRAPH_COUNT;

  @JMockParameter(descI18nKey = DOC_ARTICLE_PARAMETER_WORD_COUNT)
  private int wordCount = DEFAULT_WORD_COUNT;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  private transient String dict;

  private transient final Random random = new Random();

  @JMockConstructor(descI18nKey = DOC_ARTICLE_C1,
      example = "@Article()",
      exampleValues = {
          """
              有时我们是主角，有时我们是配角，但无论角色如何，我们都在努力地活着，追求着自己的梦想。 未来会怎样？ 有时我们是主角，有时我们是配角，但无论角色如何，我们都在努力地活着，追求着自己的梦想。 静坐的时光，总有往昔的故事，如同电影一样心头回放，那些曾经的人，那些渐远渐无书的你我，而如今形同陌路了，唯有残留的往事，像是爬满心房的藤蔓，无休无止。 科技的发展改变了我们的生活方式。

              从风格上来说不管它是豪放的还纤柔的，也不管它是浪漫的还是写实的我也同样爱读。 静坐的时光，总有往昔的故事，如同电影一样心头回放，那些曾经的人，那些渐远渐无书的你我，而如今形同陌路了，唯有残留的往事，像是爬满心房的藤蔓，无休无止。 从风格上来说不管它是豪放的还纤柔的，也不管它是浪漫的还是写实的我也同样爱读。 喜欢诗，喜欢读诗，时间一长，也就喜欢练习写诗。

              不被打扰，也是一种美丽的享受。 喜欢诗，喜欢读诗，时间一长，也就喜欢练习写诗。 孤独何妨呢? 从互联网到人工智能，从智能手机到智能家居，科技正在以前所未有的速度改变着我们的世界。 春天来了，万物复苏。"""
      })
  public MArticle() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_ARTICLE_C2,
      example = "@Article(en)",
      exampleValues = {
          """
              The time is gone, the promise is still there, the people are gone, like the arrow from the mstring, even if it is far to the horizon, the tender words are still there.  You are over there. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting.  I am here.

              At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting.  Looking at the withered leaves, knowing that some departures are unobstructed, and the empty heart will be filled with the tranquility of the autumn light, so the parting time and time will be warmed by the next meeting.  We could have become friends, communicate, talk, and sigh.

              If the affection is deep, I am not afraid of the long-term, having you in my heart is enough to comfort a lonely soul.  You are over there.  Accidentally met and met.  If the affection is deep, I am not afraid of the long-term, having you in my heart is enough to comfort a lonely soul."""})
  public MArticle(Locale locale) {
    this.locale = locale;
    this.dict = getString(ArticleDocMessage.DATA_ARTICLE, locale);
  }

  @JMockConstructor(descI18nKey = DOC_ARTICLE_C3,
      example = "@Article(2|200|en)",
      exampleValues = {"""
          At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting.  I am here. The time is gone, the promise is still there, the people are gone, like the arrow from the mstring, even if it is far to the horizon, the tender words are still there.  The autumn models came with graceful and graceful steps, lovingly sending the back of Xia Yuan, accompanied by fallen leaves, waiting for the arrival of the next season.

          Xia came quietly, and went quietly.  Xia came quietly, and went quietly. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting."""})
  public MArticle(Integer paragraphCount, Integer wordCount, Locale locale) {
    this.paragraphCount = nullSafe(paragraphCount, DEFAULT_PARAGRAPH_COUNT);
    this.wordCount = nullSafe(wordCount, DEFAULT_WORD_COUNT);
    this.dict = getString(ArticleDocMessage.DATA_ARTICLE, locale);
  }

  @JMockConstructor(descI18nKey = DOC_ARTICLE_C4,
      example = "@Paragraph(2|200|At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting.)",
      exampleValues = {"""
          At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting.

          At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting. At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting."""})
  public MArticle(Integer paragraphCount, Integer wordCount, String dict) {
    this.paragraphCount = nullSafe(paragraphCount, DEFAULT_PARAGRAPH_COUNT);
    this.wordCount = nullSafe(wordCount, DEFAULT_WORD_COUNT);
    this.dict = stringSafe(dict, getString(ArticleDocMessage.DATA_ARTICLE, Locale.ENGLISH));
  }

  @Override
  public String mock() {
    return generateArticle(paragraphCount, wordCount);
  }

  /**
   * Generates a random article with the specified parameters
   *
   * @param paragraphCount Number of paragraphs in the article
   * @param wordCount      Target word count for the article
   * @return Generated article text
   */
  public String generateArticle(int paragraphCount, int wordCount) {
    if (paragraphCount <= 0 || wordCount <= 0) {
      throw new IllegalArgumentException("Paragraph count and word count must be positive");
    }

    List<String> sentences = splitTextIntoSentences(dict);

    // Generate paragraphs
    List<String> paragraphs = new ArrayList<>();
    int currentWordCount = 0;

    while (paragraphs.size() < paragraphCount && currentWordCount < wordCount) {
      String paragraph = generateParagraph(sentences);
      int paragraphWordCount = countWords(paragraph);

      // Check if adding this paragraph would exceed the word count
      if (currentWordCount + paragraphWordCount > wordCount && !paragraphs.isEmpty()) {
        // Adjust the last paragraph to meet word count
        String lastParagraph = adjustParagraphLength(paragraphs.get(paragraphs.size() - 1),
            wordCount - currentWordCount);
        paragraphs.set(paragraphs.size() - 1, lastParagraph);
        break;
      }

      paragraphs.add(paragraph);
      currentWordCount += paragraphWordCount;

      // If we have enough paragraphs but not enough words, add more content
      if (paragraphs.size() < paragraphCount && currentWordCount < wordCount) {
        String extraParagraph = generateParagraph(sentences);
        paragraphs.add(extraParagraph);
        currentWordCount += countWords(extraParagraph);
      }
    }

    return String.join("\n\n", paragraphs);
  }

  /**
   * Generates a single paragraph by combining random sentences
   */
  private String generateParagraph(List<String> sentences) {
    int sentenceCount = random.nextInt(3) + 3; // 3-5 sentences per paragraph
    return IntStream.range(0, sentenceCount)
        .mapToObj(i -> sentences.get(random.nextInt(sentences.size())))
        .collect(Collectors.joining(" "));
  }

  /**
   * Adjusts paragraph length to meet target word count
   */
  private String adjustParagraphLength(String paragraph, int targetWordCount) {
    String[] words = paragraph.split("\\s+");
    if (words.length <= targetWordCount) {
      return paragraph;
    }

    StringBuilder result = new StringBuilder();
    int count = 0;
    for (String word : words) {
      if (count >= targetWordCount) {
        break;
      }
      result.append(word).append(" ");
      count++;
    }
    return result.toString().trim();
  }

  /**
   * Splits text into sentences
   */
  private List<String> splitTextIntoSentences(String text) {
    // Simple sentence splitting (works for English and Chinese)
    String[] sentences = text.split("(?<=[.!?。？！])");
    return List.of(sentences);
  }

  /**
   * Counts words in a string
   */
  private int countWords(String text) {
    if (text == null || text.isEmpty()) {
      return 0;
    }
    return CHINA.equals(locale) ? text.length() : text.split("\\s+").length;
  }

  public static void main(String[] args) {
    System.out.println(new MArticle(2, 200,
        "At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting.").mock());
  }
}

