package cloud.xcan.comp.jmock.core.support.utils;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

public class EmojiGeneratorTest {

  @Test
  public void testGetRandomEmoji() {
    String emoji = EmojiGeneratorUtils.getRandomEmoji();
    System.out.println(emoji);
    assertNotNull(emoji, "The emoji should not be null");
    assertTrue(emoji.length() > 0, "The emoji should not be empty");
    assertTrue(emoji.matches("\\p{So}"), "The emoji should be a symbol character");
  }

  @Test
  public void testMultipleRandomEmojis() {
    Set<String> emojis = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      String emoji = EmojiGeneratorUtils.getRandomEmoji();
      System.out.println(emoji);
      emojis.add(emoji);
    }
    assertTrue(emojis.size() > 1, "There should be multiple different emojis generated");
  }


}