package cloud.xcan.comp.jmock.core.function.article;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ARTICLE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPARAGRAPH_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPARAGRAPH_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPARAGRAPH_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPARAGRAPH_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static java.util.Locale.CHINA;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MPARAGRAPH_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARTICLE}, order = 405)
public class MParagraph extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private String locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MPARAGRAPH_C1,
      example = "@Paragraph()",
      exampleValues = {
          "喜欢读诗，不管它是中国的还是外国的，也不管它是古代的还是现代的都爱读。从风格上来说不管它是豪放的还纤柔的，也不管它是浪漫的还是写实的我也同样爱读。因为我认为诗是大自然灵魂精神的体现，诗是社会的律动，诗是人思想意识真情实感的流露。喜欢诗，喜欢读诗，时间一长，也就喜欢练习写诗。古体诗，格律诗，词曲到新诗都学习过，也写过；虽然写得不好，可还是也写了，尝试了。",
          "夜，宛若一副带着点点诗意的画卷，铺展开来。不知道从何时起，喜欢独处，喜欢一个人坐着，纵然对着夜色，是无数的孤独，也深觉挺好。孤独何妨呢?不被打扰，也是一种美丽的享受。无聊时候，敲几行贴心的小字，沉迷夜色的清然与优柔之中。静坐的时光，总有往昔的故事，如同电影一样心头回放，那些曾经的人，那些渐远渐无书的你我，而如今形同陌路了，唯有残留的往事，像是爬满心房的藤蔓，无休无止。"})
  public MParagraph() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MPARAGRAPH_C2,
      example = "@Paragraph(en)",
      exampleValues = {
          "At this moment, you are so far away, just like the summer that went away, there is no longer the enthusiasm of the past, it seems that we are all used to such a quiet parting. The time is gone, the promise is still there, the people are gone, like the arrow from the mstring, even if it is far to the horizon, the tender words are still there. If the affection is deep, I am not afraid of the long-term, having you in my heart is enough to comfort a lonely soul. The cymbals were pale that day, the white dew was frost, and the autumn breeze brought coolness. Xia came quietly, and went quietly. The autumn models came with graceful and graceful steps, lovingly sending the back of Xia Yuan, accompanied by fallen leaves, waiting for the arrival of the next season. Looking at the withered leaves, knowing that some departures are unobstructed, and the empty heart will be filled with the tranquility of the autumn light, so the parting time and time will be warmed by the next meeting.",
          "accidentally met and met. You are over there. I am here. A virtual thread connects us together. We could have become friends, communicate, talk, and sigh. However, we have been indulged in real life for too long. In this masked society, we have lost the most precious and simplest relationship between people-trust!"})
  public MParagraph(Locale locale) {
    String title = MessageResources.getString(JMockMessage.FDATA_PARAGRAPH, locale);
    this.dictArray = title.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_MPARAGRAPH_C3,
      example = "@Paragraph(段落1|段落2)",
      exampleValues = {"段落1", "段落2"})
  public MParagraph(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }

}
