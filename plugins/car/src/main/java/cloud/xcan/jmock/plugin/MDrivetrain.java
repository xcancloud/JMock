package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_DRIVETRAIN_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_DRIVETRAIN_DESC;
import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_DRIVETRAIN_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2002)
public class MDrivetrain extends AbstractMockFunction {

  // List of drivetrain types
  public static final List<String> DRIVETRAIN_TYPES = Arrays.asList(
      "FWD (Front-Wheel Drive)", "RWD (Rear-Wheel Drive)", "AWD (All-Wheel Drive)",
      "4WD (Four-Wheel Drive)", "Part-time 4WD", "Electric Drive"
  );

  @JMockConstructor(descI18nKey = DOC_DRIVETRAIN_C1,
      example = "@Drivetrain()",
      exampleValues = {"AWD (All-Wheel Drive)", "RWD (Rear-Wheel Drive)",
          "FWD (Front-Wheel Drive)"})
  public MDrivetrain() {
  }

  @Override
  public String mock() {
    return DRIVETRAIN_TYPES.get(random.nextInt(DRIVETRAIN_TYPES.size()));
  }

}
