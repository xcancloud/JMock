package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_MIN_T;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_AGE_C1;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_AGE_C2;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_AGE_DESC;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_AGE_PARAMETER_MAX;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_AGE_PARAMETER_MIN;
import static cloud.xcan.jmock.plugin.UserDocMessage.DOC_CATEGORY_USER;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author Hao Guo
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_AGE_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 800)
public class MAge extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_AGE_PARAMETER_MIN)
  private int min;

  @JMockParameter(descI18nKey = DOC_AGE_PARAMETER_MAX)
  private int max;

  /**
   * Max value of Integer allowed
   */
  final static int DEFAULT_MIN_VALUE = 1;

  final static int DEFAULT_MAX_VALUE = 100;

  @JMockConstructor(descI18nKey = DOC_AGE_C1,
      example = "@String()", exampleValues = {"60"}
  )
  public MAge() {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_AGE_C2,
      example = "@String(1,50)", exampleValues = {"25"}
  )
  public MAge(Integer min, Integer max) {
    if (ObjectUtils.isEmpty(min) || min < DEFAULT_MIN_VALUE) {
      ParamParseException.throw0(PARAM_MIN_T, new Object[]{"min", 1});
    }
    if (min > max) {
      ParamParseException.throw0(PARAM_MAX_T, new Object[]{"min", "max"});
    }
    this.min = min;
    this.max = max;
  }

  @Override
  public Integer mock() {
    return RandomUtils.nextInt(this.min, this.max + 1);
  }
}
