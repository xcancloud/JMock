package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

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

  private final Locale locale;

  public MTaxCode() {
    this(Locale.CHINA);
  }

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
