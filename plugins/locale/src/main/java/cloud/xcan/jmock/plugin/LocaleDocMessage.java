package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class LocaleDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 9xx.
   */
  public static final String DOC_CATEGORY_LOCALE = "jmock.func.category.locale";

  public static final String DATA_TIMEZONE = "jmock.func.data.timezone";

  public static final String DOC_LOCALE_DESC = "jmock.func.MLocale.description";
  public static final String DOC_LOCALE_PARAMETER_JOINER = "jmock.func.MLocale.parameter.joiner";
  public static final String DOC_LOCALE_C1 = "jmock.func.MLocale.C1";
  public static final String DOC_LOCALE_C2 = "jmock.func.MLocale.C2";

  public static final String DOC_TIMEZONE_DESC = "jmock.func.MTimeZone.description";
  public static final String DOC_TIMEZONE_PARAMETER_DICT = "jmock.func.MTimeZone.parameter.dict";
  public static final String DOC_TIMEZONE_C1 = "jmock.func.MTimeZone.C1";
  public static final String DOC_TIMEZONE_C2 = "jmock.func.MTimeZone.C2";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-locale-plugin-messages");
  }
}
