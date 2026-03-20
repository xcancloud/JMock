package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DATA_COMPANY_TYPES;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DATA_COMPANY_WORDS;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_CATEGORY_COMPANY;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_COMPANY_C1;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_COMPANY_C2;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_COMPANY_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@JMockFunctionRegister(descI18nKey = DOC_COMPANY_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPANY}, order = 4001)
public class MCompany extends AbstractMockFunction {

  private final String[] companyTypes;
  private final String[] companyWords;
  private final boolean isChinese;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_COMPANY_C1,
      example = "@Company()",
      exampleValues = {"云信达咨询公司", "东方有限公司"})
  public MCompany() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_COMPANY_C2,
      example = "@Company(en)",
      exampleValues = {"Alpha Gamma Digital Corp.", "Gamma Apex Strategic Holdings"})
  public MCompany(Locale locale) {
    this.locale = locale;
    this.isChinese = locale.equals(Locale.CHINA);
    this.companyTypes = getString(DATA_COMPANY_TYPES, locale).split("\\|");
    this.companyWords = getString(DATA_COMPANY_WORDS, locale).split("\\|");
  }

  @Override
  public String mock() {
    StringBuilder name = new StringBuilder();

    // 2-3 words + company type
    int wordCount = 1 + JMockRandom.nextInt(3);
    Set<String> usedWords = new HashSet<>();

    for (int i = 0; i < wordCount; i++) {
      String word;
      do {
        word = companyWords[JMockRandom.nextInt(companyWords.length)];
      } while (usedWords.contains(word));

      name.append(word);
      if (!isChinese) {
        name.append(" ");
      }
      usedWords.add(word);
    }

    // Add company type
    name.append(companyTypes[JMockRandom.nextInt(companyTypes.length)]);

    return name.toString();
  }
}
