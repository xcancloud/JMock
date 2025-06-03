package cloud.xcan.jmock.core.function.geography;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_GEOGRAPHY;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_ZIP_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_ZIP_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_ZIP_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_ZIP_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_ZIP_PARAMETER_DICT;
import static java.util.Locale.CHINA;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_ZIP_DESC,
    categoryI18nKey = {DOC_CATEGORY_GEOGRAPHY}, order = 520)
public class MZip extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_ZIP_PARAMETER_DICT)
  private String dict;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_ZIP_C1,
      example = "@Zip()",
      exampleValues = {"252863", "252665", "1252866"})
  public MZip() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_ZIP_C2,
      example = "@Zip(en)",
      exampleValues = {"EC1A 1HQ", "1ASA 9AA", "1BSA 4AB"})
  public MZip(Locale locale) {
    String title = MessageResources.getString(JMockMessage.FDATA_ZIP, locale);
    this.dictArray = title.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_ZIP_C3,
      example = "@Zip(101407|101406|101405)",
      exampleValues = {"101407", "101405", "101406"})
  public MZip(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
