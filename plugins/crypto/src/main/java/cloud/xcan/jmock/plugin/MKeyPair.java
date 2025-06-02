package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class MKeyPair extends AbstractMockFunction {

  private final String algorithm;
  private final int keySize;

  public MKeyPair() {
    this("RSA", 256);
  }

  public MKeyPair(String algorithm, int keySize) {
    this.algorithm = algorithm;
    this.keySize = keySize;
  }

  @Override
  public String mock() {
    KeyPairStrings keyPairStrings = generateRandomKeyPairAsStrings(algorithm, keySize);
    if (keyPairStrings == null) {
      return null;
    }
    return keyPairStrings.toString();
  }

  /**
   * Generates a random asymmetric key pair
   *
   * @param algorithm Key algorithm ("RSA", "EC", "DSA")
   * @param keySize   Key size in bits (e.g., 2048 for RSA, 256 for EC)
   * @return KeyPair instance
   */
  public static KeyPair generateRandomKeyPair(String algorithm, int keySize) {
    KeyPairGenerator keyPairGen = null;
    try {
      keyPairGen = KeyPairGenerator.getInstance(algorithm);
    } catch (NoSuchAlgorithmException e) {
      return null;
    }

    switch (algorithm) {
      case "RSA", "DSA" -> keyPairGen.initialize(keySize, random);
      case "EC" -> {
        String curveName = switch (keySize) {
          case 256 -> "secp256r1";
          case 384 -> "secp384r1";
          case 521 -> "secp521r1";
          default -> throw new IllegalArgumentException("Unsupported EC key size: " + keySize);
        };
        try {
          keyPairGen.initialize(new ECGenParameterSpec(curveName), random);
        } catch (InvalidAlgorithmParameterException e) {
          return null;
        }
      }
      default -> {
        return null;
      }
    }

    return keyPairGen.generateKeyPair();
  }

  public static KeyPairStrings generateRandomKeyPairAsStrings(String algorithm, int keySize) {
    KeyPair keyPair = generateRandomKeyPair(algorithm, keySize);
    if (keyPair == null) return null;

    return new KeyPairStrings(
        publicKeyToString(keyPair.getPublic()),
        privateKeyToString(keyPair.getPrivate())
    );
  }

  public static String publicKeyToString(PublicKey publicKey) {
    byte[] encoded = publicKey.getEncoded();

    String base64 = Base64.getEncoder().encodeToString(encoded);
    return "-----BEGIN PUBLIC KEY-----\n" +
        wrapText(base64, 64) +
        "\n-----END PUBLIC KEY-----";
  }

  public static String privateKeyToString(PrivateKey privateKey) {
    byte[] encoded = privateKey.getEncoded();

    String base64 = Base64.getEncoder().encodeToString(encoded);
    return "-----BEGIN PRIVATE KEY-----\n" +
        wrapText(base64, 64) +
        "\n-----END PRIVATE KEY-----";
  }

  private static String wrapText(String text, int lineLength) {
    StringBuilder result = new StringBuilder();
    int index = 0;
    while (index < text.length()) {
      int endIndex = Math.min(index + lineLength, text.length());
      result.append(text.substring(index, endIndex)).append("\n");
      index = endIndex;
    }
    return result.toString().trim();
  }

  public static class KeyPairStrings {

    private final String publicKey;
    private final String privateKey;

    public KeyPairStrings(String publicKey, String privateKey) {
      this.publicKey = publicKey;
      this.privateKey = privateKey;
    }

    public String getPublicKey() {
      return publicKey;
    }

    public String getPrivateKey() {
      return privateKey;
    }

    @Override
    public String toString() {
      return "Public Key:\n" + publicKey + "\n\nPrivate Key:\n" + privateKey;
    }
  }

}
