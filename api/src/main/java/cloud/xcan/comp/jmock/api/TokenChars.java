package cloud.xcan.comp.jmock.api;

public interface TokenChars {

  String FUNC_NAME_PREFIX = "M";

  String FUNC_NAME_ARRAY = "Array";

  String FUNC_NAME_SQL = "Sql";

  char DEFAULT_ESCAPE_CHAR = '\\';

  /**
   * The default function identifier extractor of the expression
   */
  char FUNC_IDENTIFIER = '@';
  char FUNC_PARAM_START = '(';
  char FUNC_PARAM_END = ')';
  char FUNC_PARAM_SEPARATOR = ',';


  /**
   * The default method call identifier extractor of the expression
   */
  char METHOD_CALL_IDENTIFIER = '$';
  char METHOD_CALL_PARAM_START = '{';
  char METHOD_CALL_PARAM_END = '}';
  char METHOD_CALL_REF_SEPARATOR = '.';

}
