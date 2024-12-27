package cloud.xcan.comp.jmock.core.exception;


import cloud.xcan.comp.jmock.api.exception.AbstractParseException;

public class TokenException extends AbstractParseException {

  protected String name;

  /**
   * Construct a new text or expression parse exception.
   *
   * @param name Exception extractor name
   */
  public TokenException(String name) {
    this(name, -1);
  }

  /**
   * Construct a new text or expression parse exception.
   *
   * @param name     Exception extractor name
   * @param position The position in the text or expression where the problem occurred.
   */
  public TokenException(String name, int position) {
    super(position + 1);
    this.name = unEscapeString(name);
  }

  /**
   * Return the exception locale message.
   */
  @Override
  public String getMessage() {
    return "";
  }

  public String unEscapeString(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      switch (s.charAt(i)) {
        case '\n':
          sb.append("\\n");
          break;
        case '\t':
          sb.append("\\t");
          break;
        // ... rest of escape characters
        default:
          sb.append(s.charAt(i));
      }
    }
    return sb.toString();
  }

  public String getName() {
    return name;
  }
}
