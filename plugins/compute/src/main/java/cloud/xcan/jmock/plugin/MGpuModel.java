package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_FRAMEWORK_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_GPU_MODEL_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_GPU_MODEL_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@JMockFunctionRegister(descI18nKey = DOC_GPU_MODEL_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5011)
public class MGpuModel extends AbstractMockFunction {

  private static final Random random = new Random();

  public static final List<String> NVIDIA_GPUS = Arrays.asList(
      "RTX 4090", "RTX 4080", "RTX 4070 Ti", "RTX 4070", "RTX 4060 Ti",
      "RTX 3090 Ti", "RTX 3080", "RTX 3070", "RTX 3060 Ti",
      "Quadro RTX 6000", "Tesla V100"
  );

  public static final List<String> AMD_GPUS = Arrays.asList(
      "RX 7900 XTX", "RX 7900 XT", "RX 7800 XT", "RX 7700 XT",
      "RX 6950 XT", "RX 6800 XT", "RX 6700 XT", "Radeon Pro W6800"
  );

  public static final List<String> INTEL_GPUS = Arrays.asList(
      "Arc A770", "Arc A750", "Arc A380", "Iris Xe MAX"
  );

  @JMockConstructor(descI18nKey = DOC_GPU_MODEL_C1,
      example = "@GpuModel()",
      exampleValues = {"NVIDIA RTX 4080", "NVIDIA Quadro RTX 6000"})
  public MGpuModel() {
  }

  @Override
  public String mock() {
    int manufacturer = random.nextInt(100);
    if (manufacturer < 75) {
      // NVIDIA (75% probability)
      return "NVIDIA " + NVIDIA_GPUS.get(random.nextInt(NVIDIA_GPUS.size()));
    } else if (manufacturer < 95) {
      // AMD (20% probability)
      return "AMD " + AMD_GPUS.get(random.nextInt(AMD_GPUS.size()));
    } else {
      // Intel (5% probability)
      return "Intel " + INTEL_GPUS.get(random.nextInt(INTEL_GPUS.size()));
    }
  }
}
