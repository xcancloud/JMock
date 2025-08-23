package cloud.xcan.jmock.plugin;


import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_GENDER_C1;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_GENDER_C2;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_GENDER_C3;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_GENDER_DESC;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_GENDER_PARAMETER_DICT;
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
@JMockFunctionRegister(descI18nKey = DOC_GENDER_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 807)
public class MGender extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_GENDER_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String locale;

  private String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_GENDER_C1,
      example = "@Gender()",
      exampleValues = {"男", "女"})
  public MGender() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_GENDER_C2,
      example = "@Gender(en)",
      exampleValues = {"male", "female"})
  public MGender(Locale locale) {
    String lastName = MessageResources.getString(UserDocMessage.DATA_GENDER, locale);
    this.dictArray = lastName.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_GENDER_C3,
      example = "@Gender(F|M))",
      exampleValues = {"F", "M"})
  public MGender(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
