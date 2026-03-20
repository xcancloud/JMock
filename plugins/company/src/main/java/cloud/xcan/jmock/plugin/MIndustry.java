package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DATA_INDUSTRIES;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_CATEGORY_COMPANY;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_INDUSTRY_C1;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_INDUSTRY_C2;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_INDUSTRY_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.Locale;

@JMockFunctionRegister(descI18nKey = DOC_INDUSTRY_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPANY}, order = 4003)
public class MIndustry extends AbstractMockFunction {

  private final String[] industries;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_INDUSTRY_C1,
      example = "@Industry(en)",
      exampleValues = {"信息技术", "电子商务"})
  public MIndustry() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_INDUSTRY_C2,
      example = "@Industry(en)",
      exampleValues = {"Food & Beverage", "Education"})
  public MIndustry(Locale locale) {
    this.locale = locale;
    this.industries = getString(DATA_INDUSTRIES, locale).split("\\|");
  }

  @Override
  public String mock() {
    return industries[JMockRandom.nextInt(industries.length)];
  }

}
