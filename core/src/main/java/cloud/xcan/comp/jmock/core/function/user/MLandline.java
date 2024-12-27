package cloud.xcan.comp.jmock.core.function.user;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLANDINE_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLANDINE_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLANDINE_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.comp.jmock.core.support.utils.RandomStringUtils.randomNum;
import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

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
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MLANDINE_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 805)
public class MLandline extends AbstractMockFunction {

  public static final String CN_DEFAULT_AREA_CODE = "010";
  public static final String EN_DEFAULT_COUNTRY_CODE = "0044-";
  public static final String EN_DEFAULT_AREA_CODE = " 207";
  public static final int EN_DEFAULT_NUM = 7;
  public static final int CN_DEFAULT_NUM = 8;

  private String countryCode;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  private String realAreaCode;

  private int num;

  private String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MLANDINE_C1,
      example = "@MLandline()", exampleValues = {"", ""})
  public MLandline() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MLANDINE_C2,
      example = "@MLandline(en)", exampleValues = {"", ""})
  public MLandline(Locale locale) {
    if (CHINA.equals(locale)) {
      this.num = CN_DEFAULT_NUM;
      String landLine = MessageResources.getString(JMockMessage.FDATA_LANDLINE, locale);
      this.dictArray = landLine.split("\\|");
      countryCode = StringUtils.EMPTY;
    } else if (ENGLISH.equals(locale)) {
      this.num = EN_DEFAULT_NUM;
      String landLine = MessageResources.getString(JMockMessage.FDATA_LANDLINE, locale);
      this.dictArray = landLine.split("\\|");
      countryCode = EN_DEFAULT_COUNTRY_CODE;
    }
  }

  @Override
  public String mock() {
    realAreaCode = this.countryCode + dictArray[RandomUtils.nextInt(0, dictArray.length)] + "-";
    return realAreaCode + randomNum(this.num);
  }

}
