package cloud.xcan.comp.jmock.core.function.basic;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_PARAMETER_MAX;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLONG_PARAMETER_MIN;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.calcNullWeight;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.isNullWeight;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MLONG_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 102)
public class MLong extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MLONG_PARAMETER_MIN)
  private long min;

  @JMockParameter(descI18nKey = DOC_MLONG_PARAMETER_MAX)
  private long max;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  /**
   * Max value of Long allowed
   */
  final static long DEFAULT_MAX_VALUE = Long.MAX_VALUE;

  /**
   * Min value of Long allowed
   */
  final static long LONG_MIN_VALUE = Long.MIN_VALUE;

  /**
   * default Min value of Long allowed
   */
  final static long DEFAULT_MIN_VALUE = 0L;

  @JMockConstructor(descI18nKey = DOC_MLONG_C1,
      example = "@Long()", exampleValues = {"2567071027"}
  )
  public MLong() {
    this(null);
  }

  @JMockConstructor(descI18nKey = DOC_MLONG_C2,
      example = "@Long(\"1:2\")",
      exampleValues = {"98089", "null", "28907625479"}
  )
  public MLong(String nullWeight) {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, nullWeight);
  }

  @JMockConstructor(descI18nKey = DOC_MLONG_C3,
      example = "@Long(1L,10000000000L)",
      exampleValues = {"1109", "34008978", "87256252199901"}
  )
  public MLong(Long min, Long max) {
    this(min, max, null);
  }

  /**
   * Generate any integer (4 bytes) value, and the value range is -9223372036854775808 ～
   * 9223372036854775807 when it is signed.
   *
   * @param min        the minimum value, the default minimum value is not specified 0
   * @param max        the maximum value, if not specified, the default maximum is
   *                   9223372036854775807
   * @param nullWeight a ratio of null, such as value: "1:9", which means that a random mstring is
   *                   generated 10 times and an average of 1 is null
   */
  @JMockConstructor(descI18nKey = DOC_MLONG_C4,
      example = "@Long(-10000L,100000L,)",
      exampleValues = {"198", "594", "-17865", "9876", "37092"})
  public MLong(Long min, Long max, String nullWeight) {
    this.max = max;
    this.min = min;
    if (this.min > max) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", max});
    }
    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(FPARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
  }

  @Override
  public Long mock() {
    if (this.nullWeight == 0) {
      return RandomUtils.nextLong(this.min, this.max);
    }
    return RandomUtils.nextLong(this.min, this.max, this.nullWeight);
  }

}
