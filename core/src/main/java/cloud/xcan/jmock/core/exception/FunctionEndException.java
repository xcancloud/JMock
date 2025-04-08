package cloud.xcan.jmock.core.exception;

import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_END_ERROR_T;
import static cloud.xcan.jmock.api.i18n.ThreadLocaleHolder.getLocale;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class FunctionEndException extends ExpressionTokenException {

  public FunctionEndException(String name, int position) {
    super(name, position);
  }

  public static FunctionEndException of(String name, int position) {
    return new FunctionEndException(name, position);
  }

  @Override
  public String getMessage() {
    return MessageResources
        .getString(FPARAM_END_ERROR_T, new Object[]{name, position}, getLocale());
  }
}
