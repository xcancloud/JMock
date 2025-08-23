package cloud.xcan.jmock.api.i18n;

import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Get locale messages from Resource Bundle.
 *
 * @author XiaoLong Liu
 */
public class MessageResources {

  public static final Set<String> RESOURCE_BUNDLE = new HashSet<>();

  static {
    RESOURCE_BUNDLE.add("i18n/jmock-messages");
  }

  /**
   * You cannot instantiate this class.
   */
  private MessageResources() {
  }

  /**
   * Looks up a string resource identified by {@code key} in {@code resources}.
   */
  public static String getString(String key) {
    for (String res : RESOURCE_BUNDLE) {
      ResourceBundle bundle = ResourceBundle.getBundle(res);
      if (bundle.containsKey(key)) {
        return bundle.getString(key);
      }
    }
    return "";
  }

  /**
   * Looks up a string resource identified by {@code key} in {@code resources}.
   */
  public static String getString(String key, Locale locale) {
    for (String res : RESOURCE_BUNDLE) {
      ResourceBundle bundle = ResourceBundle.getBundle(res, locale);
      if (bundle.containsKey(key)) {
        return bundle.getString(key);
      }
    }
    return "";
  }

  /**
   * Looks up a string resource identified by {@code key} in {@code resources} and formats it as a
   * message using {@code MessageFormat.format} with the given {@code arguments}.
   */
  public static String getString(String key, Object[] arguments) {
    return MessageFormat.format(getString(key), arguments);
  }

  /**
   * Looks up a string resource identified by {@code key} in {@code resources} and formats it as a
   * message using {@code MessageFormat.format} with the given {@code arguments}.
   */
  public static String getString(String key, Object[] arguments, Locale locale) {
    return MessageFormat.format(getString(key, locale), arguments);
  }

}
