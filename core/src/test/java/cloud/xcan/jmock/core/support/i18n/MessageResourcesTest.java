package cloud.xcan.jmock.core.support.i18n;


import static cloud.xcan.jmock.api.i18n.MessageResources.RESOURCE_BUNDLE;

import cloud.xcan.jmock.api.i18n.MessageResources;
import java.util.Locale;
import java.util.ResourceBundle;
import org.junit.Assert;
import org.junit.Test;

public class MessageResourcesTest {

  String messageKey = "jmock.func.param.size.t";
  String enMessageTemplate = "MockParameter {0} must be between {1} and {2}";
  String enMessage = "MockParameter length must be between 1 and 10";
  String chMessageTemplate = "参数{0}个数必须在{1}和{2}之间";
  String chMessage = "参数length个数必须在1和10之间";

  @Test
  public void changeLocaleTest() {
    ResourceBundle RESOURCES = ResourceBundle.getBundle(RESOURCE_BUNDLE, Locale.US);
    Assert.assertEquals("en", RESOURCES.getLocale().getLanguage());
    Assert.assertEquals(enMessageTemplate, RESOURCES.getString(messageKey));

    Locale.setDefault(new Locale("zh", "CN"));
    RESOURCES = ResourceBundle.getBundle(RESOURCE_BUNDLE);
    Assert.assertEquals("zh", RESOURCES.getLocale().getLanguage());
    Assert.assertEquals(chMessageTemplate, RESOURCES.getString(messageKey));

    Locale.setDefault(new Locale("en"));
  }

  @Test
  public void getString() {
    String message = MessageResources.getString(messageKey, Locale.US);
    Assert.assertEquals(enMessageTemplate, message);
    message = MessageResources.getString(messageKey, new Locale("zh", "CN"));
    Assert.assertEquals(chMessageTemplate, message);
  }

  @Test
  public void getStringWithParamTest() {
    String message = MessageResources.getString(messageKey, new Object[]{"length", 1, 10},
        Locale.US);
    Assert.assertEquals(enMessage, message);
    message = MessageResources.getString(messageKey, new Object[]{"length", 1, 10},
        new Locale("zh", "CN"));
    Assert.assertEquals(chMessage, message);
  }

}
