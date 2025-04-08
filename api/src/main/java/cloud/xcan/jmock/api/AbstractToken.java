package cloud.xcan.jmock.api;

import java.util.Objects;

/**
 * The basic implementation of a {@link Token}.
 *
 * @author XiaoLong Liu
 */
public abstract class AbstractToken implements Token {

  /**
   * Token name
   */
  protected String name;
  /**
   * Expression start parsing position
   */
  protected int startPos = 0;
  /**
   * Expression end parsing position
   */
  protected int endPos = 0;

  protected String token = "";

  /**
   * @return Returns the prefix of the expression identifier. For example, a String function call
   * returns `@String(`.
   */
  public abstract String prefixIdentifier();

  public AbstractToken(String name) {
    this.name = name;
  }

  public AbstractToken(String name, int startPos, int endPos, String token) {
    this.name = name;
    this.startPos = startPos;
    this.endPos = endPos;
    this.token = token;
  }

  @Override
  public String name() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int startPos() {
    return startPos;
  }

  public void setStartPos(int startPos) {
    this.startPos = startPos;
  }

  @Override
  public int endPos() {
    return endPos;
  }

  public void setEndPos(int endPos) {
    this.endPos = endPos;
  }

  @Override
  public String token() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof AbstractToken)) {
      return false;
    }
    AbstractToken that = (AbstractToken) o;
    return startPos == that.startPos
        && endPos == that.endPos
        && Objects.equals(name, that.name)
        && Objects.equals(token, that.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, startPos, endPos, token);
  }
}
