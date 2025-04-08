package cloud.xcan.jmock.core.function.geography;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_GEOGRAPHY;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOUNTRY_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOUNTRY_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOUNTRY_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOUNTRY_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.JMockResources;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author hao.guo
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MCOUNTRY_DESC,
    categoryI18nKey = {DOC_CATEGORY_GEOGRAPHY}, order = 501)
public class MCountry extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String[] dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  final static Locale DEFAULT_LOCALE_VALUE = Locale.CHINA;

  @JMockConstructor(descI18nKey = DOC_MCOUNTRY_C1,
      example = "@Country()",
      exampleValues = {"中国"})
  public MCountry() {
    this(DEFAULT_LOCALE_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MCOUNTRY_C2,
      example = "@Country(zh_CN)",
      exampleValues = {"中国"})
  public MCountry(Locale locale) {
    this(locale, null);
  }

  @JMockConstructor(descI18nKey = DOC_MCOUNTRY_C3,
      example = "@Country(中国,俄罗斯)",
      exampleValues = {"中国"})
  public MCountry(String dict) {
    this(null, dict);
  }

  public MCountry(Locale locale, String dict) {
    if (ObjectUtils.isEmpty(locale)) {
      locale = DEFAULT_LOCALE_VALUE;
    }
    if (ObjectUtils.isEmpty(dict)) {
      String countryResources = MessageResources.getString(JMockResources.COUNTRY, locale);
      String country = new String(countryResources.getBytes(StandardCharsets.ISO_8859_1),
          StandardCharsets.UTF_8);
      this.dict = country.split(",");
    } else {
      this.dict = dict.split(",");
    }
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dict.length);
    return dict[dictIndex];
  }
}
