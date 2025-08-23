package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class DateDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 2xx.
   */
  public static final String DOC_CATEGORY_DATE = "jmock.func.category.date";

  public static final String DATA_WEEK = "jmock.func.data.week";
  public static final String DATA_MONTH = "jmock.func.data.month";

  public static final String DOC_LOCALE_DATE_DESC = "jmock.func.MLocaleDate.description";
  public static final String DOC_LOCALE_DATE_PARAMETER_FORMAT = "jmock.func.MLocaleDate.parameter.format";
  public static final String DOC_LOCALE_DATE_PARAMETER_ZONEID = "jmock.func.MLocaleDate.parameter.zoneId";
  public static final String DOC_LOCALE_DATE_PARAMETER_RANDOM = "jmock.func.MLocaleDate.parameter.random";
  public static final String DOC_LOCALE_DATE_C1 = "jmock.func.MLocaleDate.C1";
  public static final String DOC_LOCALE_DATE_C2 = "jmock.func.MLocaleDate.C2";
  public static final String DOC_LOCALE_DATE_C3 = "jmock.func.MLocaleDate.C3";
  public static final String DOC_LOCALE_DATE_C4 = "jmock.func.MLocaleDate.C4";

  public static final String DOC_LOCALE_TIME_DESC = "jmock.func.MLocaleTime.description";
  public static final String DOC_LOCALE_TIME_PARAMETER_FORMAT = "jmock.func.MLocaleTime.parameter.format";
  public static final String DOC_LOCALE_TIME_PARAMETER_RANDOM = "jmock.func.MLocaleTime.parameter.random";
  public static final String DOC_LOCALE_TIME_C1 = "jmock.func.MLocaleTime.C1";
  public static final String DOC_LOCALE_TIME_C2 = "jmock.func.MLocaleTime.C2";
  public static final String DOC_LOCALE_TIME_C3 = "jmock.func.MLocaleTime.C3";
  public static final String DOC_LOCALE_TIME_C4 = "jmock.func.MLocaleTime.C4";

  public static final String DOC_LOCALE_DATE_TIME_DESC = "jmock.func.MLocaleDateTime.description";
  public static final String DOC_LOCALE_DATE_TIME_PARAMETER_FORMAT = "jmock.func.MLocaleDateTime.parameter.format";
  public static final String DOC_LOCALE_DATE_TIME_PARAMETER_RANDOM = "jmock.func.MLocaleDateTime.parameter.random";
  public static final String DOC_LOCALE_DATE_TIME_C1 = "jmock.func.MLocaleDateTime.C1";
  public static final String DOC_LOCALE_DATE_TIME_C2 = "jmock.func.MLocaleDateTime.C2";
  public static final String DOC_LOCALE_DATE_TIME_C3 = "jmock.func.MLocaleDateTime.C3";
  public static final String DOC_LOCALE_DATE_TIME_C4 = "jmock.func.MLocaleDateTime.C4";

  public static final String DOC_TIMESTAMP_DESC = "jmock.func.MTimestamp.description";
  public static final String DOC_TIMESTAMP_PARAMETER_UNIX = "jmock.func.MTimestamp.parameter.unix";
  public static final String DOC_TIMESTAMP_C1 = "jmock.func.MTimestamp.C1";
  public static final String DOC_TIMESTAMP_C2 = "jmock.func.MTimestamp.C2";

  public static final String DOC_WEEK_DESC = "jmock.func.MWeek.description";
  public static final String DOC_WEEK_C1 = "jmock.func.MWeek.C1";
  public static final String DOC_WEEK_C2 = "jmock.func.MWeek.C2";

  public static final String DOC_MONTH_DESC = "jmock.func.MMonth.description";
  public static final String DOC_MONTH_C1 = "jmock.func.MMonth.C1";
  public static final String DOC_MONTH_C2 = "jmock.func.MMonth.C2";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-date-plugin-messages");
  }
}
