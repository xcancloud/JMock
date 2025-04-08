package cloud.xcan.jmock.core.exception;

public class ExpressionTokenException extends TokenException {

  protected String expression;

  /**
   * Construct a new expression extractor parse exception.
   *
   * @param name Exception extractor name
   */
  public ExpressionTokenException(String name) {
    this(name, -1);
  }

  /**
   * Construct a new expression extractor parse exception.
   *
   * @param name     Exception extractor name
   * @param position The position in the expression where the problem occurred.
   */
  public ExpressionTokenException(String name, int position) {
    this(name, position, null);
  }

  /**
   * Construct a new expression extractor parse exception.
   *
   * @param name     Exception extractor name
   * @param position The position in the expression where the problem occurred.
   */
  public ExpressionTokenException(String name, int position, String expression) {
    super(name, position);
    this.expression = expression;
  }

  /**
   * Return the exception locale message.
   */
  @Override
  public String getMessage() {
    return "";
  }

}
