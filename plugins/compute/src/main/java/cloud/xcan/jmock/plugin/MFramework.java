package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_BACKEND_FRAMEWORKS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_FRONTEND_FRAMEWORKS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_MOBILE_FRAMEWORKS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FRAMEWORK_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FRAMEWORK_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_FRAMEWORK_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5010)
public class MFramework extends AbstractMockFunction {

  private final String[] frontendFrameworks;
  private final String[] backendFrameworks;
  private final String[] mobileFrameworks;

  @JMockConstructor(descI18nKey = DOC_FRAMEWORK_C1,
      example = "@Framework()",
      exampleValues = {"React Native", "Ruby on Rails"})
  public MFramework() {
    this.frontendFrameworks = getString(DATA_FRONTEND_FRAMEWORKS).split("\\|");
    this.backendFrameworks = getString(DATA_BACKEND_FRAMEWORKS).split("\\|");
    this.mobileFrameworks = getString(DATA_MOBILE_FRAMEWORKS).split("\\|");
  }

  @Override
  public String mock() {
    int category = JMockRandom.nextInt(100);
    if (category < 40) {
      return frontendFrameworks[JMockRandom.nextInt(frontendFrameworks.length)];
    } else if (category < 80) {
      return backendFrameworks[JMockRandom.nextInt(backendFrameworks.length)];
    } else {
      return mobileFrameworks[JMockRandom.nextInt(mobileFrameworks.length)];
    }
  }
}
