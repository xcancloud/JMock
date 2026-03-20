package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_AMD_CPUS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_APPLE_CPUS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_INTEL_CPUS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CPU_MODEL_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CPU_MODEL_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_CPU_MODEL_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5005)
public class MCpuModel extends AbstractMockFunction {

  private final String[] intelCpus;
  private final String[] amdCpus;
  private final String[] appleCpus;

  @JMockConstructor(descI18nKey = DOC_CPU_MODEL_C1,
      example = "@MCloudService()",
      exampleValues = {"Intel Xeon W-1390P", "Intel Core i9-13900K"})
  public MCpuModel() {
    this.intelCpus = getString(DATA_INTEL_CPUS).split("\\|");
    this.amdCpus = getString(DATA_AMD_CPUS).split("\\|");
    this.appleCpus = getString(DATA_APPLE_CPUS).split("\\|");
  }

  @Override
  public String mock() {
    int manufacturer = JMockRandom.nextInt(100);
    if (manufacturer < 70) {
      return "Intel " + intelCpus[JMockRandom.nextInt(intelCpus.length)];
    } else if (manufacturer < 95) {
      return "AMD " + amdCpus[JMockRandom.nextInt(amdCpus.length)];
    } else {
      return appleCpus[JMockRandom.nextInt(appleCpus.length)];
    }
  }
}
