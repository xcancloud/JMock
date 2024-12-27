package cloud.xcan.comp.jmock.core.function.basic;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_PARAMETER_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MBOOL_PARAMETER_TRUE_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_UNACCEPTABLE_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.calcNullWeight;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.isNullWeight;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.support.utils.RandomStringUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MBOOL_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 105)
public class MBool extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MBOOL_PARAMETER_TRUE_WEIGHT)
  private double trueWeight;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  @JMockParameter(descI18nKey = DOC_MBOOL_PARAMETER_DICT)
  private String dict;

  private int count;
  private List<String> dictArray;
  private List<Integer> weightArray;
  private List<String> valueWeightArray;

  final static String DEFAULT_TRUE_WEIGHT_VALUE = "2:1";

  final static String DEFAULT_DICT_VALUE = "true|false";

  @JMockConstructor(descI18nKey = DOC_MBOOL_C1,
      example = "@Bool()",
      exampleValues = {"true", "false"})
  public MBool() {
    this(DEFAULT_TRUE_WEIGHT_VALUE, null, DEFAULT_DICT_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MBOOL_C2,
      example = "@Bool(2:1)",
      exampleValues = {"true", "true", "false"})
  public MBool(String trueWeight) {
    this(trueWeight, null, DEFAULT_DICT_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MBOOL_C3,
      example = "@Bool(2:1,1:5)",
      exampleValues = {"true", "null", "false"})
  public MBool(String trueWeight, String nullWeight) {
    this(trueWeight, nullWeight, DEFAULT_DICT_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MBOOL_C4,
      example = "@Bool(2:1,1:5,1|0)",
      exampleValues = {"1", "null", "0"})
  public MBool(String trueWeight, String nullWeight, String dict) {
    this.dictArray = Arrays.asList(dict.split("\\|"));
    this.valueWeightArray = Arrays.asList(trueWeight.split(":"));
    if (dictArray.size() != 2) {
      ParamParseException.throw0(FPARAM_UNACCEPTABLE_T, new Object[]{"dict"});
    }
    if (valueWeightArray.size() != 2) {
      ParamParseException.throw0(FPARAM_UNACCEPTABLE_T, new Object[]{"trueWeight"});
    }
    count = valueWeightArray.stream().mapToInt(Integer::parseInt).sum();
    weightArray = new ArrayList<>(dictArray.size());
    for (int i = 0; i < dictArray.size(); i++) {
      int rangeValue;
      if (i == 0) {
        rangeValue = Integer.parseInt(valueWeightArray.get(i));
      } else {
        rangeValue = Integer.parseInt(valueWeightArray.get(i)) + weightArray.get(i - 1);
      }
      weightArray.add(i, rangeValue);
    }
    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(FPARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
  }

  @Override
  public String mock() {
    return RandomStringUtils.random(dictArray, weightArray, count, nullWeight);
  }
}
