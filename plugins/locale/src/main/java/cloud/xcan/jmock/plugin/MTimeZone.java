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
import java.util.Arrays;
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

  private static final String DEFAULT_TIMEZONES =
      "Australia/Darwin|Asia/Shanghai|Europe/Paris|America/New_York|UTC";

  static {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-locale-plugin-messages");
  }

  @JMockParameter(descI18nKey = DOC_TIMEZONE_PARAMETER_DICT)
  private String dict;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_TIMEZONE_C1,
      example = "@TimeZone()",
      exampleValues = {"Australia/Darwin", "Africa/Cairo", "Europe/Paris"})
  public MTimeZone() {
    String title = MessageResources.getString(LocaleDocMessage.DATA_TIMEZONE, CHINA);
    this.dictArray = normalizeDict(title);
  }

  @JMockConstructor(descI18nKey = DOC_TIMEZONE_C2,
      example = "@TimeZone(Australia/Darwin|Australia/Sydney|America/Argentina/Buenos_Aires))",
      exampleValues = {"Australia/Darwin", "America/Argentina/Buenos_Aires"})
  public MTimeZone(String dict) {
    this.dictArray = normalizeDict(dict);
  }

  private static String[] normalizeDict(String pipeSeparated) {
    if (pipeSeparated == null) {
      pipeSeparated = "";
    }
    String[] filtered = Arrays.stream(pipeSeparated.split("\\|", -1))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .toArray(String[]::new);
    return filtered.length > 0 ? filtered : DEFAULT_TIMEZONES.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
