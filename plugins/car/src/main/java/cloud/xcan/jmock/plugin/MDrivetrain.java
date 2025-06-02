package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MDrivetrain extends AbstractMockFunction {

  // List of drivetrain types
  public static final List<String> DRIVETRAIN_TYPES = Arrays.asList(
      "FWD (Front-Wheel Drive)", "RWD (Rear-Wheel Drive)", "AWD (All-Wheel Drive)",
      "4WD (Four-Wheel Drive)", "Part-time 4WD", "Electric Drive"
  );

  public MDrivetrain() {
  }

  @Override
  public String mock() {
    return DRIVETRAIN_TYPES.get(random.nextInt(DRIVETRAIN_TYPES.size()));
  }
}
