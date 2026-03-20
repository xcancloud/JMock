package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.CarDocMessage.DATA_ENGINE_TYPES;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_ENGINE_C1;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_ENGINE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_ENGINE_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2003)
public class MEngine extends AbstractMockFunction {

  private final String[] engineTypes;

  @JMockConstructor(descI18nKey = DOC_ENGINE_C1,
      example = "@Engine()",
      exampleValues = {"2.2L Diesel", "3.5L V6 Turbo", "1.5L I4 Turbo"})
  public MEngine() {
    this.engineTypes = getString(DATA_ENGINE_TYPES).split("\\|");
  }

  @Override
  public String mock() {
    return engineTypes[JMockRandom.nextInt(engineTypes.length)];
  }

}
