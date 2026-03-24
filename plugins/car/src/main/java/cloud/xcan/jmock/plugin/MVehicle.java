package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.CarDocMessage.DATA_VEHICLE_TYPES;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_VEHICLE_C1;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_VEHICLE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_VEHICLE_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2006)
public class MVehicle extends AbstractMockFunction {

  private static final String DEFAULT_VEHICLE_TYPES =
      "Sedan|SUV|Truck|Hatchback|Coupe|Convertible|Minivan|Crossover|Sports Car|Electric Vehicle|"
          + "Station Wagon|Pickup Truck|Compact Car|Luxury Car|Hybrid Vehicle";

  private final String[] vehicleTypes;

  @JMockConstructor(descI18nKey = DOC_VEHICLE_C1,
      example = "@Vehicle()",
      exampleValues = {"Coupe", "SUV", "Station Wagon"})
  public MVehicle() {
    this.vehicleTypes = CarPluginMessages.pipeDict(DATA_VEHICLE_TYPES, DEFAULT_VEHICLE_TYPES);
  }

  @Override
  public String mock() {
    return vehicleTypes[JMockRandom.nextInt(vehicleTypes.length)];
  }
}
