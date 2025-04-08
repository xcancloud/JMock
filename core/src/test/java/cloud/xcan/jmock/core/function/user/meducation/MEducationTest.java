package cloud.xcan.jmock.core.function.user.meducation;

import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockResources;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.user.MEducation;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MEducationTest {

  @Test
  public void case1_defaultConstructorTest() throws Exception {
    FunctionToken token = new FunctionToken("Education", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEducation mock = (MEducation) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      String country = MessageResources.getString(JMockResources.EDUCATION, Locale.CHINA);
      System.out.println(str);
      Assertions.assertTrue(country.contains(str));
    }
  }

  @Test
  public void case2_localTest() throws Exception {
    FunctionToken token = new FunctionToken("Education",
        new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEducation mock = (MEducation) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      String countryResources = MessageResources.getString(JMockResources.EDUCATION,
          Locale.ENGLISH);
      String country = new String(countryResources.getBytes(StandardCharsets.ISO_8859_1),
          StandardCharsets.UTF_8);
      System.out.println(str);
      Assertions.assertTrue(country.contains(str));
    }
  }

  @Test
  public void case3_dictTest() throws Exception {
    String dict = "a,我的,usa";
    FunctionToken token = new FunctionToken("Education",
        new String[]{dict});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEducation mock = (MEducation) parser.parse(token);
    for (int i = 0; i < 10; i++) {
      String str = mock.mock();
      System.out.println(str);
      Assertions.assertTrue(dict.contains(str));
    }
  }
}
