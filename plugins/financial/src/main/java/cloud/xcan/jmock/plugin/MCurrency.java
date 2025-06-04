package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_FINANCIAL;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CURRENCY_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CURRENCY_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CURRENCY_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@JMockFunctionRegister(descI18nKey = DOC_CURRENCY_DESC,
    categoryI18nKey = {DOC_CATEGORY_FINANCIAL}, order = 7003)
public class MCurrency extends AbstractMockFunction {

  private static final Map<Locale, List<String>> CURRENCIES = Map.of(
      Locale.CHINA, Arrays.asList("CNY", "USD", "EUR", "JPY", "GBP", "HKD", "AUD", "CAD"),
      Locale.ENGLISH, Arrays.asList("USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY")
  );

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_CURRENCY_C1,
      example = "@MCurrency()",
      exampleValues = {
          "EUR",
          "EUR"
      })
  public MCurrency() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_CURRENCY_C2,
      example = "@MCurrency(en)",
      exampleValues = {
          "CNY",
          "CAD"
      })
  public MCurrency(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomCurrency(locale);
  }

  /**
   * Generates a random currency code
   *
   * @param locale Locale for currency preference
   * @return 3-letter currency code
   */
  public static String generateRandomCurrency(Locale locale) {
    List<String> currencies = CURRENCIES.getOrDefault(locale, CURRENCIES.get(Locale.ENGLISH));
    return currencies.get(ThreadLocalRandom.current().nextInt(currencies.size()));
  }
}
