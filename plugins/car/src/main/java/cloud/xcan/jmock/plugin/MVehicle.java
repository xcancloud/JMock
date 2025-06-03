package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_PLATE_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_VEHICLE_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_VEHICLE_DESC;
import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_VEHICLE_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2006)
public class MVehicle extends AbstractMockFunction {

  public static final List<String> VEHICLE_TYPES = Arrays.asList(
      "Sedan", "SUV", "Truck", "Hatchback", "Coupe",
      "Convertible", "Minivan", "Crossover", "Sports Car", "Electric Vehicle",
      "Station Wagon", "Pickup Truck", "Compact Car", "Luxury Car", "Hybrid Vehicle"
  );

  @JMockConstructor(descI18nKey = DOC_VEHICLE_C1,
      example = "@Vehicle()",
      exampleValues = {"Coupe", "SUV", "Station Wagon"})
  public MVehicle() {
  }

  public static void main(String[] args) {
    for (int i = 0; i < 3; i++) {
      System.out.println(new MVehicle().mock());
    }
  }

  @Override
  public String mock() {
    return VEHICLE_TYPES.get(random.nextInt(VEHICLE_TYPES.size()));
  }
}
