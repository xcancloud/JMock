package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ColorUtilTest {

  @Test
  void randomHexColor() {
    String c = ColorUtil.randomHexColor();
    assertNotNull(c);
    assertTrue(c.startsWith("#"));
    assertEquals(7, c.length());
  }

  @Test
  void randomRgbColor() {
    assertTrue(ColorUtil.randomRgbColor().startsWith("rgb("));
  }

  @Test
  void randomHslColor() {
    assertTrue(ColorUtil.randomHslColor().startsWith("hsl("));
  }

  @Test
  void randomHwbColor() {
    assertTrue(ColorUtil.randomHwbColor().startsWith("hwb("));
  }

  @Test
  void randomLchColor() {
    assertTrue(ColorUtil.randomLchColor().startsWith("lch("));
  }

  @Test
  void randomCmykColor() {
    assertTrue(ColorUtil.randomCmykColor().startsWith("cmyk("));
  }
}
