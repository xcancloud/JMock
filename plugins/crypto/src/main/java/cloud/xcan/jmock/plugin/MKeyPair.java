package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_CATEGORY_CRYPTO;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_KEY_PAIR_C1;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_KEY_PAIR_C2;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_KEY_PAIR_DESC;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_KEY_PAIR_PARAMETER_ALGORITHM;
import static cloud.xcan.jmock.plugin.CryptoDocMessage.DOC_KEY_PAIR_PARAMETER_KEY_SIZE;
import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

@JMockFunctionRegister(descI18nKey = DOC_KEY_PAIR_DESC,
    categoryI18nKey = {DOC_CATEGORY_CRYPTO}, order = 6004)
public class MKeyPair extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_KEY_PAIR_PARAMETER_ALGORITHM)
  private final String algorithm;

  @JMockParameter(descI18nKey = DOC_KEY_PAIR_PARAMETER_KEY_SIZE)
  private final int keySize;

  @JMockConstructor(descI18nKey = DOC_KEY_PAIR_C1,
      example = "@KeyPair()",
      exampleValues = {"""
          Public Key:
          -----BEGIN PUBLIC KEY-----
          MF0wDQYJKoZIhvcNAQEBBQADTAAwSQJCINq6aF16xfELo/7qNjGxYCiBjMWvGCSi
          uIWuel3qr/6y5tEaSF28kt9kuXUH+WRSiBRkjMlh+2Msi0BQPamOR6F5AgMBAAE=
          -----END PUBLIC KEY-----

          Private Key:
          -----BEGIN PRIVATE KEY-----
          MIIBWQIBADANBgkqhkiG9w0BAQEFAASCAUMwggE/AgEAAkIg2rpoXXrF8Quj/uo2
          MbFgKIGMxa8YJKK4ha56Xeqv/rLm0RpIXbyS32S5dQf5ZFKIFGSMyWH7YyyLQFA9
          qY5HoXkCAwEAAQJCCBBHEhhnaq+nsL9/EoANzdEDQt6+A8nyGiHNWgE0+a8kYlGd
          gqIU54IkjxvCW+j2alI045v2O4g0g2YYVwjkxnuRAiFSJDI3zxKV2kjM8iMv4E2K
          utyrI/U/7J1TVCHFJAYjJN0CIWZkr1aLQ/YAIGBOmqBegGjRHbpy5UzDoyKN6yFl
          j4mHTQIhMQYdWqWhyAIRdRAG5CoQ3X2M+i9pzg8gDn2l8g0ABLWZAiFH7IOphAvJ
          +g7D95LwAVGrzvBV7q0Y/fdSp2O5wtBPp6UCIU4MJyqLslRHwBgg/bFbp+oH8YjR
          m8UhZPMtr2RsE1i52g==
          -----END PRIVATE KEY-----"""})
  public MKeyPair() {
    this("RSA", 526);
  }

  @JMockConstructor(descI18nKey = DOC_KEY_PAIR_C2,
      example = "@KeyPair(EC,256)",
      exampleValues = {"""
          Public Key:
          -----BEGIN PUBLIC KEY-----
          MF0wDQYJKoZIhvcNAQEBBQADTAAwSQJCNFUTq6yj/nhluxtj+sRNFl2L/AGuDNUs
          hw8gdRcQBPShH0Q+wlxjLLESu2EXHpvtCgRVUajCV240WRy9hhis5HdPAgMBAAE=
          -----END PUBLIC KEY-----

          Private Key:
          -----BEGIN PRIVATE KEY-----
          MIIBWQIBADANBgkqhkiG9w0BAQEFAASCAUMwggE/AgEAAkI0VROrrKP+eGW7G2P6
          xE0WXYv8Aa4M1SyHDyB1FxAE9KEfRD7CXGMssRK7YRcem+0KBFVRqMJXbjRZHL2G
          GKzkd08CAwEAAQJCB0mjvCAGFG1iyVwmn6jPjWJImLcJLGtRUJuYKwYjqnUAjLYC
          /7JjZpZ8z+6QBxXV9tz5ynIAccbRkxpCnqEQC/QlAiF+BDX++1uoOnLvfQHb0mN0
          4R47FXCCJv1SmHVjUpVv55UCIWpP55219mvomEuXyaXAi8PQkzULNZCTbnRvndil
          ZOpaUwIhNthIU9g79QSzPy4N4Ak6BgVwLdDP7aVgfVEjTf4EUfbdAiFMWpkyFwcl
          10bSO/Dyn8iB2aJwXV2aOZkB/Uwz1g4VlssCIX2TEF/qTyy4PAGQUwNeGIWo4h6a
          yOf7XXnOoEYUPaP2gw==
          -----END PRIVATE KEY-----"""})
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
    if (keyPair == null) {
      return null;
    }

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
