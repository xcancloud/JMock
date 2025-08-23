package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.WebDocMessage.DOC_CATEGORY_WEB;
import static cloud.xcan.jmock.plugin.WebDocMessage.DOC_EMOJI_C1;
import static cloud.xcan.jmock.plugin.WebDocMessage.DOC_EMOJI_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.support.utils.EmojiGeneratorUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_EMOJI_DESC,
    categoryI18nKey = {DOC_CATEGORY_WEB}, order = 1001)
public class MEmoji extends AbstractMockFunction {

  @JMockConstructor(descI18nKey = DOC_EMOJI_C1,
      example = "@Emoji()",
      exampleValues = {"\uD83D\uDE05", "\uD83D\uDE18"})
  public MEmoji() {
  }

  @Override
  public Object mock() {
    return EmojiGeneratorUtils.getRandomEmoji();
  }
}
