package cloud.xcan.jmock.plugin;


import static cloud.xcan.jmock.plugin.CodeDocMessage.DOC_CATEGORY_CODE;
import static cloud.xcan.jmock.plugin.CodeDocMessage.DOC_ERROR_TYPE_C1;
import static cloud.xcan.jmock.plugin.CodeDocMessage.DOC_ERROR_TYPE_DESC;
import static cloud.xcan.jmock.plugin.MCodeSnippet.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_ERROR_TYPE_DESC, categoryI18nKey = {
    DOC_CATEGORY_CODE}, order = 3002)
public class MErrorType extends AbstractMockFunction {

  public static final List<String> ERROR_TYPES = Arrays.asList(
      "NullPointerException", "IllegalArgumentException", "IndexOutOfBoundsException",
      "TypeError", "ReferenceError", "SyntaxError", "RuntimeError",
      "FileNotFoundError", "NetworkError", "TimeoutError", "ValidationError",
      "DatabaseError", "AuthenticationError", "AuthorizationError"
  );

  @JMockConstructor(descI18nKey = DOC_ERROR_TYPE_C1,
      example = "@ErrorType()",
      exampleValues = {"ReferenceError", "IndexOutOfBoundsException"})
  public MErrorType() {
  }

  @Override
  public String mock() {
    return ERROR_TYPES.get(random.nextInt(ERROR_TYPES.size()));
  }
}
