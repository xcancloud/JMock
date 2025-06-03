package cloud.xcan.jmock.core.function.locale;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_LOCALE;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_LOCALE_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_LOCALE_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_LOCALE_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_LOCALE_PARAMETER_JOINER;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_LENGTH_T;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter
@JMockFunctionRegister(descI18nKey = DOC_LOCALE_DESC,
    categoryI18nKey = {DOC_CATEGORY_LOCALE}, order = 701)
public class MLocale extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_LOCALE_PARAMETER_JOINER)
  private String joiner;

  /**
   * Default connector
   */
  public static final String DEFAULT_JOINER = "_";

  /**
   * Default build language range
   */
  static Locale[] locales = {Locale.SIMPLIFIED_CHINESE, Locale.ENGLISH};

  @JMockConstructor(descI18nKey = DOC_LOCALE_C1,
      example = "@Locale()",
      exampleValues = {"zh_CN"})
  public MLocale() {
    this(DEFAULT_JOINER);
  }

  /**
   * Generate international configuration, including language and country area.
   *
   * @param joiner Country and language connector. One character is supported, "_" is used by
   *               default
   */
  @JMockConstructor(descI18nKey = DOC_LOCALE_C2,
      example = "@Locale(-)",
      exampleValues = {"zh-CN"})
  public MLocale(String joiner) {
    if (joiner == null || joiner.isEmpty()) {
      this.joiner = DEFAULT_JOINER;
    } else {
      if (joiner.length() > 1) {
        ParamParseException.throw0(FPARAM_LENGTH_T, new Object[]{"joiner", 1});
      }
      this.joiner = joiner;
    }
  }

  @Override
  public String mock() {
    Locale locale = locales[RandomUtils.RANDOM.nextInt(locales.length)];
    StringBuilder sb = new StringBuilder(locale.getLanguage());
    if (StringUtils.isNotEmpty(locale.getCountry())) {
      sb.append(joiner);
      sb.append(locale.getCountry());
    }
    return sb.toString();
  }

}
