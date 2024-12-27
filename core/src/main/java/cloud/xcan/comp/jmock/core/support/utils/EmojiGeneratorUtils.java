package cloud.xcan.comp.jmock.core.support.utils;

import java.util.Random;

public class EmojiGeneratorUtils {

  private static final String[] EMOJIS = {
      "\uD83D\uDE00", // 😀
      "\uD83D\uDE01", // 😁
      "\uD83D\uDE02", // 😂
      "\uD83D\uDE03", // 😃
      "\uD83D\uDE04", // 😄
      "\uD83D\uDE05", // 😅
      "\uD83D\uDE06", // 😆
      "\uD83D\uDE07", // 😇
      "\uD83D\uDE08", // 😈
      "\uD83D\uDE09", // 😉
      "\uD83D\uDE0A", // 😍
      "\uD83D\uDE0B", // 😋
      "\uD83D\uDE0C", // 😌
      "\uD83D\uDE0D", // 😍
      "\uD83D\uDE0E", // 😎
      "\uD83D\uDE0F", // 😏
      "\uD83D\uDE10", // 😐
      "\uD83D\uDE11", // 😑
      "\uD83D\uDE12", // 😒
      "\uD83D\uDE13", // 😓
      "\uD83D\uDE14", // 😔
      "\uD83D\uDE15", // 😕
      "\uD83D\uDE16", // 😖
      "\uD83D\uDE17", // 😗
      "\uD83D\uDE18", // 😘
      "\uD83D\uDE19", // 😙
      "\uD83D\uDE1A", // 😚
      "\uD83D\uDE1B", // 😛
      "\uD83D\uDE1C", // 😜
      "\uD83D\uDE1D", // 😝
      "\uD83D\uDE1E", // 😞
      "\uD83D\uDE1F", // 😟
      "\uD83D\uDE20", // 😠
      "\uD83D\uDE21", // 😡
      "\uD83D\uDE22", // 😢
      "\uD83D\uDE23", // 😣
      "\uD83D\uDE24", // 😤
      "\uD83D\uDE25", // 😥
      "\uD83D\uDE26", // 😦
      "\uD83D\uDE27", // 😧
      "\uD83D\uDE28", // 😨
      "\uD83D\uDE29", // 😩
      "\uD83D\uDE2A", // 😪
      "\uD83D\uDE2B", // 😫
      "\uD83D\uDE2C", // 😬
      "\uD83D\uDE2D", // 😭
      "\uD83D\uDE2E", // 😮
      "\uD83D\uDE2F", // 😯
      "\uD83D\uDE30", // 😰
      "\uD83D\uDE31", // 😱
      "\uD83D\uDE32", // 😲
      "\uD83D\uDE33", // 😳
      "\uD83D\uDE34", // 😴
      "\uD83D\uDE35", // 😵
      "\uD83D\uDE36", // 😶
      "\uD83D\uDE37", // 😷
      "\uD83D\uDE38", // 😸
      "\uD83D\uDE39", // 😹
      "\uD83D\uDE3A", // 😺
      "\uD83D\uDE3B", // 😻
      "\uD83D\uDE3C", // 😼
      "\uD83D\uDE3D", // 😽
      "\uD83D\uDE3E", // 😾
      "\uD83D\uDE3F", // 😿
      "\uD83D\uDE40", // 😏
      "\uD83D\uDE40", // 😐
      "\uD83D\uDE41", // 😑
      "\uD83D\uDE42", // 😒
      "\uD83D\uDE43", // 😓
      "\uD83D\uDE44", // 😔
      "\uD83D\uDE45", // 😕
      "\uD83D\uDE46", // 😖
      "\uD83D\uDE47", // 😗
      "\uD83D\uDE48", // 😘
      "\uD83D\uDE49", // 😙
      "\uD83D\uDE4A", // 😚
      "\uD83D\uDE4B", // 😛
      "\uD83D\uDE4C", // 😜
      "\uD83D\uDE4D", // 😝
      "\uD83D\uDE4E", // 😞
      "\uD83D\uDE4F", // 😟
      "\uD83D\uDE50", // 😠
      "\uD83D\uDE51", // 😡
      "\uD83D\uDE52", // 😢
      "\uD83D\uDE53", // 😣
      "\uD83D\uDE54", // 😤
      "\uD83D\uDE55", // 😥
      "\uD83D\uDE56", // 😦
      "\uD83D\uDE57", // 😧
      "\uD83D\uDE58", // 😨
      "\uD83D\uDE59", // 😩
      "\uD83D\uDE5A", // 😪
      "\uD83D\uDE5B", // 😫
      "\uD83D\uDE5C", // 😬
      "\uD83D\uDE5D", // 😭
      "\uD83D\uDE5E", // 😮
      "\uD83D\uDE5F", // 😯
      "\uD83D\uDE60", // 😰
      "\uD83D\uDE61", // 😱
      "\uD83D\uDE62", // 😲
      "\uD83D\uDE63", // 😳
      "\uD83D\uDE64", // 😴
      "\uD83D\uDE65", // 😵
      "\uD83D\uDE66", // 😶
      "\uD83D\uDE67", // 😷
      "\uD83D\uDE68", // 😸
      "\uD83D\uDE69", // 😹
      "\uD83D\uDE6A", // 😺
      "\uD83D\uDE6B", // 😻
      "\uD83D\uDE6C", // 😼
      "\uD83D\uDE6D", // 😽
      "\uD83D\uDE6E", // 😾
      "\uD83D\uDE6F", // 😿
      "\uD83D\uDE70", // 🙀
      "\uD83D\uDE71", // 😺
      "\uD83D\uDE72", // 😻
      "\uD83D\uDE73", // 😼
      "\uD83D\uDE74", // 😽
      "\uD83D\uDE75", // 🙀
      "\uD83D\uDE76", // 😿
  };

  private static final Random RANDOM = new Random();

  public static String getRandomEmoji() {
    int index = RANDOM.nextInt(EMOJIS.length);
    return EMOJIS[index];
  }
}
