package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MHash extends AbstractMockFunction {

  private final String algorithm;

  public MHash() {
    this("MD5");
  }

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
