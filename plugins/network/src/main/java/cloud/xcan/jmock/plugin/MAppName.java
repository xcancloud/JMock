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
import java.util.Arrays;
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

  private static final String DEFAULT_APP_NAMES = "微信|QQ|示例应用";

  static {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-network-plugin-messages");
  }

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT, type = "String")
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
    this.dictArray = normalizeDict(dict);
  }

  private static String[] normalizeDict(String pipeSeparated) {
    if (pipeSeparated == null) {
      pipeSeparated = "";
    }
    String[] raw = pipeSeparated.split("\\|", -1);
    String[] filtered = Arrays.stream(raw)
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .toArray(String[]::new);
    return filtered.length > 0 ? filtered : DEFAULT_APP_NAMES.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
