package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_COMPUTER_NAMES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_DEVICE_MODIFIERS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_IOT_DEVICES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_MOBILE_DEVICES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_DEVICE_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_DEVICE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_DEVICE_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5007)
public class MDevice extends AbstractMockFunction {

  private final String[] computerNames;
  private final String[] mobileDevices;
  private final String[] iotDevices;
  private final String[] deviceModifiers;

  @JMockConstructor(descI18nKey = DOC_DEVICE_C1,
      example = "@Device()",
      exampleValues = {"Mainframe", "Server"})
  public MDevice() {
    this.computerNames = getString(DATA_COMPUTER_NAMES).split("\\|");
    this.mobileDevices = getString(DATA_MOBILE_DEVICES).split("\\|");
    this.iotDevices = getString(DATA_IOT_DEVICES).split("\\|");
    this.deviceModifiers = getString(DATA_DEVICE_MODIFIERS).split("\\|");
  }

  @Override
  public String mock() {
    int category = JMockRandom.nextInt(100);
    if (category < 60) {
      String name = computerNames[JMockRandom.nextInt(computerNames.length)];
      if (JMockRandom.nextDouble() < 0.4) {
        name += " " + deviceModifiers[JMockRandom.nextInt(deviceModifiers.length)];
      }
      return name;
    } else if (category < 85) {
      String name = mobileDevices[JMockRandom.nextInt(mobileDevices.length)];
      if (JMockRandom.nextDouble() < 0.6) {
        name += " " + (JMockRandom.nextInt(15) + 1);
      }
      return name;
    } else {
      return iotDevices[JMockRandom.nextInt(iotDevices.length)];
    }
  }
}
