package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_MIN_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_WEIGHT_T;
import static cloud.xcan.jmock.api.support.utils.StringToTypeUtils.calcNullWeight;
import static cloud.xcan.jmock.api.support.utils.StringToTypeUtils.isNullWeight;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_CATEGORY_GEOGRAPHY;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_C1;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_C2;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_DESC;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_PARAMETER_MAX_LAT;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_PARAMETER_MAX_LNG;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_PARAMETER_MIN_LAT;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_PARAMETER_MIN_LNG;
import static cloud.xcan.jmock.plugin.GeographyDocMessage.DOC_COORDINATES_PARAMETER_SCALE;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.support.utils.RandomUtils;
import java.text.DecimalFormat;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ObjectUtils;

/**
 * @author Hao Guo
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_COORDINATES_DESC,
    categoryI18nKey = {DOC_CATEGORY_GEOGRAPHY}, order = 513)
public class MCoordinates extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_COORDINATES_PARAMETER_MIN_LNG)
  private double minLng;

  @JMockParameter(descI18nKey = DOC_COORDINATES_PARAMETER_MAX_LNG)
  private double maxLng;

  @JMockParameter(descI18nKey = DOC_COORDINATES_PARAMETER_MIN_LAT)
  private double minLat;

  @JMockParameter(descI18nKey = DOC_COORDINATES_PARAMETER_MAX_LAT)
  private double maxLat;

  @JMockParameter(descI18nKey = DOC_COORDINATES_PARAMETER_SCALE)
  private Integer scale;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  private String scalePattern;

  /**
   * Max value of Integer allowed
   */
  final static double DEFAULT_MAX_LNG_VALUE = 180;

  final static double DEFAULT_MIN_LNG_VALUE = -180;

  final static double DEFAULT_MAX_LAT_VALUE = 90;

  final static double DEFAULT_MIN_LAT_VALUE = -90;

  final static int DEFAULT_SCALE_VALUE = 6;

  final static int MAX_SCALE_VALUE = 10;

  @JMockConstructor(descI18nKey = DOC_COORDINATES_C1,
      example = "@Coordinates()",
      exampleValues = {"20.336546,90.232121"})
  public MCoordinates() {
    this(DEFAULT_MIN_LNG_VALUE, DEFAULT_MAX_LNG_VALUE, DEFAULT_MIN_LAT_VALUE, DEFAULT_MAX_LAT_VALUE,
        DEFAULT_SCALE_VALUE, null);
  }

  @JMockConstructor(descI18nKey = DOC_COORDINATES_C2,
      example = "@Coordinates(30,60,100,160,5,1:2)",
      exampleValues = {"40.336546,110.232121"})
  public MCoordinates(Double minLng, Double maxLng, Double minLat, Double maxLat, Integer scale,
      String nullWeight) {
    if (ObjectUtils.isEmpty(minLng) || minLng < DEFAULT_MIN_LNG_VALUE) {
      ParamParseException.throw0(PARAM_MIN_T, new Object[]{"minLng", DEFAULT_MIN_LNG_VALUE});
    }
    if (ObjectUtils.isEmpty(maxLng) || maxLng > DEFAULT_MAX_LNG_VALUE) {
      ParamParseException.throw0(PARAM_MAX_T, new Object[]{"maxLng", DEFAULT_MAX_LNG_VALUE});
    }
    if (ObjectUtils.isEmpty(minLat) || minLat < DEFAULT_MIN_LAT_VALUE) {
      ParamParseException.throw0(PARAM_MIN_T, new Object[]{"minLat", DEFAULT_MIN_LAT_VALUE});
    }
    if (ObjectUtils.isEmpty(maxLat) || maxLat > DEFAULT_MAX_LAT_VALUE) {
      ParamParseException.throw0(PARAM_MAX_T, new Object[]{"maxLat", DEFAULT_MAX_LAT_VALUE});
    }
    if (ObjectUtils.isEmpty(scale) || scale < DEFAULT_SCALE_VALUE) {
      ParamParseException.throw0(PARAM_MIN_T, new Object[]{"scale", DEFAULT_SCALE_VALUE});
    }
    if (scale > MAX_SCALE_VALUE) {
      ParamParseException.throw0(PARAM_MAX_T, new Object[]{"scale", MAX_SCALE_VALUE});
    }
    if (minLng > maxLng) {
      ParamParseException.throw0(PARAM_MAX_T, new Object[]{"minLng", "maxLng"});
    }
    if (minLat > maxLat) {
      ParamParseException.throw0(PARAM_MAX_T, new Object[]{"minLat", "maxLat"});
    }
    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(PARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
    this.minLng = minLng;
    this.maxLng = maxLng;
    this.minLat = minLat;
    this.maxLat = maxLat;
    StringBuilder pattern = new StringBuilder("0.");
    for (int i = 0; i < scale; i++) {
      pattern.append("0");
    }
    this.scalePattern = pattern.toString();
  }

  @Override
  public String mock() {
    double lng = RandomUtils.nextDouble(0, this.maxLng);
    double lat = RandomUtils.nextDouble(0, this.maxLat);
    DecimalFormat decimalFormat = new DecimalFormat(scalePattern);
    String strLng = decimalFormat.format(lng);
    String strLat = decimalFormat.format(lat);
    if (minLng < 0) {
      if (RandomUtils.nextInt() % 2 == 0) {
        strLng = "-" + strLng;
      }
    }
    if (minLat < 0) {
      if (RandomUtils.nextInt() % 2 == 0) {
        strLat = "-" + strLat;
      }
    }
    return strLng + "," + strLat;
  }
}
