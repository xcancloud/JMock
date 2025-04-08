package cloud.xcan.jmock.api;

import static cloud.xcan.angus.spec.utils.ObjectUtils.mapEquals;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FunctionToken extends AbstractToken {

  /**
   * The default function identifier extractor of the expression
   */
  private char identifier = TokenChars.FUNC_IDENTIFIER;

  private Map<String, String> params;

  public FunctionToken(String name, String[] params) {
    super(name);
    if (isNotEmpty(params)) {
      Map<String, String> paramsMap = new HashMap<>();
      for (int i = 1; i <= params.length; i++) {
        paramsMap.put(String.valueOf(i), params[i - 1]);
      }
      this.params = paramsMap;
    }
  }

  public FunctionToken(String name, Map<String, String> params) {
    super(name);
    this.params = params;
  }

  public FunctionToken(char identifier, String name, int startPos, int endPos,
      Map<String, String> params, String token) {
    super(name, startPos, endPos, token);
    this.identifier = identifier;
    this.params = params;
  }

  @Override
  public String prefixIdentifier() {
    return this.identifier + super.name() + TokenChars.FUNC_PARAM_START;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FunctionToken)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    FunctionToken that = (FunctionToken) o;
    return identifier == that.identifier && mapEquals(params, that.params);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), identifier, params);
  }
}
