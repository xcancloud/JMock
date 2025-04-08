
package cloud.xcan.jmock.api.exception;


public abstract class AbstractParseException extends RuntimeException {

  /**
   * -1 if not known; should be known in all reasonable cases
   */
  protected int position;

  /**
   * Construct a new parse exception.
   */
  public AbstractParseException() {
    this(-1);
  }

  /**
   * Construct a new parse exception.
   *
   * @param position the position in the expression mstring where the problem occurred
   */
  public AbstractParseException(int position) {
    this.position = position;
  }

  /**
   * Return the position in the mstring where the problem occurred.
   */
  public int getPosition() {
    return this.position;
  }

  /**
   * Return a detailed locale description of this exception, including the mstring and position (if
   * available).
   */
  @Override
  public abstract String getMessage();

}
