package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_BUDGET_CATEGORY_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_BUDGET_CATEGORY_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_BUDGET_CATEGORY_DESC;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_FINANCIAL;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@JMockFunctionRegister(descI18nKey = DOC_BUDGET_CATEGORY_DESC,
    categoryI18nKey = {DOC_CATEGORY_FINANCIAL}, order = 7002)
public class MBudgetCategory extends AbstractMockFunction {

  private static final Map<Locale, List<String>> BUDGET_CATEGORIES = Map.of(
      Locale.CHINA, Arrays.asList(
          "人员工资", "市场营销", "研发支出", "行政费用", "设备采购",
          "差旅报销", "培训费用", "办公用品", "软件许可", "咨询服务",
          "税费支出", "租金水电", "保险费用", "折旧摊销", "其他支出"
      ),
      Locale.ENGLISH, Arrays.asList(
          "Salaries & Wages", "Marketing", "R&D Expenses", "Administrative", "Equipment",
          "Travel & Reimbursement", "Training", "Office Supplies", "Software Licenses",
          "Consulting",
          "Taxes", "Rent & Utilities", "Insurance", "Depreciation", "Miscellaneous"
      )
  );

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_BUDGET_CATEGORY_C1,
      example = "@BudgetCategory()",
      exampleValues = {
          "市场营销",
          "差旅报销"
      })
  public MBudgetCategory() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_BUDGET_CATEGORY_C2,
      example = "@BudgetCategory(en)",
      exampleValues = {
          "Taxes",
          "Travel & Reimbursement"
      })
  public MBudgetCategory(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomBudgetCategory(locale);
  }

  /**
   * Generates a random budget category
   *
   * @param locale Locale for category name
   * @return Budget category name
   */
  public static String generateRandomBudgetCategory(Locale locale) {
    List<String> categories = BUDGET_CATEGORIES.getOrDefault(locale,
        BUDGET_CATEGORIES.get(Locale.ENGLISH));
    return categories.get(ThreadLocalRandom.current().nextInt(categories.size()));
  }
}
