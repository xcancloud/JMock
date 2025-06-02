package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class MSymmetricKey extends AbstractMockFunction {

  public static final SecureRandom random = new SecureRandom();

  private final String algorithm;

  public MSymmetricKey() {
    this("AES");
  }

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
    KeyGenerator keyGen = null;
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
