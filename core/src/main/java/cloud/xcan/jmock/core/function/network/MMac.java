package cloud.xcan.jmock.core.function.network;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MMAC_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MMAC_DESC;
import static cloud.xcan.jmock.core.support.utils.IPUtils.mac;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MMAC_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 905)
public class MMac extends AbstractMockFunction {

  // TODO http://it-tools.xcan.work/mac-address-generator

  @JMockConstructor(descI18nKey = DOC_MMAC_C1,
      example = "@Mac()",
      exampleValues = {"d8:01:8f:96:b1:f4", "65:d2:65:a5:6c:81", "2b:3d:70:76:69:f0"})
  public MMac() {
  }

  @Override
  public String mock() {
    return mac();
  }
}
