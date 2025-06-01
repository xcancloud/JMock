package cloud.xcan.jmock.core.support.revreg;

import com.mifmif.common.regex.Generex;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RegRandom {

  private static final Map<String, Generex> GENEREX_MAP = new ConcurrentHashMap<>();

  public static String random(String expression) {
    Generex generex = getGenerex(expression);
    return generex.random();
  }

  private static Generex getGenerex(String expression) {
    Generex generex;
    if (GENEREX_MAP.containsKey(expression)) {
      generex = GENEREX_MAP.get(expression);
    } else {
      generex = new Generex(expression);
      GENEREX_MAP.put(expression, generex);
    }
    return generex;
  }

}
