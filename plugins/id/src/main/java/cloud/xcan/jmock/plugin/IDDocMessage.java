package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class IDDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 3xx.
   */
  public static final String DOC_CATEGORY_ID = "jmock.func.category.id";

  public static final String DOC_GUID_DESC = "jmock.func.MGuid.description";
  public static final String DOC_GUID_PARAMETER_FORMAT = "jmock.func.MGuid.parameter.withoutSeparator";
  public static final String DOC_GUID_C1 = "jmock.func.MGuid.C1";
  public static final String DOC_GUID_C2 = "jmock.func.MGuid.C2";

  public static final String DOC_UUID_DESC = "jmock.func.MUuid.description";
  public static final String DOC_UUID_PARAMETER_FORMAT = "jmock.func.MUuid.parameter.withoutSeparator";
  public static final String DOC_UUID_C1 = "jmock.func.MUuid.C1";
  public static final String DOC_UUID_C2 = "jmock.func.MUuid.C2";

  public static final String DOC_SNOWID_DESC = "jmock.func.MSnowId.description";
  public static final String DOC_SNOWID_PARAMETER_DCID = "jmock.func.MSnowId.parameter.dcId";
  public static final String DOC_SNOWID_PARAMETER_MID = "jmock.func.MSnowId.parameter.mId";
  public static final String DOC_SNOWID_C1 = "jmock.func.MSnowId.C1";
  public static final String DOC_SNOWID_C2 = "jmock.func.MSnowId.C2";

  public static final String DOC_INCID_DESC = "jmock.func.MIncId.description";
  public static final String DOC_INCID_PARAMETER_INIT = "jmock.func.MIncId.parameter.init";
  public static final String DOC_INCID_PARAMETER_STEP = "jmock.func.MIncId.parameter.step";
  public static final String DOC_INCID_C1 = "jmock.func.MIncId.C1";
  public static final String DOC_INCID_C2 = "jmock.func.MIncId.C2";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-id-plugin-messages");
  }
}
