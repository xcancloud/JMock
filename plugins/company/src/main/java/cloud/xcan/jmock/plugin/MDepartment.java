package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DATA_DEPARTMENTS;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_CATEGORY_COMPANY;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_DEPARTMENT_C1;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_DEPARTMENT_C2;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_DEPARTMENT_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.Locale;

@JMockFunctionRegister(descI18nKey = DOC_DEPARTMENT_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPANY}, order = 4002)
public class MDepartment extends AbstractMockFunction {

  private final String[] departments;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_DEPARTMENT_C1,
      example = "@Department()",
      exampleValues = {"产品管理部", "采购部"})
  public MDepartment() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_DEPARTMENT_C2,
      example = "@Department(en)",
      exampleValues = {"Finance", "Sales"})
  public MDepartment(Locale locale) {
    this.locale = locale;
    this.departments = getString(DATA_DEPARTMENTS, locale).split("\\|");
  }

  @Override
  public String mock() {
    return departments[JMockRandom.nextInt(departments.length)];
  }
}
