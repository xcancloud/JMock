package cloud.xcan.jmock.core.function.network;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_IPV4_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_IPV4_DESC;
import static cloud.xcan.jmock.core.support.utils.IPUtils.ipV4;

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
@JMockFunctionRegister(descI18nKey = DOC_IPV4_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 903)
public class MIPv4 extends AbstractMockFunction {

  @JMockConstructor(descI18nKey = DOC_IPV4_C1,
      example = "@IPv4()",
      exampleValues = {"185.122.62.64", "56.106.168.69"})
  public MIPv4() {
  }

  @Override
  public String mock() {
    return ipV4();
  }
}
