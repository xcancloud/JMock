package cloud.xcan.jmock.core.function.network;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PROTOCOL_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PROTOCOL_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PROTOCOL_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PROTOCOL_PARAMETER_DICT;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_PROTOCOL_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 907)
public class MProtocol extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PROTOCOL_PARAMETER_DICT)
  private String dict;

  private transient String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_PROTOCOL_C1,
      example = "@Protocol()",
      exampleValues = {"SMTP"})
  public MProtocol() {
    this(null);
  }


  @JMockConstructor(descI18nKey = DOC_PROTOCOL_C2,
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
