package cloud.xcan.jmock.core.support.utils;


import static cloud.xcan.jmock.core.support.utils.RandomStringUtils.random;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
public class EncryptionUtils {

  public static int MD5_16_LENGTH = 16;

  public static int MD5_32_LENGTH = 32;

  public static List<Integer> DEFAULT_MD5_LENGTH_LIST = Arrays.asList(
      MD5_16_LENGTH, MD5_32_LENGTH);

  public static String SHA1_VERSION = "SHA-1";
  public static String SHA224_VERSION = "SHA-224";
  public static String SHA256_VERSION = "SHA-256";
  public static String SHA384_VERSION = "SHA-384";
  public static String SHA512_VERSION = "SHA-512";

  public static List<String> DEFAULT_SHA_VERSION_LIST = Arrays.asList(
      SHA1_VERSION,
      SHA224_VERSION,
      SHA256_VERSION,
      SHA384_VERSION,
      SHA512_VERSION);

  public static String md5Digest(int length) {
    String data = RandomStringUtils.random(6);
    if (MD5_16_LENGTH == length) {
      return md5_16(data);
    } else {
      return md5(data);
    }
  }

  public static String shaDigest(String version) {
    String data = RandomStringUtils.random(6);
    if (StringUtils.equals(SHA1_VERSION, version)) {
      return sha1(data);
    } else if (StringUtils.equals(SHA224_VERSION, version)) {
      return sha224(data);
    } else if (StringUtils.equals(SHA256_VERSION, version)) {
      return sha256(data);
    } else if (StringUtils.equals(SHA384_VERSION, version)) {
      return sha384(data);
    } else {
      return sha512(data);
    }
  }

  public static String md5(String data) {
    return DigestUtils.md5Hex(data);
  }

  public static String md5_16(String data) {
    return DigestUtils.md5Hex(data).substring(8, 24);
  }

  public static String sha1(String data) {
    return DigestUtils.sha1Hex(data);
  }

  public static String sha224(String data) {
    return DigestUtils.sha512_224Hex(data);
  }

  public static String sha256(String data) {
    return DigestUtils.sha256Hex(data);
  }

  public static String sha384(String data) {
    return DigestUtils.sha384Hex(data);
  }

  public static String sha512(String data) {
    return DigestUtils.sha512Hex(data);
  }
}
