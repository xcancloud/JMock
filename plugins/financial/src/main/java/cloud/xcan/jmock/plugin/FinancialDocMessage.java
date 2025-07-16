package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class FinancialDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 70xx.
   */
  public final static String DOC_CATEGORY_FINANCIAL = "jmock.func.category.financial";

  public final static String DOC_AMOUNT_DESC = "jmock.func.MAmount.description";
  public final static String DOC_AMOUNT_C1 = "jmock.func.MAmount.C1";
  public final static String DOC_AMOUNT_C2 = "jmock.func.MAmount.C2";

  public final static String DOC_BUDGET_CATEGORY_DESC = "jmock.func.MBudgetCategory.description";
  public final static String DOC_BUDGET_CATEGORY_C1 = "jmock.func.MBudgetCategory.C1";
  public final static String DOC_BUDGET_CATEGORY_C2 = "jmock.func.MBudgetCategory.C2";

  public final static String DOC_CURRENCY_DESC = "jmock.func.MCurrency.description";
  public final static String DOC_CURRENCY_C1 = "jmock.func.MCurrency.C1";
  public final static String DOC_CURRENCY_C2 = "jmock.func.MCurrency.C2";

  public final static String DOC_INVOICE_NUMBER_DESC = "jmock.func.MInvoiceNumber.description";
  public final static String DOC_INVOICE_NUMBER_C1 = "jmock.func.MInvoiceNumber.C1";
  public final static String DOC_INVOICE_NUMBER_C2 = "jmock.func.MInvoiceNumber.C2";

  public final static String DOC_TAX_CODE_DESC = "jmock.func.MTaxCode.description";
  public final static String DOC_TAX_CODE_C1 = "jmock.func.MTaxCode.C1";
  public final static String DOC_TAX_CODE_C2 = "jmock.func.MTaxCode.C2";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-financial-plugin-messages");
  }
}
