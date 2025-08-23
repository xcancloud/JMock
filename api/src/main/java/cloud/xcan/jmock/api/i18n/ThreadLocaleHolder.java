package cloud.xcan.jmock.api.i18n;

import java.util.Locale;

public class ThreadLocaleHolder {

  public static ThreadLocal<Locale> localeHolder = new InheritableThreadLocal<>();

  public static void setLocale(Locale locale) {
    localeHolder.set(locale);
  }

  public static Locale getLocale() {
    Locale locale = localeHolder.get();
    return locale == null ? Locale.getDefault() : locale;
  }

  public static void removeLocale() {
    localeHolder.remove();
  }
}
