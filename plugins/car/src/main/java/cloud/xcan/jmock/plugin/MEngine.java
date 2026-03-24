package cloud.xcan.jmock.plugin;

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

  private static final String DEFAULT_ENGINE_TYPES =
      "1.5L I4 Turbo|2.0L I4|3.0L V6|5.0L V8|Electric Motor|Hybrid (2.5L I4 + Electric)|2.0L Diesel|"
          + "3.5L V6 Turbo|2.4L Hybrid|1.0L Turbo|1.8L Hybrid|2.2L Diesel|4.0L V8|2.5L Boxer|3.3L V6";

  private final String[] engineTypes;

  @JMockConstructor(descI18nKey = DOC_ENGINE_C1,
      example = "@Engine()",
      exampleValues = {"2.2L Diesel", "3.5L V6 Turbo", "1.5L I4 Turbo"})
  public MEngine() {
    this.engineTypes = CarPluginMessages.pipeDict(DATA_ENGINE_TYPES, DEFAULT_ENGINE_TYPES);
  }

  @Override
  public String mock() {
    return engineTypes[JMockRandom.nextInt(engineTypes.length)];
  }

}
