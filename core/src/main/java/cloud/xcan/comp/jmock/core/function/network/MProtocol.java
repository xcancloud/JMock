package cloud.xcan.comp.jmock.core.function.network;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPROTOCOL_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPROTOCOL_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPROTOCOL_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MPROTOCOL_PARAMETER_DICT;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MPROTOCOL_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 907)
public class MProtocol extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MPROTOCOL_PARAMETER_DICT)
  private String dict;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MPROTOCOL_C1,
      example = "@Protocol()",
      exampleValues = {"SMTP"})
  public MProtocol() {
    this(null);
  }


  @JMockConstructor(descI18nKey = DOC_MPROTOCOL_C2,
      example = "@Protocol(FTP|TFTP|HTTP)",
      exampleValues = {"HTTP"})
  public MProtocol(String dict) {
    if (ObjectUtils.isEmpty(dict)) {
      String protocol = MessageResources.getString(JMockMessage.FDATA_PROTOCOL);
      this.dictArray = protocol.split("\\|");
    } else {
      this.dictArray = dict.split("\\|");
    }
  }

  @Override
  public String mock() {
    Integer dictIndex = RandomUtils.nextInt(0, dictArray.length);
    return dictArray[dictIndex];
  }
}
