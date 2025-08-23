package cloud.xcan.jmock.api.support.utils;

import java.security.SecureRandom;

public class UUIDUtils {

  /*
   * The most significant 64 bits of this UUID.
   *
   * @serial
   */
  private final long mostSigBits;

  /*
   * The least significant 64 bits of this UUID.
   *
   * @serial
   */
  private final long leastSigBits;

  /*
   * The random number replacer used by this class to create random
   * based UUIDs. In a holder class to defer initialization until needed.
   */
  private static class Holder {

    static final SecureRandom numberGenerator = new SecureRandom();
  }

  // MockConstructor and Factories

  /*
   * Private constructor which uses a byte array to construct the new UUID.
   */
  private UUIDUtils(byte[] data) {
    long msb = 0;
    long lsb = 0;
    assert data.length == 16 : "data must be 16 bytes in length";
    for (int i = 0; i < 8; i++) {
      msb = (msb << 8) | (data[i] & 0xff);
    }
    for (int i = 8; i < 16; i++) {
      lsb = (lsb << 8) | (data[i] & 0xff);
    }
    this.mostSigBits = msb;
    this.leastSigBits = lsb;
  }


  /**
   * Static factory to retrieve a type 4 (pseudo randomly generated) UUID.
   * <p>
   * The {@code UUID} is generated using a cryptographically strong pseudo random number replacer.
   *
   * @return A randomly generated {@code UUID}
   */
  public static String randomStringUUID(Boolean withoutSeparator) {
    SecureRandom ng = Holder.numberGenerator;
    byte[] randomBytes = new byte[16];
    ng.nextBytes(randomBytes);
    randomBytes[6] &= 0x0f;
    randomBytes[6] |= 0x40;
    randomBytes[8] &= 0x3f;
    randomBytes[8] |= 0x80;
    return new UUIDUtils(randomBytes).toUUIDString(withoutSeparator);
  }

  private String toUUIDString(Boolean withoutSeparator) {
    String separator = withoutSeparator ? "" : "-";
    return (digits(mostSigBits >> 32, 8) + separator +
        digits(mostSigBits >> 16, 4) + separator +
        digits(mostSigBits, 4) + separator +
        digits(leastSigBits >> 48, 4) + separator +
        digits(leastSigBits, 12));
  }

  /**
   * Returns val represented by the specified number of hex digits.
   */
  private static String digits(long val, int digits) {
    long hi = 1L << (digits * 4);
    return Long.toHexString(hi | (val & (hi - 1))).substring(1);
  }
}
