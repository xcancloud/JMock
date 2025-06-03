package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ARTICLE;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MARTICLE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MARTICLE_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARTICLE}, order = 100000)
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

  public MBrand() {
  }

  @Override
  public String mock() {
    return CAR_BRANDS.get(random.nextInt(CAR_BRANDS.size()));
  }

}
