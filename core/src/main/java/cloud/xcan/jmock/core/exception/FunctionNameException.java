package cloud.xcan.jmock.core.exception;


import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_NAME_CHAR_ERROR_T;
import static cloud.xcan.jmock.api.i18n.ThreadLocaleHolder.getLocale;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class FunctionNameException extends ExpressionTokenException {

  private final String errorChar;

  public FunctionNameException(String name, int position, char errorChar) {
    super(name, position);
    this.errorChar = unEscapeString(String.valueOf(errorChar));
  }

  public static FunctionNameException of(String name, int position, char errorChar) {
    return new FunctionNameException(name, position, errorChar);
  }

  @Override
  public String getMessage() {
    return MessageResources.getString(PARSER_NAME_CHAR_ERROR_T, new Object[]{
        name, position, errorChar}, getLocale());
  }
}
