package cloud.xcan.jmock.plugin.web;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MColor;
import cloud.xcan.jmock.plugin.MEmoji;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class WebDataGeneratorTest {

  @RepeatedTest(20)
  void testMColor_default() {
    Object color = new MColor().mock();
    assertNotNull(color);
    assertTrue(color.toString().length() >= 3, "Color too short: " + color);
  }

  @Test
  void testMColor_variety() {
    Set<String> colors = new HashSet<>();
    MColor gen = new MColor();
    for (int i = 0; i < 50; i++) {
      colors.add(gen.mock().toString());
    }
    assertTrue(colors.size() >= 5, "Should generate varied colors, got: " + colors.size());
  }

  @RepeatedTest(20)
  void testMEmoji_default() {
    Object emoji = new MEmoji().mock();
    assertNotNull(emoji);
    assertTrue(emoji.toString().length() >= 1, "Emoji too short: " + emoji);
  }

  @Test
  void testMEmoji_variety() {
    Set<String> emojis = new HashSet<>();
    MEmoji gen = new MEmoji();
    for (int i = 0; i < 50; i++) {
      emojis.add(gen.mock().toString());
    }
    assertTrue(emojis.size() >= 5, "Should generate varied emojis, got: " + emojis.size());
  }
}
