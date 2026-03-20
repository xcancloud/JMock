package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
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

  private final String[] brands;

  @JMockConstructor(descI18nKey = DOC_BRAND_C1,
      example = "@Brand()",
      exampleValues = {"Peugeot", "Nissan", "Chery"})
  public MBrand() {
    this.brands = getString(DATA_CAR_BRANDS).split("\\|");
  }

  @Override
  public String mock() {
    return brands[JMockRandom.nextInt(brands.length)];
  }

}
