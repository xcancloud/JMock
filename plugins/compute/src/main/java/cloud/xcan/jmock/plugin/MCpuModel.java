package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CPU_MODEL_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CPU_MODEL_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_CPU_MODEL_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5005)
public class MCpuModel extends AbstractMockFunction {

  public static final List<String> INTEL_CPUS = Arrays.asList(
      "Core i3-12100", "Core i5-13400", "Core i7-13700K", "Core i9-13900K",
      "Xeon W-1390P", "Xeon E-2388G", "Celeron G6900", "Pentium Gold G7400"
  );

  public static final List<String> AMD_CPUS = Arrays.asList(
      "Ryzen 3 7300X", "Ryzen 5 7600X", "Ryzen 7 7800X3D", "Ryzen 9 7950X",
      "Threadripper PRO 5995WX", "EPYC 9654", "Athlon Gold 7220U"
  );

  public static final List<String> APPLE_CPUS = Arrays.asList(
      "Apple M1", "Apple M1 Pro", "Apple M1 Max", "Apple M1 Ultra",
      "Apple M2", "Apple M2 Pro", "Apple M2 Max", "Apple M2 Ultra"
  );

  @JMockConstructor(descI18nKey = DOC_CPU_MODEL_C1,
      example = "@MCloudService()",
      exampleValues = {"Intel Xeon W-1390P", "Intel Core i9-13900K"})
  public MCpuModel() {
  }

  @Override
  public String mock() {
    int manufacturer = random.nextInt(100);
    if (manufacturer < 70) {
      // Intel (70% probability)
      return "Intel " + INTEL_CPUS.get(random.nextInt(INTEL_CPUS.size()));
    } else if (manufacturer < 95) {
      // AMD (25% probability)
      return "AMD " + AMD_CPUS.get(random.nextInt(AMD_CPUS.size()));
    } else {
      // Apple Silicon (5% probability)
      return APPLE_CPUS.get(random.nextInt(APPLE_CPUS.size()));
    }
  }
}
