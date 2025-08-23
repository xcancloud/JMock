package cloud.xcan.jmock.plugin;


import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_NOT_BLANK_T;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_LASTNAME_C1;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_LASTNAME_C2;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_LASTNAME_C3;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_LASTNAME_DESC;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_LASTNAME_PARAMETER_DICT;
import static java.util.Locale.CHINA;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
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
@JMockFunctionRegister(descI18nKey = DOC_LASTNAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 804)
public class MLastname extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_LASTNAME_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  private String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_LASTNAME_C1,
      example = "@Lastname()", exampleValues = {"孙", "林", "梁"})
  public MLastname() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_LASTNAME_C2,
      example = "@Lastname(en)", exampleValues = {"Sun", "Zhang"})
  public MLastname(Locale locale) {
    String lastName = MessageResources.getString(UserDocMessage.DATA_LASTNAME, locale);
    this.dictArray = lastName.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_LASTNAME_C3,
      example = "@Lastname(李|王|张|刘|陈)", exampleValues = {"张", "王"})
  public MLastname(String dict) {
    if (isEmpty(dict)) {
      ParamParseException.throw0(PARAM_NOT_BLANK_T, new Object[]{"dict"});
    }
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
