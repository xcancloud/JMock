package cloud.xcan.comp.jmock.core.support.utils;

import java.util.Random;

public class ColorUtil {

  private static final Random RANDOM = new Random();

  public static String randomHexColor() {
    return String.format("#%06X", RANDOM.nextInt(0xFFFFFF + 1));
  }

  public static String randomRgbColor() {
    int r = RANDOM.nextInt(256);
    int g = RANDOM.nextInt(256);
    int b = RANDOM.nextInt(256);
    return String.format("rgb(%d, %d, %d)", r, g, b);
  }

  public static String randomHslColor() {
    int h = RANDOM.nextInt(360); // Hue: 0-360
    int s = RANDOM.nextInt(101); // Saturation: 0-100
    int l = RANDOM.nextInt(101); // Lightness: 0-100
    return String.format("hsl(%d, %d%%, %d%%)", h, s, l);
  }

  public static String randomHwbColor() {
    int h = RANDOM.nextInt(360); // Hue: 0-360
    int w = RANDOM.nextInt(101); // Whiteness: 0-100
    int b = RANDOM.nextInt(101); // Blackness: 0-100
    return String.format("hwb(%d, %d%%, %d%%)", h, w, b);
  }

  public static String randomLchColor() {
    int l = RANDOM.nextInt(101); // Lightness: 0-100
    int c = RANDOM.nextInt(101); // Chroma: 0-100
    int h = RANDOM.nextInt(360); // Hue: 0-360
    return String.format("lch(%d, %d, %d)", l, c, h);
  }

  public static String randomCmykColor() {
    int c = RANDOM.nextInt(101); // Cyan: 0-100
    int m = RANDOM.nextInt(101); // Magenta: 0-100
    int y = RANDOM.nextInt(101); // Yellow: 0-100
    int k = RANDOM.nextInt(101); // Black: 0-100
    return String.format("cmyk(%d%%, %d%%, %d%%, %d%%)", c, m, y, k);
  }
}
