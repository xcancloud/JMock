package cloud.xcan.jmock.core.function.network;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PORT_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PORT_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PORT_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PORT_PARAMETER_MAX;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PORT_PARAMETER_MIN;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_NOT_NULL_T;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author hao.guo
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_PORT_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 906)
public class MPort extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_PORT_PARAMETER_MIN)
  private int min;

  @JMockParameter(descI18nKey = DOC_PORT_PARAMETER_MAX)
  private int max;

  /**
   * Max value of Integer allowed
   */
  final static int DEFAULT_MIN_VALUE = 1024;
  final static int DEFAULT_MAX_VALUE = 65535;

  /**
   * No-parameter MockConstructor: @Port()
   */
  @JMockConstructor(descI18nKey = DOC_PORT_C1,
      example = "@Port()",
      exampleValues = {"1025"})
  public MPort() {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }

  /**
   * MockConstructor: @Port()
   */
  @JMockConstructor(descI18nKey = DOC_PORT_C2,
      example = "@Port(5000,10000)",
      exampleValues = {"6000"})
  public MPort(Integer min, Integer max) {

    if (ObjectUtils.isEmpty(min)) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"min"});
    }
    if (min < DEFAULT_MIN_VALUE) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", DEFAULT_MIN_VALUE});
    }
    if (ObjectUtils.isEmpty(max)) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"max"});
    }
    if (max > DEFAULT_MAX_VALUE) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"max"});
    }
    if (min > max) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", "max"});
    }
    this.max = max;
    this.min = min;
  }

  @Override
  public Integer mock() {
    return RandomUtils.nextInt(this.min, this.max + 1);
  }

}
