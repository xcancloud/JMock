package cloud.xcan.comp.jmock.core.function.article;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ARTICLE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MSENTENCE_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MSENTENCE_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MSENTENCE_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MSENTENCE_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.i18n.JMockResources;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author hao.guo
 * <p>
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MSENTENCE_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARTICLE}, order = 403)
public class MSentence extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String[] dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  /**
   * Max value of Integer allowed
   */
  final static Locale DEFAULT_LOCAL_VALUE = Locale.CHINA;

  @JMockConstructor(descI18nKey = DOC_MSENTENCE_C1,
      example = "@Sentence()",
      exampleValues = {"好好学习"})
  public MSentence() {
    this(DEFAULT_LOCAL_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MSENTENCE_C2,
      example = "@Sentence(zh_CN)",
      exampleValues = {"好好学习"})
  public MSentence(Locale locale) {
    this(locale, null);
  }

  @JMockConstructor(descI18nKey = DOC_MSENTENCE_C3,
      example = "@Sentence(好好学习|天天向上)",
      exampleValues = {"天天向上"})
  public MSentence(String dict) {
    this(null, dict);
  }

  public MSentence(Locale locale, String dict) {
    if (ObjectUtils.isEmpty(locale)) {
      locale = DEFAULT_LOCAL_VALUE;
    }
    if (ObjectUtils.isEmpty(dict)) {
      String sentenceResources = MessageResources.getString(JMockResources.SENTENCE, locale);
      String sentence = new String(sentenceResources.getBytes(StandardCharsets.ISO_8859_1),
          StandardCharsets.UTF_8);
      this.dict = sentence.split("\\|");
    } else {
      this.dict = dict.split("\\|");
    }
  }

  @Override
  public String mock() {
    return dict[RandomUtils.nextInt(0, dict.length)];
  }
}
