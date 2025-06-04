package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_BASE64_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_BASE64_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_BASE64_DESC;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_BASE64_PARAMETER_LENGTH;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CRYPTO;
import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.Base64;

@JMockFunctionRegister(descI18nKey = DOC_BASE64_DESC,
    categoryI18nKey = {DOC_CATEGORY_CRYPTO}, order = 6001)
public class MBase64 extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_BASE64_PARAMETER_LENGTH)
  private final int length;

  @JMockConstructor(descI18nKey = DOC_BASE64_C1,
      example = "@Base64()",
      exampleValues = {
          "pWJCBBEyFi4THaYWOcLtlj+aWJ5w/v9SazFHbi+D/XQdY1AoFDkaF/hJlHS1tjITELoz5ROb7WS98Tio4IO796mezlxjdCT4c+Hdp5adjZNRK2FT3QZ0jKeOwM263KIT",
          "wddMreXApGyczv6Glsti22HtOABU69E1KacyRidg6l6AAD2fvPezQeN+jEUOyk9zwTgyA/WXsTUpw5f4C9TMS09V4rvm/ikQD3OQilYpo0g6MIblEToYBXnWS4ybnoMh"
      })
  public MBase64() {
    this(128);
  }

  @JMockConstructor(descI18nKey = DOC_BASE64_C2,
      example = "@Base64(32)",
      exampleValues = {"XII3JC7y4gQvCXLB7V08uKDvBORyDiVI", "LQqpeYt1sl3rlYPaLYQZUsKtALZj8Yt7"})
  public MBase64(int length) {
    this.length = length;
  }

  @Override
  public String mock() {
    return generateRandomBase64String(length);
  }

  /**
   * Generates a random Base64 encoded string
   *
   * @param length Desired output string length
   * @return Base64 encoded random string
   */
  public static String generateRandomBase64String(int length) {
    // Calculate required byte length (Base64 uses 4 chars for every 3 bytes)
    int byteLength = (int) Math.ceil(length * 3.0 / 4);
    byte[] randomBytes = new byte[byteLength];
    random.nextBytes(randomBytes);

    String base64 = Base64.getEncoder().encodeToString(randomBytes);
    return base64.substring(0, Math.min(length, base64.length()));
  }

}
