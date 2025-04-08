package cloud.xcan.jmock.core.support.utils;

import org.junit.Test;

public class ColorUtilTest {

  @Test
  public void testRandomHexColor() {
    String hexColor = ColorUtil.randomHexColor();
    System.out.println("hexColor: " + hexColor);
  }

  @Test
  public void testRandomRgbColor() {
    String rgbColor = ColorUtil.randomRgbColor();
    System.out.println("rgbColor: " + rgbColor);
  }

  @Test
  public void testRandomHslColor() {
    String hslColor = ColorUtil.randomHslColor();
    System.out.println("hslColor: " + hslColor);
  }

  @Test
  public void testRandomHwbColor() {
    String hwbColor = ColorUtil.randomHwbColor();
    System.out.println("hwbColor: " + hwbColor);
  }

  @Test
  public void testRandomLchColor() {
    String lchColor = ColorUtil.randomLchColor();
    System.out.println("lchColor: " + lchColor);
  }

  @Test
  public void testRandomCmykColor() {
    String cmykColor = ColorUtil.randomCmykColor();
    System.out.println("cmykColor: " + cmykColor);
  }
}
