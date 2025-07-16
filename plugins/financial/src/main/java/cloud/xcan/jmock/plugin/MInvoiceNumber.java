package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_CATEGORY_FINANCIAL;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_INVOICE_NUMBER_C1;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_INVOICE_NUMBER_C2;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_INVOICE_NUMBER_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@JMockFunctionRegister(descI18nKey = DOC_INVOICE_NUMBER_DESC,
    categoryI18nKey = {DOC_CATEGORY_FINANCIAL}, order = 7004)
public class MInvoiceNumber extends AbstractMockFunction {

  private static final Map<Locale, String> INVOICE_PREFIXES = Map.of(
      Locale.CHINA, "FP",
      Locale.ENGLISH, "INV"
  );

  private static final Map<Locale, String> INVOICE_SEPARATORS = Map.of(
      Locale.CHINA, "-",
      Locale.ENGLISH, "-"
  );

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_INVOICE_NUMBER_C1,
      example = "@InvoiceNumber()",
      exampleValues = {
          "FP-202506-63498",
          "FP-202506-75545"
      })
  public MInvoiceNumber() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_INVOICE_NUMBER_C2,
      example = "@InvoiceNumber(en)",
      exampleValues = {
          "INV-202506-18877",
          "INV-202506-81864"
      })
  public MInvoiceNumber(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomInvoiceNumber(locale);
  }

  /**
   * Generates a random invoice number
   *
   * @param locale Locale for invoice format
   * @return Formatted invoice number
   */
  public static String generateRandomInvoiceNumber(Locale locale) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    String prefix = INVOICE_PREFIXES.getOrDefault(locale, "INV");
    String separator = INVOICE_SEPARATORS.getOrDefault(locale, "-");

    // Current year and month
    String yearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));

    // Sequential number (5 digits)
    String sequence = String.format("%05d", 10000 + rand.nextInt(90000));

    return prefix + separator + yearMonth + separator + sequence;
  }
}
