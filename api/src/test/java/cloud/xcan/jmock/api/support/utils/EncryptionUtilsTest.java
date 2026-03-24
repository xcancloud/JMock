package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EncryptionUtilsTest {

  @Test
  void md5_and_md5_16() {
    String full = EncryptionUtils.md5("hello");
    assertEquals(32, full.length());
    String half = EncryptionUtils.md5_16("hello");
    assertEquals(16, half.length());
    assertTrue(full.contains(half));
  }

  @Test
  void md5Digest_lengths() {
    assertEquals(16, EncryptionUtils.md5Digest(EncryptionUtils.MD5_16_LENGTH).length());
    assertEquals(32, EncryptionUtils.md5Digest(EncryptionUtils.MD5_32_LENGTH).length());
  }

  @Test
  void shaVariants() {
    assertNotNull(EncryptionUtils.shaDigest(EncryptionUtils.SHA1_VERSION));
    assertNotNull(EncryptionUtils.shaDigest(EncryptionUtils.SHA224_VERSION));
    assertNotNull(EncryptionUtils.shaDigest(EncryptionUtils.SHA256_VERSION));
    assertNotNull(EncryptionUtils.shaDigest(EncryptionUtils.SHA384_VERSION));
    assertNotNull(EncryptionUtils.shaDigest(EncryptionUtils.SHA512_VERSION));
    assertNotNull(EncryptionUtils.shaDigest("unknown")); // falls through to sha512
  }

  @Test
  void directSha() {
    assertEquals(40, EncryptionUtils.sha1("a").length());
    assertTrue(EncryptionUtils.sha256("b").length() >= 32);
  }

  @Test
  void defaultConstantLists_nonEmpty() {
    assertFalse(EncryptionUtils.DEFAULT_MD5_LENGTH_LIST.isEmpty());
    assertFalse(EncryptionUtils.DEFAULT_SHA_VERSION_LIST.isEmpty());
  }
}
