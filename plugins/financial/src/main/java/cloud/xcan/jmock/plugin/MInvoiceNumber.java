package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MInvoiceNumber extends AbstractMockFunction {

  private static final Map<Locale, String> INVOICE_PREFIXES = Map.of(
      Locale.CHINA, "FP",
      Locale.ENGLISH, "INV"
  );

  private static final Map<Locale, String> INVOICE_SEPARATORS = Map.of(
      Locale.CHINA, "-",
      Locale.ENGLISH, "-"
  );

  private final Locale locale;

  public MInvoiceNumber() {
    this(Locale.CHINA);
  }

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
