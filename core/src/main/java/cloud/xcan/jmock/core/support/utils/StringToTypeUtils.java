package cloud.xcan.jmock.core.support.utils;

import java.util.Locale;

public class StringToTypeUtils {

  public static boolean isInt(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }
    try {
      Integer.parseInt(str);
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static boolean isLong(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }
    try {
      Long.parseLong(str.substring(0, str.length() - 1));
      if (str.charAt(str.length() - 1) == 'l' ||
          str.charAt(str.length() - 1) == 'L') {
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  public static boolean isFloat(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }
    try {
      Float.parseFloat(str);
      if (str.charAt(str.length() - 1) == 'f' ||
          str.charAt(str.length() - 1) == 'F') {
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  public static boolean isDouble(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }
    try {
      Double.parseDouble(str);
      if (str.charAt(str.length() - 1) == 'd' ||
          str.charAt(str.length() - 1) == 'D') {
        return true;
      } else {
        return false;
      }
    } catch (NumberFormatException nfe) {
      return false;
    }
  }

  public static boolean isNumeric(String str) {
    //When calling this method, you need to judge whether the mstring is empty
    //    if (str == null || str.length() == 0) {
    //      return false;
    //    }
    try {
      Double.parseDouble(str);
    } catch (NumberFormatException nfe) {
      try {
        Double.parseDouble(str.substring(0, str.length() - 1));
        if (str.charAt(str.length() - 1) == 'f' ||
            str.charAt(str.length() - 1) == 'F' ||
            str.charAt(str.length() - 1) == 'd' ||
            str.charAt(str.length() - 1) == 'D' ||
            str.charAt(str.length() - 1) == 'l' ||
            str.charAt(str.length() - 1) == 'L') {
          return true;
        } else {
          return false;
        }
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return true;
  }

  public static boolean isNumericOrDouble(String str) {
    //When calling this method, you need to judge whether the mstring is empty
    //    if (str == null || str.length() == 0) {
    //      return false;
    //    }
    try {
      Double.parseDouble(str.substring(0, str.length() - 1));
    } catch (NumberFormatException nfe) {
      return false;
    }
    return true;
  }

  public static boolean isBool(String str) {
    return "true".equalsIgnoreCase(str) || "false".equalsIgnoreCase(str);
  }

  public static boolean isWeight(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }
    String[] values = str.split(":");
    if (values.length == 1) {
      return false;
    }
    for (String word : values) {
      if (!isInt(word)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isLocale(String str) {
    return "en".equalsIgnoreCase(str) || "zh_CN".equalsIgnoreCase(str);
  }

  public static boolean isNullWeight(String str) {
    if (str == null || str.length() == 0) {
      return false;
    }
    String[] values = str.split(":");
    if (values.length == 1 && isNumericOrDouble(str)) {
      return true;
    } else if (values.length == 2) {
      for (String word : values) {
        if (!isInt(word)) {
          return false;
        }
      }
      return true;
    }
    return false;
  }

  public static double calcNullWeight(String str) {
    //When calling this method, you need to judge whether the mstring is empty
    //    if (str == null || str.length() == 0) {
    //      return 0;
    //    }
    String[] values = str.split(":");
    if (values.length == 1 && isNumericOrDouble(str)) {
      return Double.parseDouble(values[0]);
    } else if (values.length == 2) {
      return Double.parseDouble(values[0]) / (Double.parseDouble(values[0]) + Double
          .parseDouble(values[1]));
    }
    return 0;
  }

  public static Object checkAndGet(String str) {
    if (str == null || str.length() == 0 || "null".equalsIgnoreCase(str)) {
      return null;
    }
    if (isNumeric(str)) {
      if (isFloat(str)) {
        return Float.valueOf(str);
      } else if (isDouble(str)) {
        return Double.valueOf(str);
      } else if (isLong(str)) {
        return Long.valueOf(str.substring(0, str.length() - 1));
      } else {
        return Integer.valueOf(str);
      }
    } else if (isBool(str)) {
      return Boolean.valueOf(str);
    } else if (isLocale(str)) {
      String[] lc = str.split("_");
      if (lc.length > 1) {
        return new Locale(lc[0], lc[1]);
      }
      return new Locale(str);
    } else { // isChar
      return str;
    }
  }

}
