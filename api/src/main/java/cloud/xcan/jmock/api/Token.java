package cloud.xcan.jmock.api;

/**
 * A extractor is an abstraction of a function or method call expression.
 *
 * @author XiaoLong Liu
 */
public interface Token {

  /**
   * Token name
   */
  String name();

  /**
   * Index of first character
   */
  int startPos();

  /**
   * Index of char after the last character
   */
  int endPos();

  String token();

  Token NOOP = new Token() {
    @Override
    public String name() {
      return "NOOP";
    }

    @Override
    public int startPos() {
      return -1;
    }

    @Override
    public int endPos() {
      return -1;
    }

    @Override
    public String token() {
      return "@NOOP()";
    }

  };
}
