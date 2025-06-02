package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MVehicle extends AbstractMockFunction {

  public static final List<String> VEHICLE_TYPES = Arrays.asList(
      "Sedan", "SUV", "Truck", "Hatchback", "Coupe",
      "Convertible", "Minivan", "Crossover", "Sports Car", "Electric Vehicle",
      "Station Wagon", "Pickup Truck", "Compact Car", "Luxury Car", "Hybrid Vehicle"
  );

  public MVehicle() {
  }

  @Override
  public String mock() {
    return VEHICLE_TYPES.get(random.nextInt(VEHICLE_TYPES.size()));
  }
}
