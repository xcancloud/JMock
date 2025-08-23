package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class HashDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 6xx.
   */
  public static final String DOC_CATEGORY_HASH = "jmock.func.category.hash";

  public static final String DOC_SHA_DESC = "jmock.func.MSha.description";
  public static final String DOC_SHA_PARAMETER_VERSION = "jmock.func.MSha.parameter.version";
  public static final String DOC_SHA_C1 = "jmock.func.MSha.C1";
  public static final String DOC_SHA_C2 = "jmock.func.MSha.C2";

  public static final String DOC_MD5_DESC = "jmock.func.MMd5.description";
  public static final String DOC_MD5_PARAMETER_LENGTH = "jmock.func.MMd5.parameter.length";
  public static final String DOC_MD5_C1 = "jmock.func.MMd5.C1";
  public static final String DOC_MD5_C2 = "jmock.func.MMd5.C2";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-hash-plugin-messages");
  }
}
