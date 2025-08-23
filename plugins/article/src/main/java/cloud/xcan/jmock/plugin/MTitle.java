package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_CATEGORY_ARTICLE;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_TITLE_C1;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_TITLE_C2;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_TITLE_C3;
import static cloud.xcan.jmock.plugin.ArticleDocMessage.DOC_TITLE_DESC;
import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_TITLE_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARTICLE}, order = 402)
public class MTitle extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_TITLE_C1,
      example = "@Title()",
      exampleValues = {"做好疫情防护"})
  public MTitle() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_TITLE_C2,
      example = "@Title(en)",
      exampleValues = {"Do a good job in epidemic prevention"})
  public MTitle(Locale locale) {
    String title = MessageResources.getString(ArticleDocMessage.DATA_TITLE, locale);
    this.dictArray = title.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_TITLE_C3,
      example = "@Title(做好疫情防护|乌俄局势)",
      exampleValues = {"做好疫情防护"})
  public MTitle(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }

}
