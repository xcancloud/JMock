package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_DESKTOP_OS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_MOBILE_OS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_SERVER_OS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_OS_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_OS_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_OS_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5003)
public class MOs extends AbstractMockFunction {

  private final String[] desktopOs;
  private final String[] serverOs;
  private final String[] mobileOs;

  @JMockConstructor(descI18nKey = DOC_OS_C1,
      example = "@Os()",
      exampleValues = {"iOS 16", "Windows Server 2022"})
  public MOs() {
    this.desktopOs = getString(DATA_DESKTOP_OS).split("\\|");
    this.serverOs = getString(DATA_SERVER_OS).split("\\|");
    this.mobileOs = getString(DATA_MOBILE_OS).split("\\|");
  }

  @Override
  public String mock() {
    int category = JMockRandom.nextInt(100);
    if (category < 60) {
      return desktopOs[JMockRandom.nextInt(desktopOs.length)];
    } else if (category < 85) {
      return mobileOs[JMockRandom.nextInt(mobileOs.length)];
    } else {
      return serverOs[JMockRandom.nextInt(serverOs.length)];
    }
  }
}
