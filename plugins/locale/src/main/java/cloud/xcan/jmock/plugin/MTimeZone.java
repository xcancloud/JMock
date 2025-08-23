package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.LocaleDocMessage.DOC_CATEGORY_LOCALE;
import static cloud.xcan.jmock.plugin.LocaleDocMessage.DOC_TIMEZONE_C1;
import static cloud.xcan.jmock.plugin.LocaleDocMessage.DOC_TIMEZONE_C2;
import static cloud.xcan.jmock.plugin.LocaleDocMessage.DOC_TIMEZONE_DESC;
import static cloud.xcan.jmock.plugin.LocaleDocMessage.DOC_TIMEZONE_PARAMETER_DICT;
import static java.util.Locale.CHINA;

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
@JMockFunctionRegister(descI18nKey = DOC_TIMEZONE_DESC,
    categoryI18nKey = {DOC_CATEGORY_LOCALE}, order = 702)
public class MTimeZone extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_TIMEZONE_PARAMETER_DICT)
  private String dict;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_TIMEZONE_C1,
      example = "@TimeZone()",
      exampleValues = {"Australia/Darwin", "Africa/Cairo", "Europe/Paris"})
  public MTimeZone() {
    String title = MessageResources.getString(LocaleDocMessage.DATA_TIMEZONE, CHINA);
    this.dictArray = title.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_TIMEZONE_C2,
      example = "@TimeZone(Australia/Darwin|Australia/Sydney|America/Argentina/Buenos_Aires))",
      exampleValues = {"Australia/Darwin", "America/Argentina/Buenos_Aires"})
  public MTimeZone(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
