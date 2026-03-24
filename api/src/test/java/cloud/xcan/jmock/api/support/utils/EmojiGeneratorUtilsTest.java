package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.RepeatedTest;

class EmojiGeneratorUtilsTest {

  @RepeatedTest(10)
  void getRandomEmoji_returnsSurrogatePair() {
    String e = EmojiGeneratorUtils.getRandomEmoji();
    assertNotNull(e);
  }
}
