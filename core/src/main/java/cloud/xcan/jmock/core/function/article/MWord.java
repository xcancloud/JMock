package cloud.xcan.jmock.core.function.article;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ARTICLE;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MWORD_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MWORD_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MWORD_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MWORD_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MWORD_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARTICLE}, order = 401)
public class MWord extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private String locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MWORD_C1,
      example = "@Word()",
      exampleValues = {"弯弯曲曲", "千家万户"})
  public MWord() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MWORD_C2,
      example = "@Word(en)",
      exampleValues = {"along", "designer"})
  public MWord(Locale locale) {
    String title = MessageResources.getString(JMockMessage.FDATA_WORD, locale);
    this.dictArray = title.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_MWORD_C3,
      example = "@Word(开开心心|快快乐乐|红红火火))",
      exampleValues = {"开开心心", "快快乐乐"})
  public MWord(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }

}
