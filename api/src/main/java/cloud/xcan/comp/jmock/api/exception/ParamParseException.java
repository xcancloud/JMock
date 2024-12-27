
package cloud.xcan.comp.jmock.api.exception;


import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.api.i18n.ThreadLocaleHolder;

public class ParamParseException extends AbstractParseException {

  private final String messageKey;
  private final Object[] params;

  public ParamParseException(String messageKey) {
    this(messageKey, null);
  }

  public ParamParseException(String messageKey, Object[] params) {
    super(-1);
    this.messageKey = messageKey;
    this.params = params;
  }

  public static ParamParseException of(String messageKey) {
    return new ParamParseException(messageKey);
  }

  public static ParamParseException of(String messageKey, Object[] params) {
    return new ParamParseException(messageKey, params);
  }

  public static void throw0(String messageKey) {
    throw of(messageKey);
  }

  public static void throw0(String messageKey, Object[] params) {
    throw of(messageKey, params);
  }

  @Override
  public String getMessage() {
    if (params == null) {
      return MessageResources.getString(messageKey, ThreadLocaleHolder.getLocale());
    }
    return MessageResources.getString(messageKey, params, ThreadLocaleHolder.getLocale());
  }

  public String getMessageKey() {
    return messageKey;
  }

}
