package cloud.xcan.comp.jmock.core.function.user;


import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MNAME_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MNAME_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MNAME_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MNAME_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MNAME_PARAMETER_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static java.util.Locale.CHINA;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MNAME_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 801)
public class MName extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MNAME_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String locale;

  private transient String[] dictArray;

  private transient String[] lastnameArray;
  private transient String[] firstnameArray;

  @JMockConstructor(descI18nKey = DOC_MNAME_C1,
      example = "@Name()",
      exampleValues = {"郑倾宇", "何政贤"})
  public MName() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MNAME_C2,
      example = "@Name(en)",
      exampleValues = {"Chris Jack", "Abra Abel"})
  public MName(Locale locale) {
    String lastname = MessageResources.getString(JMockMessage.FDATA_LASTNAME, locale);
    String firstname = MessageResources.getString(JMockMessage.FDATA_FIRSTNAME, locale);
    this.lastnameArray = lastname.split("\\|");
    this.firstnameArray = firstname.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_MNAME_C3,
      example = "@Name(欧阳娜娜|贾玲)",
      exampleValues = {"欧阳娜娜", "贾玲"})
  public MName(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    if (Objects.isNull(dictArray)) {
      Integer lastnameIndex = RandomUtils.nextInt(0, lastnameArray.length);
      Integer firstnameIndex = RandomUtils.nextInt(0, firstnameArray.length);
      return lastnameArray[lastnameIndex] + firstnameArray[firstnameIndex];
    }
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
