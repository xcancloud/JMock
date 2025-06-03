package cloud.xcan.jmock.core.function.basic;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_C4;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_PARAMETER_MAX;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_INTEGER_PARAMETER_MIN;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
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

/**
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_INTEGER_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 102)
public class MInteger extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_INTEGER_PARAMETER_MIN)
  private int min;

  @JMockParameter(descI18nKey = DOC_INTEGER_PARAMETER_MAX)
  private int max;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  /**
   * Max value of Integer allowed
   */
  final static int DEFAULT_MAX_VALUE = Integer.MAX_VALUE;

  @JMockConstructor(descI18nKey = DOC_INTEGER_C1,
      example = "@Integer()", exampleValues = {"666", "8888"}
  )
  public MInteger() {
    this(null);
  }

  @JMockConstructor(descI18nKey = DOC_INTEGER_C2,
      example = "@Integer(\"1:2\")", exampleValues = {"8760182", "237", "null"}
  )
  public MInteger(String nullWeight) {
    this(0, DEFAULT_MAX_VALUE, nullWeight);
  }

  @JMockConstructor(descI18nKey = DOC_INTEGER_C3,
      example = "@Integer(100,200)", exampleValues = {"162", "133", "191"}
  )
  public MInteger(Integer min, Integer max) {
    this(min, max, null);
  }


  /**
   * Generate any integer (4 bytes) value, and the value range is -2147483648 to 2147483647 when it
   * is signed.
   *
   * @param min        the minimum value, the default minimum value is not specified 0
   * @param max        the maximum value, if not specified, the default maximum is 2147483647
   * @param nullWeight a ratio of null, such as value: "1:9", which means that a random mstring is
   *                   generated 10 times and an average of 1 is null
   */
  @JMockConstructor(descI18nKey = DOC_INTEGER_C4,
      example = "@Integer(-100000,100000,\"1:3\")", exampleValues = {"-7811", "null", "78732",
      "12909", "76028"}
  )
  public MInteger(Integer min, Integer max, String nullWeight) {
    if (min == null) {
      this.min = 0;
    } else {
      this.min = min;
    }
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
  }

  @Override
  public Integer mock() {
    if (this.nullWeight == 0) {
      return RandomUtils.nextInt(this.min, this.max);
    }
    return RandomUtils.nextInt(this.min, this.max, this.nullWeight);
  }
}
