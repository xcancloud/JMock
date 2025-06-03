package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CAR;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_MDRIVETRAIN_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_MENGINE_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_MENGINE_DESC;
import static cloud.xcan.jmock.plugin.MBrand.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_MENGINE_DESC, categoryI18nKey = {
    DOC_CATEGORY_CAR}, order = 2003)
public class MEngine extends AbstractMockFunction {

  // List of engine types
  public static final List<String> ENGINE_TYPES = Arrays.asList(
      "1.5L I4 Turbo", "2.0L I4", "3.0L V6", "5.0L V8",
      "Electric Motor", "Hybrid (2.5L I4 + Electric)", "2.0L Diesel",
      "3.5L V6 Turbo", "2.4L Hybrid", "1.0L Turbo", "1.8L Hybrid",
      "2.2L Diesel", "4.0L V8", "2.5L Boxer", "3.3L V6"
  );

  @JMockConstructor(descI18nKey = DOC_MENGINE_C1,
      example = "@Engine()",
      exampleValues = {"2.2L Diesel", "3.5L V6 Turbo", "1.5L I4 Turbo"})
  public MEngine() {
  }

  @Override
  public String mock() {
    return ENGINE_TYPES.get(random.nextInt(ENGINE_TYPES.size()));
  }

}
