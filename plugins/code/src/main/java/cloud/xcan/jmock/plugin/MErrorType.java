package cloud.xcan.jmock.plugin;


import static cloud.xcan.jmock.plugin.MCodeSnippet.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MErrorType extends AbstractMockFunction {

  public static final List<String> ERROR_TYPES = Arrays.asList(
      "NullPointerException", "IllegalArgumentException", "IndexOutOfBoundsException",
      "TypeError", "ReferenceError", "SyntaxError", "RuntimeError",
      "FileNotFoundError", "NetworkError", "TimeoutError", "ValidationError",
      "DatabaseError", "AuthenticationError", "AuthorizationError"
  );

  @Override
  public String mock() {
    return ERROR_TYPES.get(random.nextInt(ERROR_TYPES.size()));
  }

}
