package cloud.xcan.jmock.core.function.user;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MEDUCATION_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MEDUCATION_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MEDUCATION_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MEDUCATION_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MEDUCATION_PARAMETER_DICT;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.JMockResources;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author hao.guo
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MEDUCATION_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 808)
public class MEducation extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MEDUCATION_PARAMETER_DICT)
  private String[] dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  final static Locale DEFAULT_LOCALE_VALUE = Locale.CHINA;

  @JMockConstructor(descI18nKey = DOC_MEDUCATION_C1,
      example = "@Education()",
      exampleValues = {"本科"})
  public MEducation() {
    this(DEFAULT_LOCALE_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MEDUCATION_C2,
      example = "@Education(zh_CN)",
      exampleValues = {"本科"})
  public MEducation(Locale locale) {
    this(locale, null);
  }

  @JMockConstructor(descI18nKey = DOC_MEDUCATION_C3,
      example = "@Education(本科,硕士)",
      exampleValues = {"本科"})
  public MEducation(String dict) {
    this(null, dict);
  }

  public MEducation(Locale locale, String dict) {
    if (ObjectUtils.isEmpty(locale)) {
      this.locale = DEFAULT_LOCALE_VALUE;
    }
    if (ObjectUtils.isEmpty(dict)) {
      String education = MessageResources.getString(JMockResources.EDUCATION, locale);
      this.dict = education.split("\\|");
    } else {
      this.dict = dict.split("\\|");
    }
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dict.length);
    return dict[dictIndex];
  }

}
