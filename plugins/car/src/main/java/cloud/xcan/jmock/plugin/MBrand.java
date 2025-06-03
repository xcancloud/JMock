package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MWORD_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_MBRAND_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_MBRAND_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_MBRAND_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2001)
public class MBrand extends AbstractMockFunction {

  public static final SecureRandom random = new SecureRandom();

  // List of car brands including both Chinese domestic and international brands
  public static final List<String> CAR_BRANDS = Arrays.asList(
      // Chinese domestic brands
      "BYD", "Geely", "Chery", "Great Wall", "Changan",
      "FAW", "SAIC Motor", "NIO", "XPeng", "Li Auto",
      "Hongqi", "BAIC", "GAC", "JAC", "Dongfeng",

      // International brands
      "Toyota", "Honda", "Ford", "Chevrolet", "Volkswagen",
      "BMW", "Mercedes-Benz", "Audi", "Tesla", "Nissan",
      "Hyundai", "Kia", "Volvo", "Subaru", "Mazda",
      "Peugeot", "Renault", "Fiat", "Jeep", "Lexus"
  );

  @JMockConstructor(descI18nKey = DOC_MBRAND_C1,
      example = "@Brand()",
      exampleValues = {"Peugeot", "Nissan", "Chery"})
  public MBrand() {
  }

  @Override
  public String mock() {
    return CAR_BRANDS.get(random.nextInt(CAR_BRANDS.size()));
  }

}
