package cloud.xcan.comp.jmock.core.function.user;


import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFIRSTNAME_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFIRSTNAME_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFIRSTNAME_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFIRSTNAME_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFIRSTNAME_PARAMETER_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_NOT_BLANK_T;
import static java.util.Locale.CHINA;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MFIRSTNAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 803)
public class MFirstname extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MFIRSTNAME_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MFIRSTNAME_C1,
      example = "@Firstname()",
      exampleValues = {"紫沫", "超俊", "政晧"})
  public MFirstname() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MFIRSTNAME_C2,
      example = "@Firstname(en)",
      exampleValues = {"Aaron", "Alan"})
  public MFirstname(Locale locale) {
    String lastName = MessageResources.getString(JMockMessage.FDATA_FIRSTNAME, locale);
    this.dictArray = lastName.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_MFIRSTNAME_C3,
      example = "@Firstname(逍遥|哪哪|曦曦)",
      exampleValues = {"曦曦", "逍遥"})
  public MFirstname(String dict) {
    if (isEmpty(dict)) {
      ParamParseException.throw0(FPARAM_NOT_BLANK_T, new Object[]{"dict"});
    }
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
