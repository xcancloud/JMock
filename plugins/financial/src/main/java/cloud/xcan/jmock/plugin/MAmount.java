package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_AMOUNT_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_AMOUNT_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_AMOUNT_DESC;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_FINANCIAL;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

@JMockFunctionRegister(descI18nKey = DOC_AMOUNT_DESC,
    categoryI18nKey = {DOC_CATEGORY_FINANCIAL}, order = 7001)
public class MAmount extends AbstractMockFunction {

  public static final double MIN_AMOUNT = 100.0;
  public static final double MAX_AMOUNT = 1000000.0;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_AMOUNT_C1,
      example = "@Amount()",
      exampleValues = {
          "¥532,009.41",
          "¥795,734.08"
      })
  public MAmount() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_AMOUNT_C2,
      example = "@Amount(en)",
      exampleValues = {
          "¤249,495.63",
          "¤965,859.18"
      })
  public MAmount(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomAmount(locale);
  }

  /**
   * Generates a random monetary amount with currency formatting
   *
   * @param locale Locale for currency formatting
   * @return Formatted monetary amount string
   */
  public static String generateRandomAmount(Locale locale) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    double amount = MIN_AMOUNT + (MAX_AMOUNT - MIN_AMOUNT) * rand.nextDouble();

    NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
    return currencyFormat.format(Math.round(amount * 100.0) / 100.0);
  }
}
