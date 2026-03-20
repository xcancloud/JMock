package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class ArrayDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 12x.
   */
  public static final String DOC_CATEGORY_ARRAY = "jmock.func.category.array";

  public static final String DOC_REPEAT_DESC = "jmock.func.MRepeat.description";
  public static final String DOC_REPEAT_PARAMETER_COUNT = "jmock.func.MRepeat.parameter.count";
  public static final String DOC_REPEAT_PARAMETER_VALUE = "jmock.func.MRepeat.parameter.value";
  public static final String DOC_REPEAT_C1 = "jmock.func.MRepeat.C1";
  public static final String DOC_REPEAT_C2 = "jmock.func.MRepeat.C2";

  public static final String DOC_SEQUENCE_DESC = "jmock.func.MSequence.description";
  public static final String DOC_SEQUENCE_PARAMETER_START = "jmock.func.MSequence.parameter.start";
  public static final String DOC_SEQUENCE_PARAMETER_STEP = "jmock.func.MSequence.parameter.step";
  public static final String DOC_SEQUENCE_PARAMETER_COUNT = "jmock.func.MSequence.parameter.count";
  public static final String DOC_SEQUENCE_C1 = "jmock.func.MSequence.C1";
  public static final String DOC_SEQUENCE_C2 = "jmock.func.MSequence.C2";
  public static final String DOC_SEQUENCE_C3 = "jmock.func.MSequence.C3";

  public static final String DOC_SHUFFLE_DESC = "jmock.func.MShuffle.description";
  public static final String DOC_SHUFFLE_PARAMETER_ITEMS = "jmock.func.MShuffle.parameter.items";
  public static final String DOC_SHUFFLE_C1 = "jmock.func.MShuffle.C1";

  public static final String DOC_SAMPLE_DESC = "jmock.func.MSample.description";
  public static final String DOC_SAMPLE_PARAMETER_ITEMS = "jmock.func.MSample.parameter.items";
  public static final String DOC_SAMPLE_PARAMETER_COUNT = "jmock.func.MSample.parameter.count";
  public static final String DOC_SAMPLE_C1 = "jmock.func.MSample.C1";

  public static final String DOC_ONEOF_DESC = "jmock.func.MOneOf.description";
  public static final String DOC_ONEOF_PARAMETER_ITEMS = "jmock.func.MOneOf.parameter.items";
  public static final String DOC_ONEOF_C1 = "jmock.func.MOneOf.C1";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-array-plugin-messages");
  }
}
