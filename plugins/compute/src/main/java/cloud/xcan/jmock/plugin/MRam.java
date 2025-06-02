package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MRam extends AbstractMockFunction {

  public static final List<Integer> RAM_SIZES = Arrays.asList(4, 8, 16, 32, 64, 128);
  public static final List<String> RAM_TYPES = Arrays.asList("DDR4", "DDR5", "LPDDR4X", "LPDDR5");
  public static final List<Integer> RAM_SPEEDS = Arrays.asList(2400, 2666, 2933, 3200, 3600, 4800,
      5200, 5600);

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
