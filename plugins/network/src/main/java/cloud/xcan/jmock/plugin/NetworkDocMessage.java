package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class NetworkDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 7xx.
   */
  public static final String DOC_CATEGORY_NETWORK = "jmock.func.category.network";

  public static final String DATA_URL_PARAM = "jmock.func.data.urlParam";
  public static final String DATA_PROTOCOL = "jmock.func.data.protocol";
  public static final String DATA_APP_NAME = "jmock.func.data.appName";

  public static final String DOC_URL_DESC = "jmock.func.MUrl.description";
  public static final String DOC_URL_PARAMETER_MAX = "jmock.func.MUrl.parameter.max";
  public static final String DOC_URL_PARAMETER_PROTOCOL = "jmock.func.MUrl.parameter.protocol";
  public static final String DOC_URL_PARAMETER_DOMAIN = "jmock.func.MUrl.parameter.domain";
  public static final String DOC_URL_PARAMETER_ALLOW_QUERY = "jmock.func.MUrl.parameter.allowQueryParams";
  public static final String DOC_URL_C1 = "jmock.func.MUrl.C1";
  public static final String DOC_URL_C2 = "jmock.func.MUrl.C2";
  public static final String DOC_URL_C3 = "jmock.func.MUrl.C3";

  public static final String DOC_PROTOCOL_DESC = "jmock.func.MProtocol.description";
  public static final String DOC_PROTOCOL_PARAMETER_DICT = "jmock.func.MProtocol.parameter.dict";
  public static final String DOC_PROTOCOL_C1 = "jmock.func.MProtocol.C1";
  public static final String DOC_PROTOCOL_C2 = "jmock.func.MProtocol.C2";

  public static final String DOC_IPV4_DESC = "jmock.func.MIPv4.description";
  public static final String DOC_IPV4_C1 = "jmock.func.MIPv4.C1";

  public static final String DOC_IPV6_DESC = "jmock.func.MIPv6.description";
  public static final String DOC_IPV6_C1 = "jmock.func.MIPv6.C1";

  public static final String DOC_PORT_DESC = "jmock.func.MPort.description";
  public static final String DOC_PORT_PARAMETER_MIN = "jmock.func.MPort.parameter.min";
  public static final String DOC_PORT_PARAMETER_MAX = "jmock.func.MPort.parameter.max";
  public static final String DOC_PORT_C1 = "jmock.func.MPort.C1";
  public static final String DOC_PORT_C2 = "jmock.func.MPort.C2";

  public static final String DOC_MAC_DESC = "jmock.func.MMac.description";
  public static final String DOC_MAC_C1 = "jmock.func.MMac.C1";

  public static final String DOC_APP_NAME_DESC = "jmock.func.MAppName.description";
  public static final String DOC_APP_NAME_C1 = "jmock.func.MAppName.C1";
  public static final String DOC_APP_NAME_C2 = "jmock.func.MAppName.C2";

  public static final String DOC_APP_VERSION_DESC = "jmock.func.MAppVersion.description";
  public static final String DOC_APP_VERSION_PARAMETER_PREFIX_DICT = "jmock.func.MAppVersion.parameter.prefixDict";
  public static final String DOC_APP_VERSION_PARAMETER_RELEASE_STATE_DICT = "jmock.func.MAppVersion.parameter.releaseStateDict";
  public static final String DOC_APP_VERSION_PARAMETER_BUILD_STATE_DICT = "jmock.func.MAppVersion.parameter.buildStateDict";
  public static final String DOC_APP_VERSION_C1 = "jmock.func.MAppVersion.C1";
  public static final String DOC_APP_VERSION_C2 = "jmock.func.MAppVersion.C2";
  public static final String DOC_APP_VERSION_C3 = "jmock.func.MAppVersion.C3";
  public static final String DOC_APP_VERSION_C4 = "jmock.func.MAppVersion.C4";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-network-plugin-messages");
  }
}
