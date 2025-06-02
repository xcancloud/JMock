package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MDevice extends AbstractMockFunction {

  public static final List<String> COMPUTER_NAMES = Arrays.asList(
      "Workstation", "Laptop", "Desktop", "Server", "Mainframe", "Terminal"
  );

  public static final List<String> MOBILE_DEVICES = Arrays.asList(
      "iPhone", "iPad", "Android Phone", "Android Tablet", "Smart Watch", "E-reader"
  );

  public static final List<String> IOT_DEVICES = Arrays.asList(
      "Smart Thermostat", "Security Camera", "Smart Speaker", "Router",
      "NAS Device", "Smart TV", "Gaming Console"
  );

  public static final List<String> DEVICE_MODIFIERS = Arrays.asList(
      "Pro", "Max", "Lite", "Ultra", "Mini", "Plus", "SE", "X", "Z"
  );

  public MDevice() {
  }

  @Override
  public String mock() {
    int category = random.nextInt(100);
    if (category < 60) {
      // Computer devices (60% probability)
      String name = COMPUTER_NAMES.get(random.nextInt(COMPUTER_NAMES.size()));
      if (random.nextDouble() < 0.4) {
        name += " " + DEVICE_MODIFIERS.get(random.nextInt(DEVICE_MODIFIERS.size()));
      }
      return name;
    } else if (category < 85) {
      // Mobile devices (25% probability)
      String name = MOBILE_DEVICES.get(random.nextInt(MOBILE_DEVICES.size()));
      if (random.nextDouble() < 0.6) {
        name += " " + (random.nextInt(15) + 1);
      }
      return name;
    } else {
      // IoT devices (15% probability)
      return IOT_DEVICES.get(random.nextInt(IOT_DEVICES.size()));
    }
  }
}
