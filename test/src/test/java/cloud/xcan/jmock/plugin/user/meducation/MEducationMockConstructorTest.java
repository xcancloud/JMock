package cloud.xcan.jmock.plugin.user.meducation;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MEducation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MEducationMockConstructorTest {


  @Test
  public void MEducation1() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Education",
        new String[]{});
    MEducation mock = (MEducation) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MEducation2() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Education",
        new String[]{"en"});
    MEducation mock = (MEducation) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MEducation3() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Education",
        new String[]{"等我,耳机,佛山"});
    MEducation mock = (MEducation) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }

  @Test
  public void MEducation4() throws Exception {
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();

    FunctionToken token = new FunctionToken("Education",
        new String[]{"zh_CN", "等我,耳机,佛山"});
    MEducation mock = (MEducation) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertNotNull(mock.getDict());
  }
}
