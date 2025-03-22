package cloud.xcan.comp.jmock.core.function.date;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_DATE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MWEEK_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MWEEK_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MWEEK_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.i18n.JMockResources;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MWEEK_DESC,
    categoryI18nKey = {DOC_CATEGORY_DATE}, order = 273)
public class MWeek extends AbstractMockFunction {

  private String[] dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  final static Locale DEFAULT_LOCALE_VALUE = Locale.CHINA;

  @JMockConstructor(descI18nKey = DOC_MWEEK_C1,
      example = "@Week()",
      exampleValues = {"星期二"})
  public MWeek() {
    this(DEFAULT_LOCALE_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MWEEK_C2,
      example = "@Week()",
      exampleValues = {"Tuesday"})
  public MWeek(Locale locale) {
    if (ObjectUtils.isEmpty(locale)) {
      this.locale = DEFAULT_LOCALE_VALUE;
    }
    String week = MessageResources.getString(JMockResources.WEEK, locale);
    this.dict = week.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dict.length);
    return dict[dictIndex];
  }

}
