package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_CATEGORY_FINANCIAL;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_TAX_CODE_C1;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_TAX_CODE_C2;
import static cloud.xcan.jmock.plugin.FinancialDocMessage.DOC_TAX_CODE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@JMockFunctionRegister(descI18nKey = DOC_TAX_CODE_DESC,
    categoryI18nKey = {DOC_CATEGORY_FINANCIAL}, order = 7005)
public class MTaxCode extends AbstractMockFunction {

  private static final Map<Locale, List<String>> TAX_CODE_FORMATS = Map.of(
      Locale.CHINA, Arrays.asList(
          "91310101MA1G0XX{0}{1}{2}{3}", // 统一社会信用代码
          "11010{0}{1}{2}{3}000"         // 税务登记号
      ),
      Locale.ENGLISH, Arrays.asList(
          "XX-{0}{1}{2}{3}{4}{5}{6}{7}", // EIN format
          "{0}{1}{2}-{3}{4}{5}{6}{7}{8}" // TIN format
      )
  );

  private static final String ALPHANUM = "ABCDEFGHJKLMNPQRSTUVWXYZ0123456789"; // Excluding confusing chars

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_TAX_CODE_C1,
      example = "@TaxCode()",
      exampleValues = {
          "11010RU5G000",
          "11010LJR5000",
          "110109LGN000",
      })
  public MTaxCode() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_TAX_CODE_C2,
      example = "@TaxCode(en)",
      exampleValues = {
          "XX-LNDL6HY7",
          "L4B-ZK85QS"
      })
  public MTaxCode(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomTaxCode(locale);
  }

  /**
   * Generates a random tax identification code
   *
   * @param locale Locale for tax code format
   * @return Tax identification code
   */
  public static String generateRandomTaxCode(Locale locale) {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    List<String> formats = TAX_CODE_FORMATS.getOrDefault(locale,
        TAX_CODE_FORMATS.get(Locale.ENGLISH));
    String format = formats.get(rand.nextInt(formats.size()));

    // Replace placeholders with random characters
    StringBuilder code = new StringBuilder();
    for (int i = 0; i < format.length(); i++) {
      char c = format.charAt(i);
      if (c == '{') {
        // Skip placeholder index
        while (i < format.length() && format.charAt(i) != '}') {
          i++;
        }
        code.append(ALPHANUM.charAt(rand.nextInt(ALPHANUM.length())));
      } else {
        code.append(c);
      }
    }

    return code.toString();
  }

}
