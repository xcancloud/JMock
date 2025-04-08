package cloud.xcan.jmock.core.function.basic;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_C4;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_C5;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_C6;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_PARAMETER_MAX;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_PARAMETER_MIN;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MDOUBLE_PARAMETER_SCALE;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_SIZE_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static cloud.xcan.jmock.core.support.utils.StringToTypeUtils.calcNullWeight;
import static cloud.xcan.jmock.core.support.utils.StringToTypeUtils.isNullWeight;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.support.utils.RandomUtils;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MDOUBLE_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 103)
public class MDouble extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MDOUBLE_PARAMETER_MIN)
  private double min;

  @JMockParameter(descI18nKey = DOC_MDOUBLE_PARAMETER_MAX)
  private double max;

  @JMockParameter(descI18nKey = DOC_MDOUBLE_PARAMETER_SCALE)
  private int scale;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  /**
   * Max value of Double allowed
   */
  public final static double DEFAULT_MAX_VALUE = Double.MAX_VALUE;

  public final static int DEFAULT_SCALE_VALUE = 2;

  public final static int MAX_SCALE_VALUE = 7;

  @JMockConstructor(descI18nKey = DOC_MDOUBLE_C1,
      example = "@Double()",
      exampleValues = {"78.99"})
  public MDouble() {
    this(DEFAULT_SCALE_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MDOUBLE_C4,
      example = "@Double(3)",
      exampleValues = {"7.987", "89.909", "85.231"})
  public MDouble(Integer scale) {
    this(0D, DEFAULT_MAX_VALUE, scale, null);
  }

  @JMockConstructor(descI18nKey = DOC_MDOUBLE_C2,
      example = "@Double(\"1:2\")",
      exampleValues = {"89.90", "null", "999.90"})
  public MDouble(String nullWeight) {
    this(0D, DEFAULT_MAX_VALUE, DEFAULT_SCALE_VALUE, nullWeight);
  }

  @JMockConstructor(descI18nKey = DOC_MDOUBLE_C3,
      example = "@Double(88,999999)",
      exampleValues = {"99.99", "899.99", "7865.09"})
  public MDouble(Double min, Double max) {
    this(min, max, null);
  }

  @JMockConstructor(descI18nKey = DOC_MDOUBLE_C5,
      example = "@Double(88,99999,4,\"1:2\")",
      exampleValues = {"888.9087", "null", "666.9087"})
  public MDouble(Double min, Double max, Integer scale) {
    this(min, max, scale, null);
  }


  /**
   * Generate any floating point (4 bytes) value, the value range is 1.79769313486231570e-308 to
   * 4.94065645841246544e+324 when signed.
   *
   * @param min        The minimum value, the default minimum value is not specified 0
   * @param max        The maximum value, if not specified, the default maximum value is
   *                   4.94065645841246544e+324
   * @param scale      Number of decimal places for floating point values Timing default 2 decimal
   *                   places
   * @param nullWeight The ratio of null (null), such as the value: "1:9", means generating a random
   *                   mstring 10 times and an average of 1 time is null, and the default is not
   *                   empty
   */
  @JMockConstructor(descI18nKey = DOC_MDOUBLE_C6,
      example = "@Double(88,99999,4,\"1:2\")",
      exampleValues = {"888.9087", "null", "666.9087"})
  public MDouble(Double min, Double max, Integer scale, String nullWeight) {
    if (min == null || min < 0) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", 0});
    }

    if (max == null || max <= 0) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"max", 0});
    }
    if (min > max) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", "max"});
    }
    this.min = min;
    this.max = max;

    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(FPARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
    if (scale == null) {
      this.scale = DEFAULT_SCALE_VALUE;
    } else if (scale > MAX_SCALE_VALUE || scale < 0) {
      ParamParseException.throw0(FPARAM_SIZE_T, new Object[]{"scale", 0, MAX_SCALE_VALUE});
    } else {
      this.scale = scale;
    }
  }

  @Override
  public Double mock() {
    if (this.nullWeight == 0) {
      return RandomUtils.nextDouble(this.min, this.max, scale);
    }
    return RandomUtils.nextDouble(this.min, this.max, this.scale, this.nullWeight);
  }
}
