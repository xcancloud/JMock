package cloud.xcan.comp.jmock.core.function.basic;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MENUM_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MENUM_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MENUM_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MENUM_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MENUM_PARAMETER_DICT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MENUM_PARAMETER_VALUE_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_NOT_NULL_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.PARSER_PARAM_NOT_MATCH_WEIGHTS;
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
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MENUM_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 104)
public class MEnum extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MENUM_PARAMETER_DICT)
  private String dict;

  @JMockParameter(descI18nKey = DOC_MENUM_PARAMETER_VALUE_WEIGHT)
  private String valueWeight;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  /**
   * Random number is empty ratio: 1:1:1...
   */
  public final static String DEFAULT_VALUE_WEIGHT = "1";
  /**
   * Random number is empty ratio: 0
   */
  public final static double DEFAULT_NULL_WEIGHT = 0;

  private List<String> dictArray;
  private List<String> valueWeightArray;
  private int count;
  private List<Integer> weightArray;
  private boolean defaultWeightFlag = true;

  public MEnum() {
  }

  /**
   * SPI relies on no-parameter construction, so no-parameter construction must be provided to SPI,
   * but the business external document does not expose the no-parameter constructor to avoid users'
   * use.
   */
  @JMockConstructor(descI18nKey = DOC_MENUM_C1,
      example = "@Enum(HIGH|WIDTH)",
      exampleValues = {"HIGH"})
  public MEnum(String dict) {
    this(dict, DEFAULT_VALUE_WEIGHT, null);
  }

  @JMockConstructor(descI18nKey = DOC_MENUM_C2,
      example = "@Enum(HIGH|WIDTH,1:1)",
      exampleValues = {"HIGH", "WIDTH"})
  public MEnum(String dict, String valueWeight) {
    this(dict, valueWeight, null);
  }

  @JMockConstructor(descI18nKey = DOC_MENUM_C3,
      example = "@Enum(HIGH|WIDTH,1:1,1:2)",
      exampleValues = {"HIGH", "WIDTH", "null"})
  public MEnum(String dict, String valueWeight, String nullWeight) {
    if (StringUtils.isBlank(dict)) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"value"});
    }
    if (StringUtils.isBlank(valueWeight)) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"valueWeight"});
    }
    this.dictArray = Arrays.asList(dict.split("\\|"));
    this.valueWeightArray = Arrays.asList(valueWeight.split(":"));
    this.defaultWeightFlag = (valueWeightArray.size() == 1 && StringUtils.equals(
        valueWeightArray.get(0), "1"));
    if (!defaultWeightFlag) {
      if (dictArray.size() != valueWeightArray.size()) {
        throw new ParamParseException(PARSER_PARAM_NOT_MATCH_WEIGHTS);
      }
    }
    if (defaultWeightFlag) {
      count = dictArray.size();
    } else {
      count = valueWeightArray.stream()
          .mapToInt(Integer::parseInt).sum();
    }
    weightArray = new ArrayList<>(dictArray.size());
    for (int i = 0; i < dictArray.size(); i++) {
      int rangeValue;
      if (defaultWeightFlag) {
        rangeValue = i + 1;
      } else {
        if (i == 0) {
          rangeValue = Integer.parseInt(valueWeightArray.get(i));
        } else {
          rangeValue = Integer.parseInt(valueWeightArray.get(i)) + weightArray.get(i - 1);
        }
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
    // For MEnum, the value parameter is required, and the processing of no-parameter construction returns null data
    if (dictArray == null || dictArray.isEmpty()) {
      //ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"value"});
      return null;
    }
    return RandomStringUtils.random(dictArray, weightArray, count, nullWeight);
  }
}
