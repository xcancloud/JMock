package cloud.xcan.jmock.core.function.basic.menum;


import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_NOT_NULL_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_PARAM_NOT_MATCH_WEIGHTS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MEnumExceptionTest {


  @Test
  public void test() {
    FunctionToken token = new FunctionToken("Enum", new String[]{""});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(FPARAM_NOT_NULL_T, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

  @Test
  public void test_2() {
    FunctionToken token = new FunctionToken("Enum", new String[]{"DocGenerator|B|C", "1:2", "1:3"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      assertEquals(ParamParseException.class, e.getCause().getClass());
      assertEquals(PARSER_PARAM_NOT_MATCH_WEIGHTS, ((ParamParseException) e.getCause()).getMessageKey());
    }
  }

}
