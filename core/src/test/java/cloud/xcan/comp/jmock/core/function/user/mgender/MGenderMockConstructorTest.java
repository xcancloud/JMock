package cloud.xcan.comp.jmock.core.function.user.mgender;


import cloud.xcan.comp.jmock.api.FunctionToken;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import cloud.xcan.comp.jmock.core.function.user.MGender;
import cloud.xcan.comp.jmock.core.parser.SimpleMockFunctionTokenParser;
import java.util.ArrayList;
import java.util.Locale;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class MGenderMockConstructorTest {

  String chinaMessage = MessageResources.getString(JMockMessage.FDATA_GENDER);
  ArrayList<String> china = Lists.newArrayList(chinaMessage.split("\\|"));

  String enMessage = MessageResources.getString(JMockMessage.FDATA_GENDER, Locale.ENGLISH);
  ArrayList<String> en = Lists.newArrayList(enMessage.split("\\|"));

  @BeforeAll
  public static void setLocale() {
    Locale.setDefault(Locale.CHINA);
  }

  @Test
  public void MGender() throws Exception {
    FunctionToken token = new FunctionToken("MGender", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGender mock = (MGender) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertTrue(china.contains(mock.mock()));
  }

  @Test
  public void MGender_1() throws Exception {
    FunctionToken token = new FunctionToken("MGender", new String[]{"F|M"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGender mock = (MGender) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(new String[]{"F", "M"}, mock.getDictArray());
  }

  @Test
  public void MGender_2() throws Exception {
    FunctionToken token = new FunctionToken("MGender", new String[]{"en"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MGender mock = (MGender) parser.parse(token);
    Assert.assertNotNull(mock);
    System.out.println("mock = " + mock.mock());
    Assert.assertTrue(en.contains(mock.mock()));
  }

}
