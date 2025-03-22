package cloud.xcan.comp.jmock.core.function.basic;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_C5;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_C6;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_PARAMETER_MAX;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_PARAMETER_MIN;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MFLOAT_PARAMETER_SCALE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
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
 * @author hao.guo
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MFLOAT_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 103)
public class MFloat extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MFLOAT_PARAMETER_MIN)
  private Float min;

  @JMockParameter(descI18nKey = DOC_MFLOAT_PARAMETER_MAX)
  private Float max;

  @JMockParameter(descI18nKey = DOC_MFLOAT_PARAMETER_SCALE)
  private int scale;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  /**
   * Max value of Integer allowed
   */
  final static float DEFAULT_MAX_VALUE = Float.MAX_VALUE;

  final static int DEFAULT_SCALE_VALUE = 2;

  final static int MAX_SCALE_VALUE = 7;

  /**
   * No-parameter MockConstructor: @Float()
   */
  @JMockConstructor(descI18nKey = DOC_MFLOAT_C1,
      example = "@Float()",
      exampleValues = {"1.2222222"})
  public MFloat() {
    this((String) null);
  }

  @JMockConstructor(descI18nKey = DOC_MFLOAT_C2,
      example = "@Float(1:2)",
      exampleValues = {"1.2222222"})
  public MFloat(String nullWeight) {
    this(0f, DEFAULT_MAX_VALUE, DEFAULT_SCALE_VALUE, nullWeight);
  }

  @JMockConstructor(descI18nKey = DOC_MFLOAT_C3,
      example = "@Float(5,15)",
      exampleValues = {"5.2222222"})
  public MFloat(Float min, Float max) {
    this(min, max, null);
  }

  @JMockConstructor(descI18nKey = DOC_MFLOAT_C4,
      example = "@Float(5)",
      exampleValues = {"87901.01127", "3092290435.18326"})
  public MFloat(Integer scale) {
    this(0f, DEFAULT_MAX_VALUE, scale);
  }

  @JMockConstructor(descI18nKey = DOC_MFLOAT_C5,
      example = "@Float(5,15,2)",
      exampleValues = {"5.22"})
  public MFloat(Float min, Float max, Integer scale) {
    this(min, max, scale, null);
  }

  /**
   * Generate any floating point (4 bytes) value, the value range is 1.40129846432481707e-45 to
   * 3.40282346638528860e+38 when signed.
   *
   * @param min        The minimum value, the default minimum value is not specified 0
   * @param max        The maximum value, if not specified, the default maximum value is
   *                   3.40282346638528860e+38
   * @param scale      Number of decimal places for floating point values Timing default 2 decimal
   *                   places
   * @param nullWeight The ratio of null (null), such as the value: "1:9", means generating a random
   *                   mstring 10 times and an average of 1 time is null, and the default is not
   *                   empty
   */
  @JMockConstructor(descI18nKey = DOC_MFLOAT_C6,
      example = "@Float(0,10,5,1:2)",
      exampleValues = {"1.22222"})
  public MFloat(Float min, Float max, Integer scale, String nullWeight) {
    this.min = min == null ? 0 : min;

    if (max == null || max <= 0) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"max", 0});
    }
    if (this.min > max) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", max});
    }
    this.max = max;
    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(FPARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
    if (scale == null) {
      this.scale = DEFAULT_SCALE_VALUE;
    } else if (scale > MAX_SCALE_VALUE) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", max});
    } else if (scale < 0) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"min", max});
    } else {
      this.scale = scale;
    }
  }

  @Override
  public Float mock() {
    if (this.nullWeight == 0) {
      return RandomUtils.nextFloat(this.min, this.max, scale);
    }
    return RandomUtils.nextFloat(this.min, this.max, this.scale, this.nullWeight);
  }
}
