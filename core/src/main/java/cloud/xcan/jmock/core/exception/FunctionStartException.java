package cloud.xcan.jmock.core.exception;

import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_START_NOT_FOUND_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_EXPRESSION_ERROR_T;
import static cloud.xcan.jmock.api.i18n.ThreadLocaleHolder.getLocale;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class FunctionStartException extends ExpressionTokenException {

  private final String messageKey;
  private final char functionIdentifier;

  public FunctionStartException(String name, int position, char functionIdentifier,
      String messageKey) {
    super(name, position);
    this.functionIdentifier = functionIdentifier;
    this.messageKey = messageKey;
  }

  public static FunctionStartException of(String name, int position) {
    return new FunctionStartException(name, position, '\0', FPARAM_START_NOT_FOUND_T);
  }

  public static FunctionStartException of(char functionIdentifier, int position) {
    return new FunctionStartException("", position, functionIdentifier, PARSER_EXPRESSION_ERROR_T);
  }

  @Override
  public String getMessage() {
    if (messageKey.equals(FPARAM_START_NOT_FOUND_T)) {
      return MessageResources.getString(messageKey, new Object[]{name, position}, getLocale());
    } else {
      return MessageResources.getString(messageKey, new Object[]{functionIdentifier, position
      }, getLocale());
    }
  }

  public String getMessageKey() {
    return messageKey;
  }
}
