package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class BasicDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 1xx.
   */
  public static final String DOC_CATEGORY_BASIC = "jmock.func.category.basic";

  public static final String DOC_STRING_DESC = "jmock.func.MString.description";
  public static final String DOC_STRING_PARAMETER_LENGTH = "jmock.func.MString.parameter.length";
  public static final String DOC_STRING_PARAMETER_MIN = "jmock.func.MString.parameter.min";
  public static final String DOC_STRING_PARAMETER_MAX = "jmock.func.MString.parameter.max";
  public static final String DOC_STRING_PARAMETER_CHARS = "jmock.func.MString.parameter.chars";
  public static final String DOC_STRING_C1 = "jmock.func.MString.C1";
  public static final String DOC_STRING_C2 = "jmock.func.MString.C2";
  public static final String DOC_STRING_C3 = "jmock.func.MString.C3";
  public static final String DOC_STRING_C4 = "jmock.func.MString.C4";
  public static final String DOC_STRING_C5 = "jmock.func.MString.C5";
  public static final String DOC_STRING_C6 = "jmock.func.MString.C6";

  public static final String DOC_INTEGER_DESC = "jmock.func.MInteger.description";
  public static final String DOC_INTEGER_PARAMETER_MIN = "jmock.func.MInteger.parameter.min";
  public static final String DOC_INTEGER_PARAMETER_MAX = "jmock.func.MInteger.parameter.max";
  public static final String DOC_INTEGER_C1 = "jmock.func.MInteger.C1";
  public static final String DOC_INTEGER_C2 = "jmock.func.MInteger.C2";
  public static final String DOC_INTEGER_C3 = "jmock.func.MInteger.C3";
  public static final String DOC_INTEGER_C4 = "jmock.func.MInteger.C4";

  public static final String DOC_LONG_DESC = "jmock.func.MLong.description";
  public static final String DOC_LONG_PARAMETER_MIN = "jmock.func.MLong.parameter.min";
  public static final String DOC_LONG_PARAMETER_MAX = "jmock.func.MLong.parameter.max";
  public static final String DOC_LONG_C1 = "jmock.func.MLong.C1";
  public static final String DOC_LONG_C2 = "jmock.func.MLong.C2";
  public static final String DOC_LONG_C3 = "jmock.func.MLong.C3";
  public static final String DOC_LONG_C4 = "jmock.func.MLong.C4";

  public static final String DOC_FLOAT_DESC = "jmock.func.MFloat.description";
  public static final String DOC_FLOAT_PARAMETER_MIN = "jmock.func.MFloat.parameter.min";
  public static final String DOC_FLOAT_PARAMETER_MAX = "jmock.func.MFloat.parameter.max";
  public static final String DOC_FLOAT_PARAMETER_SCALE = "jmock.func.MFloat.parameter.scale";

  public static final String DOC_FLOAT_C1 = "jmock.func.MFloat.C1";
  public static final String DOC_FLOAT_C2 = "jmock.func.MFloat.C2";
  public static final String DOC_FLOAT_C3 = "jmock.func.MFloat.C3";
  public static final String DOC_FLOAT_C4 = "jmock.func.MFloat.C4";
  public static final String DOC_FLOAT_C5 = "jmock.func.MFloat.C5";
  public static final String DOC_FLOAT_C6 = "jmock.func.MFloat.C6";

  public static final String DOC_DOUBLE_DESC = "jmock.func.MDouble.description";
  public static final String DOC_DOUBLE_PARAMETER_MIN = "jmock.func.MDouble.parameter.min";
  public static final String DOC_DOUBLE_PARAMETER_MAX = "jmock.func.MDouble.parameter.max";
  public static final String DOC_DOUBLE_PARAMETER_SCALE = "jmock.func.MDouble.parameter.scale";
  public static final String DOC_DOUBLE_C1 = "jmock.func.MDouble.C1";
  public static final String DOC_DOUBLE_C2 = "jmock.func.MDouble.C2";
  public static final String DOC_DOUBLE_C3 = "jmock.func.MDouble.C3";
  public static final String DOC_DOUBLE_C4 = "jmock.func.MDouble.C4";
  public static final String DOC_DOUBLE_C5 = "jmock.func.MDouble.C5";
  public static final String DOC_DOUBLE_C6 = "jmock.func.MDouble.C6";

  public static final String DOC_BOOL_DESC = "jmock.func.MBool.description";
  public static final String DOC_BOOL_PARAMETER_TRUE_WEIGHT = "jmock.func.MBool.parameter.trueWeight";
  public static final String DOC_BOOL_PARAMETER_DICT = "jmock.func.MBool.parameter.dict";
  public static final String DOC_BOOL_C1 = "jmock.func.MBool.C1";
  public static final String DOC_BOOL_C2 = "jmock.func.MBool.C2";
  public static final String DOC_BOOL_C3 = "jmock.func.MBool.C3";
  public static final String DOC_BOOL_C4 = "jmock.func.MBool.C4";

  public static final String DOC_ENUM_DESC = "jmock.func.MEnum.description";
  public static final String DOC_ENUM_PARAMETER_DICT = "jmock.func.MEnum.parameter.dict";
  public static final String DOC_ENUM_PARAMETER_VALUE_WEIGHT = "jmock.func.MEnum.parameter.valueWeight";
  public static final String DOC_ENUM_C1 = "jmock.func.MEnum.C1";
  public static final String DOC_ENUM_C2 = "jmock.func.MEnum.C2";
  public static final String DOC_ENUM_C3 = "jmock.func.MEnum.C3";

  public static final String DOC_REGEXP_DESC = "jmock.func.MRegexp.description";
  public static final String DOC_REGEXP_PARAMETER_REGEXP = "jmock.func.MRegexp.parameter.regexp";
  public static final String DOC_REGEXP_C1 = "jmock.func.MRegexp.C1";
  public static final String DOC_REGEXP_C2 = "jmock.func.MRegexp.C2";

  public static final String DOC_ARRAY_DESC = "jmock.func.MArray.description";
  public static final String DOC_ARRAY_C1 = "jmock.func.MArray.C1";
  public static final String DOC_ARRAY_C2 = "jmock.func.MArray.C2";
  public static final String DOC_ARRAY_C3 = "jmock.func.MArray.C3";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-basic-plugin-messages");
  }
}
