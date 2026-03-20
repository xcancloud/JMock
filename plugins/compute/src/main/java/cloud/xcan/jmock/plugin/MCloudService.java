package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_AWS_SERVICES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_AZURE_SERVICES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_GCP_SERVICES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DATA_OTHER_CLOUD_SERVICES;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CLOUD_SERVICE_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CLOUD_SERVICE_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;

@JMockFunctionRegister(descI18nKey = DOC_CLOUD_SERVICE_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5002)
public class MCloudService extends AbstractMockFunction {

  private final String[] awsServices;
  private final String[] azureServices;
  private final String[] gcpServices;
  private final String[] otherCloudServices;

  @JMockConstructor(descI18nKey = DOC_CLOUD_SERVICE_C1,
      example = "@MCloudService()",
      exampleValues = {"Google Kubernetes Engine", "IBM Cloud Object Storage"})
  public MCloudService() {
    this.awsServices = getString(DATA_AWS_SERVICES).split("\\|");
    this.azureServices = getString(DATA_AZURE_SERVICES).split("\\|");
    this.gcpServices = getString(DATA_GCP_SERVICES).split("\\|");
    this.otherCloudServices = getString(DATA_OTHER_CLOUD_SERVICES).split("\\|");
  }

  @Override
  public String mock() {
    int provider = JMockRandom.nextInt(100);
    if (provider < 50) {
      return awsServices[JMockRandom.nextInt(awsServices.length)];
    } else if (provider < 80) {
      return azureServices[JMockRandom.nextInt(azureServices.length)];
    } else if (provider < 95) {
      return gcpServices[JMockRandom.nextInt(gcpServices.length)];
    } else {
      return otherCloudServices[JMockRandom.nextInt(otherCloudServices.length)];
    }
  }
}
