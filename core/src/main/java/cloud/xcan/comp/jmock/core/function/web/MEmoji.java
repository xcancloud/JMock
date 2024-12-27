package cloud.xcan.comp.jmock.core.function.web;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_WEB;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MEMOJI_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MEMOJI_DESC;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.core.support.utils.EmojiGeneratorUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaolong.liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MEMOJI_DESC,
    categoryI18nKey = {DOC_CATEGORY_WEB}, order = 1001)
public class MEmoji extends AbstractMockFunction {

  @JMockConstructor(descI18nKey = DOC_MEMOJI_C1,
      example = "@Emoji()",
      exampleValues = {"\uD83D\uDE05", "\uD83D\uDE18"})
  public MEmoji() {
  }

  @Override
  public Object mock() {
    return EmojiGeneratorUtils.getRandomEmoji();
  }
}
