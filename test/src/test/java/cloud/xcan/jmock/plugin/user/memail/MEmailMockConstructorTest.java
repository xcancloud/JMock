package cloud.xcan.jmock.plugin.user.memail;


import cloud.xcan.jmock.api.FunctionToken;
import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.core.parser.SimpleMockFunctionTokenParser;
import cloud.xcan.jmock.plugin.MEmail;
import cloud.xcan.jmock.plugin.UserDocMessage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MEmailMockConstructorTest {

  String appName = MessageResources.getString(UserDocMessage.DATA_EMAIL);


  @Test
  public void MEmail() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(20, mock.getMax());
    Assertions.assertEquals(6, mock.getMin());
    Assertions.assertArrayEquals(appName.split("\\|"), mock.getSuffixArray());
  }

  @Test
  public void MEmail_1() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"1", "10"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assertions.assertNotNull(mock);
    Assertions.assertEquals(10, mock.getMax());
    Assertions.assertEquals(1, mock.getMin());
  }

  @Test
  public void MEmail_2() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"wx|qw"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assertions.assertNotNull(mock);
    System.out.println("mock = " + mock.mock());
    Assertions.assertArrayEquals(new String[]{"wx", "qw"}, mock.getSuffixArray());
  }


  @Test
  public void MEmail_3() throws Exception {
    FunctionToken token = new FunctionToken("MEmail", new String[]{"1", "10", "wx|qw"});
    SimpleMockFunctionTokenParser parser = new SimpleMockFunctionTokenParser();
    MEmail mock = (MEmail) parser.parse(token);
    Assertions.assertNotNull(mock);
    System.out.println("mock = " + mock.mock());
    Assertions.assertEquals(10, mock.getMax());
    Assertions.assertEquals(1, mock.getMin());
    Assertions.assertArrayEquals(new String[]{"wx", "qw"}, mock.getSuffixArray());
  }
}
