package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_RAM_SIZES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_RAM_SPEEDS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_RAM_TYPES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_RAM_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_RAM_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_RAM_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5004)
public class MRam extends AbstractMockFunction {

  private final String[] ramSizes;
  private final String[] ramTypes;
  private final String[] ramSpeeds;

  @JMockConstructor(descI18nKey = DOC_RAM_C1,
      example = "@Ram()",
      exampleValues = {"128GB DDR5 2933MHz", "32GB DDR4 5600MHz"})
  public MRam() {
    this.ramSizes = getString(DATA_RAM_SIZES).split("\\|");
    this.ramTypes = getString(DATA_RAM_TYPES).split("\\|");
    this.ramSpeeds = getString(DATA_RAM_SPEEDS).split("\\|");
  }

  @Override
  public String mock() {
    String size = ramSizes[JMockRandom.nextInt(ramSizes.length)];
    String type = ramTypes[JMockRandom.nextInt(ramTypes.length)];
    String speed = ramSpeeds[JMockRandom.nextInt(ramSpeeds.length)];

    return size + "GB " + type + " " + speed + "MHz";
  }
}
