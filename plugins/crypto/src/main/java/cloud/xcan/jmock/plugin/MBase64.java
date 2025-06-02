package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Base64;

public class MBase64 extends AbstractMockFunction {

  private final int length;

  public MBase64() {
    this(128);
  }

  public MBase64(int length) {
    this.length = length;
  }

  @Override
  public String mock() {
    return generateRandomBase64String(length);
  }

  /**
   * Generates a random Base64 encoded string
   *
   * @param length Desired output string length
   * @return Base64 encoded random string
   */
  public static String generateRandomBase64String(int length) {
    // Calculate required byte length (Base64 uses 4 chars for every 3 bytes)
    int byteLength = (int) Math.ceil(length * 3.0 / 4);
    byte[] randomBytes = new byte[byteLength];
    random.nextBytes(randomBytes);

    String base64 = Base64.getEncoder().encodeToString(randomBytes);
    return base64.substring(0, Math.min(length, base64.length()));
  }

}
