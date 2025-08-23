package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class ArticleDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 4xx.
   */
  public static final String DOC_CATEGORY_ARTICLE = "jmock.func.category.text";

  public static final String DATA_ARTICLE = "jmock.func.data.article";
  public static final String DATA_PARAGRAPH = "jmock.func.data.paragraph";
  public static final String DATA_SENTENCE = "jmock.func.data.sentence";
  public static final String DATA_WORD = "jmock.func.data.word";
  public static final String DATA_TITLE = "jmock.func.data.title";
  public static final String DATA_TANG_POETRY = "jmock.func.data.tang.poetry";

  public final static String DOC_ARTICLE_DESC = "jmock.func.MArticle.description";
  public final static String DOC_ARTICLE_PARAMETER_PARAGRAPH_COUNT = "jmock.func.MArticle.parameter.paragraphCount";
  public final static String DOC_ARTICLE_PARAMETER_WORD_COUNT = "jmock.func.MArticle.parameter.wordCount";
  public final static String DOC_ARTICLE_C1 = "jmock.func.MArticle.C1";
  public final static String DOC_ARTICLE_C2 = "jmock.func.MArticle.C2";
  public final static String DOC_ARTICLE_C3 = "jmock.func.MArticle.C3";
  public final static String DOC_ARTICLE_C4 = "jmock.func.MArticle.C4";

  public final static String DOC_PARAGRAPH_DESC = "jmock.func.MParagraph.description";
  public final static String DOC_PARAGRAPH_C1 = "jmock.func.MParagraph.C1";
  public final static String DOC_PARAGRAPH_C2 = "jmock.func.MParagraph.C2";
  public final static String DOC_PARAGRAPH_C3 = "jmock.func.MParagraph.C3";

  public final static String DOC_SENTENCE_DESC = "jmock.func.MSentence.description";
  public final static String DOC_SENTENCE_C1 = "jmock.func.MSentence.C1";
  public final static String DOC_SENTENCE_C2 = "jmock.func.MSentence.C2";
  public final static String DOC_SENTENCE_C3 = "jmock.func.MSentence.C3";

  public final static String DOC_WORD_DESC = "jmock.func.MWord.description";
  public final static String DOC_WORD_C1 = "jmock.func.MWord.C1";
  public final static String DOC_WORD_C2 = "jmock.func.MWord.C2";
  public final static String DOC_WORD_C3 = "jmock.func.MWord.C3";

  public final static String DOC_TITLE_DESC = "jmock.func.MTitle.description";
  public final static String DOC_TITLE_C1 = "jmock.func.MTitle.C1";
  public final static String DOC_TITLE_C2 = "jmock.func.MTitle.C2";
  public final static String DOC_TITLE_C3 = "jmock.func.MTitle.C3";

  public final static String DOC_TANGPOETRY_DESC = "jmock.func.MTangPoetry.description";
  public final static String DOC_TANGPOETRY_C1 = "jmock.func.MTangPoetry.C1";
  public final static String DOC_TANGPOETRY_C2 = "jmock.func.MTangPoetry.C2";
  public final static String DOC_TANGPOETRY_C3 = "jmock.func.MTangPoetry.C3";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-article-plugin-messages");
  }
}
