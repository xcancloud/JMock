package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
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

  private final String[] vehicleTypes;

  @JMockConstructor(descI18nKey = DOC_VEHICLE_C1,
      example = "@Vehicle()",
      exampleValues = {"Coupe", "SUV", "Station Wagon"})
  public MVehicle() {
    this.vehicleTypes = getString(DATA_VEHICLE_TYPES).split("\\|");
  }

  @Override
  public String mock() {
    return vehicleTypes[JMockRandom.nextInt(vehicleTypes.length)];
  }
}
