package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_HTTP_STATUS_CODES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_HTTP_STATUS_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_HTTP_STATUS_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_HTTP_STATUS_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5012)
public class MHttpStatus extends AbstractMockFunction {

  private final String[] statusEntries;

  @JMockConstructor(descI18nKey = DOC_HTTP_STATUS_C1,
      example = "@HttpStatus()",
      exampleValues = {"302 Found", "500 Internal Server Error"})
  public MHttpStatus() {
    this.statusEntries = getString(DATA_HTTP_STATUS_CODES).split("\\|");
  }

  @Override
  public String mock() {
    String entry = statusEntries[JMockRandom.nextInt(statusEntries.length)];
    int colonIdx = entry.indexOf(':');
    return entry.substring(0, colonIdx) + " " + entry.substring(colonIdx + 1);
  }
}
