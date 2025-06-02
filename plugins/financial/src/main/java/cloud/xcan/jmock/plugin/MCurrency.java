package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MCurrency extends AbstractMockFunction {

  private static final Map<Locale, List<String>> CURRENCIES = Map.of(
      Locale.CHINA, Arrays.asList("CNY", "USD", "EUR", "JPY", "GBP", "HKD", "AUD", "CAD"),
      Locale.ENGLISH, Arrays.asList("USD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY")
  );

  private final Locale locale;

  public MCurrency() {
    this(Locale.CHINA);
  }

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
