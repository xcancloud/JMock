package cloud.xcan.jmock.core.exception;

import static cloud.xcan.angus.spec.utils.ObjectUtils.isEmpty;
import static cloud.xcan.jmock.api.i18n.ThreadLocaleHolder.getLocale;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class InvalidNameException extends TokenException {

  private final String messageKey;

  public InvalidNameException(String name, int position, String messageKey) {
    super(name, position);
    this.messageKey = messageKey;
  }

  public static InvalidNameException of(String name, int position, String messageKey) {
    return new InvalidNameException(name, position, messageKey);
  }

  @Override
  public String getMessage() {
    if (isEmpty(name)) {
      return MessageResources.getString(messageKey, new Object[]{position}, getLocale());
    } else {
      return MessageResources.getString(messageKey, new Object[]{name, position}, getLocale());
    }
  }
}
