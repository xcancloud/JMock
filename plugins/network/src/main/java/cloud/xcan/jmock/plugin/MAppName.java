package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_APP_NAME_C1;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_APP_NAME_C2;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_APP_NAME_DESC;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_CATEGORY_NETWORK;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_APP_NAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 901)
public class MAppName extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_APP_NAME_C1,
      example = "@AppName()",
      exampleValues = {"搜狐新闻", "百度手机浏览器"})
  public MAppName() {
    this(MessageResources.getString(NetworkDocMessage.DATA_APP_NAME));
  }

  @JMockConstructor(descI18nKey = DOC_APP_NAME_C2,
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
