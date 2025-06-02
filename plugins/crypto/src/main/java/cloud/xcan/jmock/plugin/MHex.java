package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MSymmetricKey.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.HexFormat;

public class MHex extends AbstractMockFunction {

  private final int length;

  public MHex() {
    this(16);
  }

  public MHex(int length) {
    this.length = length;
  }

  @Override
  public String mock() {
    return generateRandomHexString(length);
  }

  /**
   * Generates a random hexadecimal string
   *
   * @param length Desired string length (must be even)
   * @return Hexadecimal string
   */
  public static String generateRandomHexString(int length) {
    if (length % 2 != 0) {
      throw new IllegalArgumentException("Hex string length must be even");
    }

    byte[] randomBytes = new byte[length / 2];
    random.nextBytes(randomBytes);
    return HexFormat.of().formatHex(randomBytes);
  }

}
