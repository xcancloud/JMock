package cloud.xcan.jmock.core.exception;


import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_CONSTRUCTOR_MISMATCH_T;
import static cloud.xcan.jmock.api.i18n.ThreadLocaleHolder.getLocale;

import cloud.xcan.jmock.api.exception.AbstractParseException;
import cloud.xcan.jmock.api.i18n.MessageResources;

public class ConstructorMismatchException extends AbstractParseException {

  /**
   * MockFunction name
   */
  private final String name;

  public ConstructorMismatchException(String name) {
    super(-1);
    this.name = name;
  }

  public static ConstructorMismatchException of(String name) {
    return new ConstructorMismatchException(name);
  }

  @Override
  public String getMessage() {
    return MessageResources
        .getString(PARSER_CONSTRUCTOR_MISMATCH_T, new Object[]{name}, getLocale());
  }

  public String getName() {
    return name;
  }
}
