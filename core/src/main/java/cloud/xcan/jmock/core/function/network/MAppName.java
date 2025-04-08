package cloud.xcan.jmock.core.function.network;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_NAME_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_NAME_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MAPP_NAME_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MAPP_NAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 901)
public class MAppName extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  //  @JMockFunctionRegister
  //  private String locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MAPP_NAME_C1,
      example = "@AppName()",
      exampleValues = {"搜狐新闻", "百度手机浏览器"})
  public MAppName() {
    this(MessageResources.getString(JMockMessage.FDATA_APP_NAME));
  }

  //  @JMockFunctionRegister(nameKey = "",
  //      example = "@AppName(en)",
  //      exampleValues = {"Cash App", "TikTok"})
  //  public MAppName(Locale locale) {
  //    String province = MessageResources.getString(JMockMessage.FDATA_APP_NAME, locale);
  //    this.dictArray = province.split("\\|");
  //  }

  @JMockConstructor(descI18nKey = DOC_MAPP_NAME_C2,
      example = "@AppName(星链|360|ie))",
      exampleValues = {"360", "星链"})
  public MAppName(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
