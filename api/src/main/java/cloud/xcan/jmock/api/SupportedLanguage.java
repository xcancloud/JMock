package cloud.xcan.jmock.api;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import java.util.Locale;

public enum SupportedLanguage {
  en,
  zh_CN;

  public Locale toLocale() {
    if (this == SupportedLanguage.en) {
      return Locale.ENGLISH;
    }
    return Locale.CHINA;
  }

  public static boolean contain(String value) {
    if (isEmpty(value)) {
      return false;
    }
    for (SupportedLanguage language : SupportedLanguage.values()) {
      if (language.name().equals(value)) {
        return true;
      }
    }
    return false;
  }

}
