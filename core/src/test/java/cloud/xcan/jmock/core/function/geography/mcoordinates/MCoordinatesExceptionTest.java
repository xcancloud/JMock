
package cloud.xcan.jmock.core.function.geography.mcoordinates;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.jupiter.api.Test;

public class MCoordinatesExceptionTest {

  @Test
  public void minLngNullValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{null, "80D", "40D", "70D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MIN_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxLngNullValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", null, "40D", "70D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void minLatNullValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "80D", null, "70D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MIN_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxLatNullValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "90D", "40D", null, "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void minLngValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"-181D", "80D", "40D", "70D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MIN_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxLngValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "181D", "40D", "70D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void minLatValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "80D", "-91D", "70D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MIN_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxLatValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "90D", "40D", "91D", "7", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void scaleNullValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "90D", "40D", "91D", null, "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void minScaleValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "90D", "40D", "91D", "4", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void maxScaleValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "90D", "40D", "91D", "11", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void lngValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"90D", "10D", "40D", "91D", "11", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }

  @Test
  public void latValueTest() {
    FunctionToken token = new FunctionToken("Coordinates",
        new String[]{"10D", "90D", "90D", "10D", "11", "1:9"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    try {
      parser.parse(token);
      fail("Exception not triggered");
    } catch (Exception e) {
      try {
        ParamParseException paramException = (ParamParseException) e.getCause();
        assertEquals(JMockMessage.FPARAM_MAX_T, paramException.getMessageKey());
      } catch (Exception exception) {
        fail("Not expected exception");
      }
    }
  }
}
