package cloud.xcan.jmock.plugin.crypto;

import static cloud.xcan.jmock.plugin.MBase64.generateRandomBase64String;
import static cloud.xcan.jmock.plugin.MHash.generateRandomHash;
import static cloud.xcan.jmock.plugin.MHex.generateRandomHexString;
import static cloud.xcan.jmock.plugin.MKeyPair.generateRandomKeyPair;
import static cloud.xcan.jmock.plugin.MSymmetricKey.generateRandomSymmetricKey;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.KeyPair;
import java.util.Base64;
import java.util.HexFormat;
import javax.crypto.SecretKey;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CryptoDataGeneratorTest {

  @Test
  void testGenerateAESKey() throws Exception {
    SecretKey key = generateRandomSymmetricKey("AES");
    assertNotNull(key);
    assertEquals("AES", key.getAlgorithm());
    assertEquals(32, key.getEncoded().length); // 256-bit key
  }

  @Test
  void testGenerateDESedeKey() throws Exception {
    SecretKey key = generateRandomSymmetricKey("DESede");
    assertNotNull(key);
    assertEquals("DESede", key.getAlgorithm());
    assertEquals(24, key.getEncoded().length); // 168-bit key (24 bytes in Base64)
  }

  @Test
  void testUnsupportedAlgorithm() {
    SecretKey secretKey = generateRandomSymmetricKey("UNKNOWN_ALG");
    assertNull(secretKey);
  }

  @Test
  void testGenerateRSAKeyPair() throws Exception {
    KeyPair keyPair = generateRandomKeyPair("RSA", 2048);
    assertNotNull(keyPair);
    assertEquals("RSA", keyPair.getPublic().getAlgorithm());
  }

  @Test
  void testGenerateECKeyPair() throws Exception {
    KeyPair keyPair = generateRandomKeyPair("EC", 256);
    assertNotNull(keyPair);
    assertEquals("EC", keyPair.getPublic().getAlgorithm());
  }

  @Test
  void testInvalidECKeySize() {
    assertThrows(IllegalArgumentException.class, () -> {
      generateRandomKeyPair("EC", 512);
    });
  }

  @Test
  void testGenerateSHA256Hash() throws Exception {
    String hash = generateRandomHash("SHA-256");
    assertNotNull(hash);
    assertEquals(64, hash.length()); // 256-bit hash = 64 hex chars
    assertTrue(hash.matches("[0-9a-f]{64}"));
  }

  @Test
  void testGenerateMD5Hash() throws Exception {
    String hash = generateRandomHash("MD5");
    assertNotNull(hash);
    assertEquals(32, hash.length()); // 128-bit hash = 32 hex chars
    assertTrue(hash.matches("[0-9a-f]{32}"));
  }

  @Test
  void testUnsupportedHashAlgorithm() {
    String hash = generateRandomHash("UNKNOWN_HASH");
    assertNull(hash);
  }

  @RepeatedTest(5)
  void testHashUniqueness() throws Exception {
    String hash1 = generateRandomHash("SHA-256");
    String hash2 = generateRandomHash("SHA-256");
    assertNotEquals(hash1, hash2);
  }

  @RepeatedTest(10)
  void testGenerateRandomBase64String() {
    for (int length : new int[]{8, 16, 24, 32, 64}) {
      String base64 = generateRandomBase64String(length);
      assertNotNull(base64);
      assertEquals(length, base64.length());

      // Verify valid Base64 characters
      assertTrue(base64.matches("[A-Za-z0-9+/=]+"));

      // Test decoding
      byte[] decoded = Base64.getDecoder().decode(base64);
      assertTrue(decoded.length > 0);
    }
  }

  @Test
  void testBase64LengthAccuracy() {
    // Test exact length requirements
    String base64 = generateRandomBase64String(4);
    assertEquals(4, base64.length());
    assertFalse(base64.contains("=")); // Shouldn't have padding at 4 chars
  }

  @RepeatedTest(10)
  void testGenerateRandomHexString() {
    for (int length : new int[]{8, 16, 32, 64}) {
      String hex = generateRandomHexString(length);
      assertNotNull(hex);
      assertEquals(length, hex.length());
      assertTrue(hex.matches("[0-9a-f]+"));

      // Verify valid hexadecimal
      byte[] bytes = HexFormat.of().parseHex(hex);
      assertEquals(length / 2, bytes.length);
    }
  }

  @Test
  void testHexStringOddLength() {
    assertThrows(IllegalArgumentException.class, () -> {
      generateRandomHexString(15);
    });
  }

  @Test
  void testHexStringCase() {
    String hex = generateRandomHexString(16);
    assertEquals(hex.toLowerCase(), hex); // Should be lowercase
  }

}
