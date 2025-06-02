package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MEngine extends AbstractMockFunction {

  // List of engine types
  public static final List<String> ENGINE_TYPES = Arrays.asList(
      "1.5L I4 Turbo", "2.0L I4", "3.0L V6", "5.0L V8",
      "Electric Motor", "Hybrid (2.5L I4 + Electric)", "2.0L Diesel",
      "3.5L V6 Turbo", "2.4L Hybrid", "1.0L Turbo", "1.8L Hybrid",
      "2.2L Diesel", "4.0L V8", "2.5L Boxer", "3.3L V6"
  );

  public MEngine() {
  }

  @Override
  public String mock() {
    return ENGINE_TYPES.get(random.nextInt(ENGINE_TYPES.size()));
  }

}
