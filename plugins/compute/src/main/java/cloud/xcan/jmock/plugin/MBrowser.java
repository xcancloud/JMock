package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_BROWSERS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_BROWSER_VERSIONS;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_BROWSER_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_BROWSER_DESC;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_BROWSER_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5001)
public class MBrowser extends AbstractMockFunction {

  private final String[] browsers;
  private final String[] browserVersions;

  @JMockConstructor(descI18nKey = DOC_BROWSER_C1,
      example = "@Browser()",
      exampleValues = {"Firefox 119", "Opera 119"})
  public MBrowser() {
    this.browsers = getString(DATA_BROWSERS).split("\\|");
    this.browserVersions = getString(DATA_BROWSER_VERSIONS).split("\\|");
  }

  @Override
  public String mock() {
    String browser = browsers[JMockRandom.nextInt(browsers.length)];
    String version = browserVersions[JMockRandom.nextInt(browserVersions.length)];

    // 80% chance to include version number
    if (JMockRandom.nextDouble() < 0.8) {
      return browser + " " + version;
    } else {
      return browser;
    }
  }
}
