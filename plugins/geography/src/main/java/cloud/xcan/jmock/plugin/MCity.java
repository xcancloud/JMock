package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_CATEGORY_GEOGRAPHY;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_CITY_C1;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_CITY_C2;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_CITY_C3;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_CITY_DESC;
import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_CITY_DESC,
    categoryI18nKey = {DOC_CATEGORY_GEOGRAPHY}, order = 503)
public class MCity extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private String locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_CITY_C1,
      exampleValues = {"北京", "上海"})
  public MCity() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_CITY_C2,
      example = "@City(en)",
      exampleValues = {"Chicago", "New York"})
  public MCity(Locale locale) {
    String province = MessageResources.getString(GeographyDocMessage.DATA_CITY, locale);
    this.dictArray = province.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_CITY_C3,
      example = "@Word(北京|深圳|上海))",
      exampleValues = {"深圳", "上海"})
  public MCity(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
