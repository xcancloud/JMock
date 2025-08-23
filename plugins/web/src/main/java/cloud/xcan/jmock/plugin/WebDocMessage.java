package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class WebDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 10xx.
   */
  public static final String DOC_CATEGORY_WEB = "jmock.func.category.web";

  public static final String DOC_COLOR_DESC = "jmock.func.MColor.description";
  public static final String DOC_COLOR_PARAMETER_FORMAT = "jmock.func.MColor.parameter.format";
  public static final String DOC_COLOR_C1 = "jmock.func.MColor.C1";
  public static final String DOC_COLOR_C2 = "jmock.func.MColor.C2";

  public static final String DOC_EMOJI_DESC = "jmock.func.MEmoji.description";
  public static final String DOC_EMOJI_C1 = "jmock.func.MEmoji.C1";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-web-plugin-messages");
  }
}
