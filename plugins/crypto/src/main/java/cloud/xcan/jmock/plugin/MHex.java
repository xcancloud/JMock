package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CRYPTO;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HEX_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HEX_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HEX_DESC;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HEX_PARAMETER_LENGTH;
import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.HexFormat;

@JMockFunctionRegister(descI18nKey = DOC_HEX_DESC,
    categoryI18nKey = {DOC_CATEGORY_CRYPTO}, order = 6003)
public class MHex extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_HEX_PARAMETER_LENGTH)
  private final int length;

  @JMockConstructor(descI18nKey = DOC_HEX_C1,
      example = "@Hex()",
      exampleValues = {"6c1aecc9", "45075b35"})
  public MHex() {
    this(8);
  }

  @JMockConstructor(descI18nKey = DOC_HEX_C2,
      example = "@Hex(32)",
      exampleValues = {"a45e50145cdee6c45141682ab3c2bce8", "c97e1952909460ccaf410a089ef1b41c"})
  public MHex(int length) {
    this.length = length;
  }

  @Override
  public String mock() {
    return generateRandomHexString(length);
  }

  /**
   * Generates a random hexadecimal string
   *
   * @param length Desired string length (must be even)
   * @return Hexadecimal string
   */
  public static String generateRandomHexString(int length) {
    if (length % 2 != 0) {
      throw new IllegalArgumentException("Hex string length must be even");
    }

    byte[] randomBytes = new byte[length / 2];
    random.nextBytes(randomBytes);
    return HexFormat.of().formatHex(randomBytes);
  }

}
