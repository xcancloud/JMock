package cloud.xcan.jmock.plugin;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Formatter {

  public static String format(String pattern, Object... arguments) {
    if (pattern == null) {
      return null;
    }

    if (arguments == null || arguments.length == 0) {
      return pattern;
    }

    Pattern placeholderPattern = Pattern.compile("\\{(\\d+)\\}");
    Matcher matcher = placeholderPattern.matcher(pattern);

    StringBuilder result = new StringBuilder();
    int lastIndex = 0;

    while (matcher.find()) {
      result.append(pattern, lastIndex, matcher.start());

      try {
        int index = Integer.parseInt(matcher.group(1));
        if (index >= 0 && index < arguments.length) {
          result.append(arguments[index]);
        } else {
          result.append(matcher.group());
        }
      } catch (NumberFormatException e) {
        result.append(matcher.group());
      }

      lastIndex = matcher.end();
    }

    result.append(pattern.substring(lastIndex));

    return result.toString();
  }

  public static String namedFormat(String pattern, Map<String, Object> params) {
    if (pattern == null) {
      return null;
    }

    if (params == null || params.isEmpty()) {
      return pattern;
    }

    Pattern placeholderPattern = Pattern.compile("\\{([a-zA-Z_][a-zA-Z0-9_]*)\\}");
    Matcher matcher = placeholderPattern.matcher(pattern);

    StringBuilder result = new StringBuilder();
    int lastIndex = 0;

    while (matcher.find()) {
      result.append(pattern, lastIndex, matcher.start());

      String paramName = matcher.group(1);
      if (params.containsKey(paramName)) {
        result.append(params.get(paramName));
      } else {
        result.append(matcher.group());
      }

      lastIndex = matcher.end();
    }

    result.append(pattern.substring(lastIndex));
    return result.toString();
  }

}
