package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.CarDocMessage.DATA_CAR_BRANDS;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_BRAND_C1;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_BRAND_DESC;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_CATEGORY_CAR;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_BRAND_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2001)
public class MBrand extends AbstractMockFunction {

  private static final String DEFAULT_BRANDS =
      "BYD|Geely|Chery|Great Wall|Changan|FAW|SAIC Motor|NIO|XPeng|Li Auto|Hongqi|BAIC|GAC|JAC|"
          + "Dongfeng|Toyota|Honda|Ford|Chevrolet|Volkswagen|BMW|Mercedes-Benz|Audi|Tesla|Nissan|"
          + "Hyundai|Kia|Volvo|Subaru|Mazda|Peugeot|Renault|Fiat|Jeep|Lexus";

  private final String[] brands;

  @JMockConstructor(descI18nKey = DOC_BRAND_C1,
      example = "@Brand()",
      exampleValues = {"Peugeot", "Nissan", "Chery"})
  public MBrand() {
    this.brands = CarPluginMessages.pipeDict(DATA_CAR_BRANDS, DEFAULT_BRANDS);
  }

  @Override
  public String mock() {
    return brands[JMockRandom.nextInt(brands.length)];
  }

}
