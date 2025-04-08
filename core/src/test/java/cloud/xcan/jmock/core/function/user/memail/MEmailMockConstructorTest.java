package cloud.xcan.jmock.core.function.user.memail;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.function.user.MEmail;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class MEmailMockConstructorTest {

  String appName = MessageResources.getString(JMockMessage.FDATA_EMAIL);


  @Test
  public void MEmail() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(20, mock.getMax());
    Assert.assertEquals(6, mock.getMin());
    Assert.assertArrayEquals(appName.split("\\|"), mock.getSuffixArray());
  }

  @Test
  public void MEmail_1() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"1", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assert.assertNotNull(mock);
    Assert.assertEquals(10, mock.getMax());
    Assert.assertEquals(1, mock.getMin());
  }

  @Test
  public void MEmail_2() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"wx|qw"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assert.assertNotNull(mock);
    System.out.println("mock = " + mock.mock());
    Assert.assertArrayEquals(new String[]{"wx", "qw"}, mock.getSuffixArray());
  }


  @Test
  public void MEmail_3() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"1", "10", "wx|qw"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assert.assertNotNull(mock);
    System.out.println("mock = " + mock.mock());
    Assert.assertEquals(10, mock.getMax());
    Assert.assertEquals(1, mock.getMin());
    Assert.assertArrayEquals(new String[]{"wx", "qw"}, mock.getSuffixArray());
  }
}
