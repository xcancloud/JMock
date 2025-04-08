package cloud.xcan.jmock.core.function.geography;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_GEOGRAPHY;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MADDRESS_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MADDRESS_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MADDRESS_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MADDRESS_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_DICT;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
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
@JMockFunctionRegister(descI18nKey = DOC_MADDRESS_DESC,
    categoryI18nKey = {DOC_CATEGORY_GEOGRAPHY}, order = 504)
public class MAddress extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private String locale;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MADDRESS_C1,
      example = "@Address()",
      exampleValues = {"恩施土家族苗族自治州利川市", "上海市浦东新区迎宾大道6000号"}
  )
  public MAddress() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MADDRESS_C2,
      example = "@Address(en)",
      exampleValues = {"1112 Hermitage Rd NW, Edmonton, AB T5A 4M4",
          "Gangwon-do, Yeongweol, Yeongwol-eup, Hasong-ri, 217-2"}
  )
  public MAddress(Locale locale) {
    String province = MessageResources.getString(JMockMessage.FDATA_ADDRESS, locale);
    this.dictArray = province.split("\\|");
  }

  @JMockConstructor(descI18nKey = DOC_MADDRESS_C3,
      example = "@Address(地址1|地址2|地址3))",
      exampleValues = {"地址3", "地址1"}
  )
  public MAddress(String dict) {
    this.dictArray = dict.split("\\|");
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
