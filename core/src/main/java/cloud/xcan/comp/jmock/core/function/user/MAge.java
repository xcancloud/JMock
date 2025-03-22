package cloud.xcan.comp.jmock.core.function.user;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAGE_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAGE_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAGE_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAGE_PARAMETER_MAX;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MAGE_PARAMETER_MIN;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author hao.guo
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MAGE_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 800)
public class MAge extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MAGE_PARAMETER_MIN)
  private int min;

  @JMockParameter(descI18nKey = DOC_MAGE_PARAMETER_MAX)
  private int max;

  /**
   * Max value of Integer allowed
   */
  final static int DEFAULT_MIN_VALUE = 1;

  final static int DEFAULT_MAX_VALUE = 100;

  @JMockConstructor(descI18nKey = DOC_MAGE_C1,
      example = "@String()", exampleValues = {"60"}
  )
  public MAge() {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MAGE_C2,
      example = "@String(1,50)", exampleValues = {"25"}
  )
  public MAge(Integer min, Integer max) {
    if (ObjectUtils.isEmpty(min) || min < DEFAULT_MIN_VALUE) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", 1});
    }
    if (min > max) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", "max"});
    }
    this.min = min;
    this.max = max;
  }

  @Override
  public Integer mock() {
    return RandomUtils.nextInt(this.min, this.max + 1);
  }
}
