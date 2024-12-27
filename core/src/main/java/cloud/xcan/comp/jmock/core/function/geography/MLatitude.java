package cloud.xcan.comp.jmock.core.function.geography;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_GEOGRAPHY;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOORDINATES_PARAMETER_MAX_LAT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOORDINATES_PARAMETER_MIN_LAT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOORDINATES_PARAMETER_SCALE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLATITUDE_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLATITUDE_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLATITUDE_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_NOT_NULL_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.calcNullWeight;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.isNullWeight;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.text.DecimalFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author hao.guo
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MLATITUDE_DESC,
    categoryI18nKey = {DOC_CATEGORY_GEOGRAPHY}, order = 511)
public class MLatitude extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MCOORDINATES_PARAMETER_MIN_LAT)
  private double minLat;

  @JMockParameter(descI18nKey = DOC_MCOORDINATES_PARAMETER_MAX_LAT)
  private double maxLat;

  @JMockParameter(descI18nKey = DOC_MCOORDINATES_PARAMETER_SCALE)
  private Integer scale;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  private String scalePattern;

  final static double DEFAULT_MAX_LAT_VALUE = 90;

  final static double DEFAULT_MIN_LAT_VALUE = -90;

  final static int DEFAULT_SCALE_VALUE = 6;

  final static double DEFAULT_NULL_WEIGHT_VALUE = 0.1;

  final static int MAX_SCALE_VALUE = 10;

  final static int MIN_SCALE_VALUE = 0;

  @JMockConstructor(descI18nKey = DOC_MLATITUDE_C1,
      example = "@Latitude()",
      exampleValues = {"90.232121"})
  public MLatitude() {
    this(DEFAULT_MIN_LAT_VALUE, DEFAULT_MAX_LAT_VALUE, DEFAULT_SCALE_VALUE, null);
  }

  @JMockConstructor(descI18nKey = DOC_MLATITUDE_C2,
      example = "@Latitude(30,60,5,1:2)",
      exampleValues = {"40.336546"})
  public MLatitude(Double minLat, Double maxLat, Integer scale, String nullWeight) {
    if (ObjectUtils.isEmpty(minLat)) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"minLat"});
    }
    if (minLat < DEFAULT_MIN_LAT_VALUE) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"minLat", DEFAULT_MIN_LAT_VALUE});
    }
    if (ObjectUtils.isEmpty(maxLat)) {
      ParamParseException.throw0(FPARAM_NOT_NULL_T, new Object[]{"maxLat"});
    }
    if (maxLat > DEFAULT_MAX_LAT_VALUE) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"minLat", DEFAULT_MAX_LAT_VALUE});
    }
    if (ObjectUtils.isEmpty(scale) || scale < MIN_SCALE_VALUE) {
      scale = DEFAULT_SCALE_VALUE;
    }
    if (scale > MAX_SCALE_VALUE) {
      scale = MAX_SCALE_VALUE;
    }
    if (minLat > maxLat) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"minLat", "maxLat"});
    }
    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(FPARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
    this.minLat = minLat;
    this.maxLat = maxLat;
    this.scale = scale;
    StringBuilder pattern = new StringBuilder("0.");
    for (int i = 0; i < scale; i++) {
      pattern.append("0");
    }
    this.scalePattern = pattern.toString();
  }

  @Override
  public String mock() {
    double lat = RandomUtils.nextDouble(0, this.maxLat);
    DecimalFormat decimalFormat = new DecimalFormat(scalePattern);
    String strLat = decimalFormat.format(lat);
    if (minLat < 0) {
      if (RandomUtils.nextInt() % 2 == 0) {
        strLat = "-" + strLat;
      }
    }
    return strLat;
  }
}
