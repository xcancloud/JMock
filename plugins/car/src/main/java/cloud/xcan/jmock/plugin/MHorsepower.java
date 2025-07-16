package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_HORSEPOWER_C1;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_HORSEPOWER_DESC;
import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_HORSEPOWER_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2004)
public class MHorsepower extends AbstractMockFunction {

  // Horsepower range constants
  private static final int MIN_HORSEPOWER = 70;
  private static final int MAX_HORSEPOWER = 1000;

  @JMockConstructor(descI18nKey = DOC_HORSEPOWER_C1,
      example = "@Horsepower()",
      exampleValues = {"723 HP", "71 HP", "982 HP"})
  public MHorsepower() {
  }

  @Override
  public String mock() {
    int hp = random.nextInt(MIN_HORSEPOWER, MAX_HORSEPOWER + 1);
    return hp + " HP";
  }

}
