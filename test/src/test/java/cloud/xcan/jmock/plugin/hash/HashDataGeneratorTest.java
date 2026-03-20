package cloud.xcan.jmock.plugin.hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MMd5;
import cloud.xcan.jmock.plugin.MSha;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class HashDataGeneratorTest {

  @RepeatedTest(20)
  void testMMd5_default() {
    String md5 = new MMd5().mock();
    assertNotNull(md5);
    assertEquals(32, md5.length(), "MD5 should be 32 hex chars: " + md5);
    assertTrue(md5.matches("[0-9a-fA-F]{32}"), "MD5 should be hex: " + md5);
  }

  @Test
  void testMMd5_uniqueness() {
    Set<String> hashes = new HashSet<>();
    MMd5 gen = new MMd5();
    for (int i = 0; i < 50; i++) {
      hashes.add(gen.mock());
    }
    assertTrue(hashes.size() >= 40, "MD5 hashes should be unique, got: " + hashes.size());
  }

  @RepeatedTest(20)
  void testMSha_default() {
    String sha = new MSha().mock();
    assertNotNull(sha);
    assertTrue(sha.length() >= 40, "SHA hash too short: " + sha);
    assertTrue(sha.matches("[0-9a-fA-F]+"), "SHA should be hex: " + sha);
  }

  @Test
  void testMSha_uniqueness() {
    Set<String> hashes = new HashSet<>();
    MSha gen = new MSha();
    for (int i = 0; i < 50; i++) {
      hashes.add(gen.mock());
    }
    assertTrue(hashes.size() >= 40, "SHA hashes should be unique, got: " + hashes.size());
  }

  @Test
  void testMSha_differentVersions() {
    String sha256 = new MSha("256").mock();
    assertNotNull(sha256);

    String sha512 = new MSha("512").mock();
    assertNotNull(sha512);

    assertNotEquals(sha256.length(), sha512.length(),
        "SHA-256 and SHA-512 should have different lengths");
  }
}
