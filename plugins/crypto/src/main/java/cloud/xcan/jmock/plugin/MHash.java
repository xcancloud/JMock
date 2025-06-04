package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CRYPTO;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HASH_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HASH_C2;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HASH_DESC;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_HASH_PARAMETER_ALGORITHM;
import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

@JMockFunctionRegister(descI18nKey = DOC_HASH_DESC,
    categoryI18nKey = {DOC_CATEGORY_CRYPTO}, order = 6002)
public class MHash extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_HASH_PARAMETER_ALGORITHM)
  private final String algorithm;

  @JMockConstructor(descI18nKey = DOC_HASH_C1,
      example = "@Hash()",
      exampleValues = {
          "4cf8e6d87f05b4f979ff8775d1f78fb8",
          "46a7f33aab52baef179770517bedeb2b"
      })
  public MHash() {
    this("MD5");
  }

  @JMockConstructor(descI18nKey = DOC_HASH_C2,
      example = "@Hash(SHA-256)",
      exampleValues = {
          "191d55ca8427b35fffc964461ee430e15ea738f67885ad65162cdafc866da558",
          "6c9cd1dadcf6c447ef4b80a9e28d358350958f9c2b8e4ca4fcffefa2c6c8185a"
      })
  public MHash(String algorithm) {
    this.algorithm = algorithm;
  }

  @Override
  public String mock() {
    return generateRandomHash(algorithm);
  }

  /**
   * Generates a random hash value using specified algorithm
   *
   * @param algorithm Hash algorithm (e.g., "SHA-256", "MD5")
   * @return Hexadecimal hash string
   */
  public static String generateRandomHash(String algorithm) {

    // Generate random input data
    byte[] randomData = new byte[32];
    random.nextBytes(randomData);

    // Compute hash
    MessageDigest digest = null;
    try {
      digest = MessageDigest.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
    byte[] hash = digest.digest(randomData);

    return HexFormat.of().formatHex(hash);
  }

}
