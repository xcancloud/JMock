package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_CATEGORY_CRYPTO;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_SYMMETRIC_KEY_C1;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_SYMMETRIC_KEY_C2;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_SYMMETRIC_KEY_DESC;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_SYMMETRIC_KEY_PARAMETER_ALGORITHM;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

@JMockFunctionRegister(descI18nKey = DOC_SYMMETRIC_KEY_DESC,
    categoryI18nKey = {DOC_CATEGORY_CRYPTO}, order = 6005)
public class MSymmetricKey extends AbstractMockFunction {

  public static final SecureRandom random = new SecureRandom();

  @JMockParameter(descI18nKey = DOC_SYMMETRIC_KEY_PARAMETER_ALGORITHM)
  private final String algorithm;

  @JMockConstructor(descI18nKey = DOC_SYMMETRIC_KEY_C1,
      example = "@SymmetricKey()",
      exampleValues = {"wCnChNn+2WSH6saSatASL+3Bo3HcW8uEz8jHqM7TtQs=", "/IWyWbP42jcWFB47zmLCfWVGK6nnRkrBM/dJqnRt+bU="})
  public MSymmetricKey() {
    this("AES");
  }

  @JMockConstructor(descI18nKey = DOC_SYMMETRIC_KEY_C2,
      example = "@SymmetricKey(AES)",
      exampleValues = {"wiuUG5/rfxB1r9VtxfgO1jy1TLyAmo5xFBm7iOPosAs=", "zHexV+cHC3O9T70eU2FEgSsFTfOmOsD/lJfGQOj90Aw="})
  public MSymmetricKey(String algorithm) {
    this.algorithm = algorithm;
  }

  @Override
  public String mock() {
    SecretKey secretKey = generateRandomSymmetricKey(algorithm);
    if (secretKey == null) {
      return null;
    }
    return keyToString(secretKey);
  }

  public static SecretKey generateRandomSymmetricKey(String algorithm) {
    KeyGenerator keyGen;
    try {
      keyGen = KeyGenerator.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      return null;
    }
    keyGen.init(switch (algorithm) {
      case "AES" -> 256;
      case "DESede" -> 168;
      case "HmacSHA256" -> 256;
      default -> 128;
    }, random);
    return keyGen.generateKey();
  }

  public static String keyToString(SecretKey secretKey) {
    byte[] keyBytes = secretKey.getEncoded();
    return Base64.getEncoder().encodeToString(keyBytes);
  }

}
