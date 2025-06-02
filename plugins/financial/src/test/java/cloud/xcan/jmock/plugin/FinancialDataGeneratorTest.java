package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MAmount.MAX_AMOUNT;
import static cloud.xcan.jmock.plugin.MAmount.generateRandomAmount;
import static cloud.xcan.jmock.plugin.MBudgetCategory.generateRandomBudgetCategory;
import static cloud.xcan.jmock.plugin.MCurrency.generateRandomCurrency;
import static cloud.xcan.jmock.plugin.MInvoiceNumber.generateRandomInvoiceNumber;
import static cloud.xcan.jmock.plugin.MTaxCode.generateRandomTaxCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class FinancialDataGeneratorTest {

  @RepeatedTest(20)
  void testGenerateRandomAmount_CN() {
    String amount = generateRandomAmount(Locale.CHINA);
    assertNotNull(amount);
    assertTrue(amount.startsWith("¥") || amount.startsWith("￥"));
    assertTrue(amount.contains(",") || amount.contains("."));
  }

  @RepeatedTest(20)
  void testGenerateRandomAmount_ENGLISH() {
    String amount = generateRandomAmount(Locale.ENGLISH);
    assertNotNull(amount);
    assertTrue(amount.startsWith("¤"));
    assertTrue(amount.contains(",") || amount.contains("."));
  }

  @Test
  void testAmountRange() {
    double min = Double.MAX_VALUE;
    double max = Double.MIN_VALUE;

    for (int i = 0; i < 100; i++) {
      String usAmount = generateRandomAmount(Locale.ENGLISH);
      String cleanAmount = usAmount.replaceAll("[¤,]", "");
      double value = Double.parseDouble(cleanAmount);

      if (value < min) {
        min = value;
      }
      if (value > max) {
        max = value;
      }
    }

    assertTrue(min >= 100.0 && min <= MAX_AMOUNT, "Min amount should be >=100: " + min);
    assertTrue(max >= min && max <= MAX_AMOUNT, "Max amount should be >=500,000: " + max);
  }

  @RepeatedTest(20)
  void testGenerateRandomCurrency_CN() {
    String currency = generateRandomCurrency(Locale.CHINA);
    assertNotNull(currency);
    assertEquals(3, currency.length());
  }

  @RepeatedTest(20)
  void testGenerateRandomCurrency_ENGLISH() {
    String currency = generateRandomCurrency(Locale.ENGLISH);
    assertNotNull(currency);
    assertEquals(3, currency.length());
  }

  @Test
  void testCurrencyCoverage() {
    Set<String> cnCurrencies = new HashSet<>();
    Set<String> usCurrencies = new HashSet<>();

    for (int i = 0; i < 20; i++) {
      cnCurrencies.add(generateRandomCurrency(Locale.CHINA));
      usCurrencies.add(generateRandomCurrency(Locale.ENGLISH));
    }

    assertTrue(cnCurrencies.size() >= 5, "Should cover multiple currencies for CN");
    assertTrue(usCurrencies.size() >= 5, "Should cover multiple currencies for ENGLISH");
  }

  @RepeatedTest(20)
  void testGenerateRandomInvoiceNumber_CN() {
    String invoice = generateRandomInvoiceNumber(Locale.CHINA);
    assertNotNull(invoice);
    assertTrue(invoice.startsWith("FP-"));
    assertEquals(15, invoice.length()); // FP-202306-01234
  }

  @RepeatedTest(20)
  void testGenerateRandomInvoiceNumber_ENGLISH() {
    String invoice = generateRandomInvoiceNumber(Locale.ENGLISH);
    assertNotNull(invoice);
    assertTrue(invoice.startsWith("INV-"));
    assertEquals(16, invoice.length()); // INV-202306-01234
  }

  @Test
  void testInvoiceNumberPattern() {
    String cnInvoice = generateRandomInvoiceNumber(Locale.CHINA);
    assertTrue(cnInvoice.matches("FP-\\d{6}-\\d{5}"));

    String usInvoice = generateRandomInvoiceNumber(Locale.ENGLISH);
    assertTrue(usInvoice.matches("INV-\\d{6}-\\d{5}"));

    String currentYearMonth = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
    assertTrue(cnInvoice.contains(currentYearMonth));
  }

  @RepeatedTest(20)
  void testGenerateRandomTaxCode_CN() {
    String taxCode = generateRandomTaxCode(Locale.CHINA);
    assertNotNull(taxCode);
    assertTrue(taxCode.startsWith("91") || taxCode.startsWith("11"));
  }

  @RepeatedTest(20)
  void testGenerateRandomTaxCode_ENGLISH() {
    String taxCode = generateRandomTaxCode(Locale.ENGLISH);
    assertNotNull(taxCode);
    assertTrue(taxCode.length() >= 9 && taxCode.length() <= 11);
    assertTrue(taxCode.contains("-") || taxCode.contains("XX"));
  }

  @Test
  void testTaxCodePattern() {
    String cnTaxCode = generateRandomTaxCode(Locale.CHINA);
    assertNotNull(cnTaxCode);

    String usTaxCode = generateRandomTaxCode(Locale.ENGLISH);
    assertNotNull(usTaxCode);
  }

  @RepeatedTest(20)
  void testGenerateRandomBudgetCategory_CN() {
    String category = generateRandomBudgetCategory(Locale.CHINA);
    assertNotNull(category);
    assertTrue(category.length() >= 2 && category.length() <= 6);
    assertFalse(category.contains("&")); // Should not contain English characters
  }

  @RepeatedTest(20)
  void testGenerateRandomBudgetCategory_ENGLISH() {
    String category = generateRandomBudgetCategory(Locale.ENGLISH);
    assertNotNull(category);
    assertTrue(category.length() >= 5 && category.length() <= 25);
  }

  @Test
  void testBudgetCategoryCoverage() {
    Set<String> cnCategories = new HashSet<>();
    Set<String> usCategories = new HashSet<>();

    for (int i = 0; i < 30; i++) {
      cnCategories.add(generateRandomBudgetCategory(Locale.CHINA));
      usCategories.add(generateRandomBudgetCategory(Locale.ENGLISH));
    }

    assertTrue(cnCategories.size() >= 10, "Should cover multiple CN categories");
    assertTrue(usCategories.size() >= 10, "Should cover multiple ENGLISH categories");
  }

  @Test
  void testDefaultLocaleHandling() {
    // Amount
    String amount = generateRandomAmount(Locale.JAPAN);
    assertTrue(amount.startsWith("¥") || amount.startsWith("￥"));

    // Currency
    String currency = generateRandomCurrency(Locale.GERMANY);
    assertTrue(Set.of("ENGLISHD", "EUR", "GBP", "JPY", "CAD", "AUD", "CHF", "CNY").contains(currency));

    // Invoice
    String invoice = generateRandomInvoiceNumber(Locale.ITALY);
    assertTrue(invoice.startsWith("INV-"));

    // Tax Code
    String taxCode = generateRandomTaxCode(Locale.KOREA);
    assertTrue(taxCode.matches("[A-Z0-9-]{9,11}"));
  }
}
