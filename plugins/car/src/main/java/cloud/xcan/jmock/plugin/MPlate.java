package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MPlate extends AbstractMockFunction {

  // License plate generation constants
  private static final String CN_PROVINCES = "京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼";
  private static final String LETTERS = "ABCDEFGHJKLMNPQRSTUVWXYZ"; // Excluding O and I
  private static final String DIGITS = "0123456789";
  private static final String LETTERS_NO_CONFUSION = "ABCDEFGHJKLMNPQRSTUVWXYZ0123456789"; // Excluding O and I

  // US license plate templates
  private static final List<String> US_FORMATS = Arrays.asList(
      // 3 letters - 3 digits (AAA-111)
      LETTERS_NO_CONFUSION + "###",
      // 3 digits - 3 letters (111-AAA)
      "###" + LETTERS_NO_CONFUSION,
      // 1 letter - 3 digits - 2 letters (A111AA)
      LETTERS_NO_CONFUSION + "#" + LETTERS_NO_CONFUSION + LETTERS_NO_CONFUSION
          + LETTERS_NO_CONFUSION + LETTERS_NO_CONFUSION,
      // 2 letters - 3 digits - 1 letter (AA111A)
      LETTERS_NO_CONFUSION + LETTERS_NO_CONFUSION + "#" + LETTERS_NO_CONFUSION
  );

  public MPlate() {
  }

  @Override
  public String mock() {
    if (random.nextDouble() < 0.5) {
      return generateChineseLicensePlate();
    } else {
      return generateUSLicensePlate();
    }
  }

  /**
   * Generates a Chinese license plate Format: Province abbreviation + registration authority +
   * separator + 5 alphanumeric characters
   */
  private static String generateChineseLicensePlate() {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    StringBuilder plate = new StringBuilder();

    // Province abbreviation + registration authority letter
    plate.append(CN_PROVINCES.charAt(rand.nextInt(CN_PROVINCES.length())))
        .append(LETTERS.charAt(rand.nextInt(LETTERS.length())));

    // Separator (dot or space with 50% probability)
    if (rand.nextBoolean()) {
      plate.append("·");
    } else {
      plate.append(" ");
    }

    // Serial number (5 alphanumeric characters, last character must be digit)
    for (int i = 0; i < 5; i++) {
      if (i == 4) {
        plate.append(DIGITS.charAt(rand.nextInt(DIGITS.length())));
      } else {
        plate.append(LETTERS_NO_CONFUSION.charAt(rand.nextInt(LETTERS_NO_CONFUSION.length())));
      }
    }

    return plate.toString();
  }

  /**
   * Generates a US license plate using random templates
   */
  private static String generateUSLicensePlate() {
    ThreadLocalRandom rand = ThreadLocalRandom.current();
    String format = US_FORMATS.get(rand.nextInt(US_FORMATS.size()));
    StringBuilder plate = new StringBuilder();

    // Build plate based on template format
    for (char c : format.toCharArray()) {
      if (c == '#') {
        plate.append(DIGITS.charAt(rand.nextInt(DIGITS.length())));
      } else {
        plate.append(c);
      }
    }

    return plate.toString();
  }
}
