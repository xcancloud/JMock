package cloud.xcan.comp.jmock.core.function.article;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ARTICLE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTANGPOETRY_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTANGPOETRY_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTANGPOETRY_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTANGPOETRY_DESC;
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
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MTANGPOETRY_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARTICLE}, order = 404)
public class MTangPoetry extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private String locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MTANGPOETRY_C1,
      example = "@TangPoetry()",
      exampleValues = {
          "柳宗元\n"
              + "晨诣超师院读禅经\n"
              + "汲井漱寒齿，清心拂尘服，\n"
              + "闲持贝叶书，步出东斋读。\n"
              + "真源了无取，忘迹世所逐；\n"
              + "遗言冀可冥，缮性何由熟？\n"
              + "道人庭宇静，苔色连深竹；\n"
              + "日出雾露余，青松如膏沐。\n"
              + "澹然离言说，悟悦心自足。",
          "韦应物\n"
              + "兵卫森画戟，宴寝凝清香。\n"
              + "海上风雨至，逍遥池阁凉。\n"
              + "烦疴近消散，嘉宾复满堂。\n"
              + "自惭居处崇，未睹斯民康。\n"
              + "理会是非遣，性达形迹忘。\n"
              + "鲜肥属时禁，蔬果幸见尝。\n"
              + "俯饮一杯酒，仰聆金玉章。\n"
              + "神欢体自轻，意欲凌风翔。\n"
              + "吴中盛文史，群彦今汪洋。\n"
              + "方知大藩地，岂曰财赋强。"})
  public MTangPoetry() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MTANGPOETRY_C2,
      example = "@TangPoetry(en)",
      exampleValues = {
          "Qiu Wei\n"
              + "AFTER MISSING THE RECLUSE \n"
              + "ON THE WESTERN MOUNTAIN\n"
              + "To your hermitage here on the top of the mountain \n"
              + "I have climbed, without stopping, these ten miles. \n"
              + "I have knocked at your door, and no one answered; \n"
              + "I have peeped into your room, at your seat beside the table. \n"
              + "Perhaps you are out riding in your canopied chair, \n"
              + "Or fishing, more likely, in some autumn pool. \n"
              + "Sorry though I am to be missing you, \n"
              + "You have become my meditation -- \n"
              + "The beauty of your grasses, fresh with rain, \n"
              + "And close beside your window the music of your pines. \n"
              + "I take into my being all that I see and hear, \n"
              + "Soothing my senses, quieting my heart; \n"
              + "And though there be neither host nor guest, \n"
              + "Have I not reasoned a visit complete? \n"
              + "...After enough, I have gone down the mountain. \n"
              + "Why should I wait for you any longer? ",
          "Wang Wei\n"
              + "AT PARTING\n"
              + "I dismount from my horse and I offer you wine, \n"
              + "And I ask you where you are going and why. \n"
              + "And you answer: \"I am discontent \n"
              + "And would rest at the foot of the southern mountain. \n"
              + "So give me leave and ask me no questions. \n"
              + "White clouds pass there without end.\" "})
  public MTangPoetry(Locale locale) {
    String title = MessageResources.getString(JMockMessage.FDATA_TANG_POETRY, locale);
    this.dictArray = title.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_MTANGPOETRY_C3,
      example = "@TangPoetry(唐诗1|唐诗2)",
      exampleValues = {"唐诗1", "唐诗2"})
  public MTangPoetry(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }

}
