package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_AMD_GPUS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_INTEL_GPUS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_NVIDIA_GPUS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_GPU_MODEL_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_GPU_MODEL_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_GPU_MODEL_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5011)
public class MGpuModel extends AbstractMockFunction {

  private final String[] nvidiaGpus;
  private final String[] amdGpus;
  private final String[] intelGpus;

  @JMockConstructor(descI18nKey = DOC_GPU_MODEL_C1,
      example = "@GpuModel()",
      exampleValues = {"NVIDIA RTX 4080", "NVIDIA Quadro RTX 6000"})
  public MGpuModel() {
    this.nvidiaGpus = getString(DATA_NVIDIA_GPUS).split("\\|");
    this.amdGpus = getString(DATA_AMD_GPUS).split("\\|");
    this.intelGpus = getString(DATA_INTEL_GPUS).split("\\|");
  }

  @Override
  public String mock() {
    int manufacturer = JMockRandom.nextInt(100);
    if (manufacturer < 75) {
      return "NVIDIA " + nvidiaGpus[JMockRandom.nextInt(nvidiaGpus.length)];
    } else if (manufacturer < 95) {
      return "AMD " + amdGpus[JMockRandom.nextInt(amdGpus.length)];
    } else {
      return "Intel " + intelGpus[JMockRandom.nextInt(intelGpus.length)];
    }
  }
}
