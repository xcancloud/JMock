package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class CompanyDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 40xx.
   */
  public final static String DOC_CATEGORY_COMPANY = "jmock.func.category.company";

  public final static String DOC_COMPANY_DESC = "jmock.func.MCompany.description";
  public final static String DOC_COMPANY_C1 = "jmock.func.MCompany.C1";
  public final static String DOC_COMPANY_C2 = "jmock.func.MCompany.C2";

  public final static String DOC_DEPARTMENT_DESC = "jmock.func.MDepartment.description";
  public final static String DOC_DEPARTMENT_C1 = "jmock.func.MDepartment.C1";
  public final static String DOC_DEPARTMENT_C2 = "jmock.func.MDepartment.C2";

  public final static String DOC_INDUSTRY_DESC = "jmock.func.MIndustry.description";
  public final static String DOC_INDUSTRY_C1 = "jmock.func.MIndustry.C1";
  public final static String DOC_INDUSTRY_C2 = "jmock.func.MIndustry.C2";

  public final static String DOC_JOB_DESC = "jmock.func.MJob.description";
  public final static String DOC_JOB_C1 = "jmock.func.MJob.C1";
  public final static String DOC_JOB_C2 = "jmock.func.MJob.C2";


  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-company-plugin-messages");
  }
}
