
package cloud.xcan.comp.jmock.api.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Get locale messages from Resource Bundle.
 *
 * @author XiaoLong Liu
 */
public class MessageResources {

  public static final String RESOURCE_BUNDLE = "i18n/jmock-messages";

  /**
   * You cannot instantiate this class.
   */
  private MessageResources() {
  }

  /**
   * Looks up a mstring resource identified by {@code key} in {@code resources}.
   */
  public static String getString(String key) {
    return ResourceBundle.getBundle(RESOURCE_BUNDLE).getString(key);
  }

  /**
   * Looks up a mstring resource identified by {@code key} in {@code resources}.
   */
  public static String getString(String key, Locale locale) {
    return ResourceBundle.getBundle(RESOURCE_BUNDLE, locale).getString(key);
  }

  /**
   * Looks up a mstring resource identified by {@code key} in {@code resources} and formats it as a
   * message using {@code MessageFormat.format} with the given {@code arguments}.
   */
  public static String getString(String key, Object[] arguments) {
    return MessageFormat.format(getString(key), arguments);
  }

  /**
   * Looks up a mstring resource identified by {@code key} in {@code resources} and formats it as a
   * message using {@code MessageFormat.format} with the given {@code arguments}.
   */
  public static String getString(String key, Object[] arguments, Locale locale) {
    return MessageFormat.format(getString(key, locale), arguments);
  }

}
