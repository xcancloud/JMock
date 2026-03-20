package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.CarDocMessage.DATA_DRIVETRAIN_TYPES;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_DRIVETRAIN_C1;
import static cloud.xcan.jmock.plugin.CarDocMessage.DOC_DRIVETRAIN_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_DRIVETRAIN_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2002)
public class MDrivetrain extends AbstractMockFunction {

  private final String[] drivetrainTypes;

  @JMockConstructor(descI18nKey = DOC_DRIVETRAIN_C1,
      example = "@Drivetrain()",
      exampleValues = {"AWD (All-Wheel Drive)", "RWD (Rear-Wheel Drive)",
          "FWD (Front-Wheel Drive)"})
  public MDrivetrain() {
    this.drivetrainTypes = getString(DATA_DRIVETRAIN_TYPES).split("\\|");
  }

  @Override
  public String mock() {
    return drivetrainTypes[JMockRandom.nextInt(drivetrainTypes.length)];
  }

}
