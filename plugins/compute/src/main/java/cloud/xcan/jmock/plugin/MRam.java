package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_RAM_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_RAM_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_RAM_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5004)
public class MRam extends AbstractMockFunction {

  public static final List<Integer> RAM_SIZES = Arrays.asList(4, 8, 16, 32, 64, 128);
  public static final List<String> RAM_TYPES = Arrays.asList("DDR4", "DDR5", "LPDDR4X", "LPDDR5");
  public static final List<Integer> RAM_SPEEDS = Arrays.asList(2400, 2666, 2933, 3200, 3600, 4800,
      5200, 5600);

  @JMockConstructor(descI18nKey = DOC_RAM_C1,
      example = "@Ram()",
      exampleValues = {"128GB DDR5 2933MHz", "32GB DDR4 5600MHz"})
  public MRam() {
  }

  @Override
  public String mock() {
    int size = RAM_SIZES.get(random.nextInt(RAM_SIZES.size()));
    String type = RAM_TYPES.get(random.nextInt(RAM_TYPES.size()));
    int speed = RAM_SPEEDS.get(random.nextInt(RAM_SPEEDS.size()));

    return size + "GB " + type + " " + speed + "MHz";
  }
}
