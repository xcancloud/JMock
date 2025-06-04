package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class DocMessage {

  static {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-crypto-plugin-messages");
  }

  /**
   * Coding order by 60xx.
   */
  public final static String DOC_CATEGORY_CRYPTO = "jmock.func.category.crypto";

  public final static String DOC_BASE64_DESC = "jmock.func.MBase64.description";
  public final static String DOC_BASE64_PARAMETER_LENGTH = "jmock.func.MBase64.parameter.length";
  public final static String DOC_BASE64_C1 = "jmock.func.MBase64.C1";
  public final static String DOC_BASE64_C2 = "jmock.func.MBase64.C2";

  public final static String DOC_HASH_DESC = "jmock.func.MHash.description";
  public final static String DOC_HASH_PARAMETER_ALGORITHM = "jmock.func.MHash.parameter.algorithm";
  public final static String DOC_HASH_C1 = "jmock.func.MHash.C1";
  public final static String DOC_HASH_C2 = "jmock.func.MHash.C2";

  public final static String DOC_HEX_DESC = "jmock.func.MHex.description";
  public final static String DOC_HEX_PARAMETER_LENGTH = "jmock.func.MHex.parameter.length";
  public final static String DOC_HEX_C1 = "jmock.func.MHex.C1";
  public final static String DOC_HEX_C2 = "jmock.func.MHex.C2";

  public final static String DOC_KEY_PAIR_DESC = "jmock.func.MKeyPair.description";
  public final static String DOC_KEY_PAIR_PARAMETER_ALGORITHM = "jmock.func.MKeyPair.parameter.algorithm";
  public final static String DOC_KEY_PAIR_PARAMETER_KEY_SIZE = "jmock.func.MKeyPair.parameter.keySize";
  public final static String DOC_KEY_PAIR_C1 = "jmock.func.MKeyPair.C1";
  public final static String DOC_KEY_PAIR_C2 = "jmock.func.MKeyPair.C2";

  public final static String DOC_SYMMETRIC_KEY_DESC = "jmock.func.MSymmetricKey.description";
  public final static String DOC_SYMMETRIC_KEY_PARAMETER_ALGORITHM = "jmock.func.MSymmetric.parameter.algorithm";
  public final static String DOC_SYMMETRIC_KEY_C1 = "jmock.func.MSymmetricKey.C1";
  public final static String DOC_SYMMETRIC_KEY_C2 = "jmock.func.MSymmetricKey.C2";

}
