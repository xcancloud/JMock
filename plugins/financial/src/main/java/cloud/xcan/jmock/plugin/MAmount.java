package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class MAmount extends AbstractMockFunction {

  public static final double MIN_AMOUNT = 100.0;
  public static final double MAX_AMOUNT = 1000000.0;

  private final Locale locale;

  public MAmount() {
    this(Locale.CHINA);
  }

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
