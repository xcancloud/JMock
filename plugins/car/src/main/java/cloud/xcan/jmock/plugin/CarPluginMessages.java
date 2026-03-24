package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import java.util.Arrays;

/**
 * Ensures car-plugin i18n is registered and resolves {@code key|key|...} data with trim / empty
 * filtering and fallback when bundles are not loaded (e.g. direct {@code new MVehicle()} in tests).
 */
final class CarPluginMessages {

  private CarPluginMessages() {
  }

  static String[] pipeDict(String messageKey, String fallbackPipeSeparated) {
    String raw = MessageResources.getString(messageKey);
    String[] filtered = Arrays.stream(raw.split("\\|", -1))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .toArray(String[]::new);
    if (filtered.length > 0) {
      return filtered;
    }
    return Arrays.stream(fallbackPipeSeparated.split("\\|"))
        .map(String::trim)
        .filter(s -> !s.isEmpty())
        .toArray(String[]::new);
  }
}
