package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.DocMessage.DOC_BROWSER_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CLOUD_SERVICE_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CLOUD_SERVICE_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_CLOUD_SERVICE_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5002)
public class MCloudService extends AbstractMockFunction {

  public static final List<String> AWS_SERVICES = Arrays.asList(
      "Amazon S3", "Amazon EC2", "AWS Lambda", "Amazon RDS",
      "Amazon DynamoDB", "Amazon SQS", "Amazon SNS", "Amazon EKS"
  );

  public static final List<String> AZURE_SERVICES = Arrays.asList(
      "Azure Blob Storage", "Azure Virtual Machines", "Azure Functions",
      "Azure SQL Database", "Azure Cosmos DB", "Azure Kubernetes Service"
  );

  public static final List<String> GCP_SERVICES = Arrays.asList(
      "Google Cloud Storage", "Compute Engine", "Cloud Functions",
      "Cloud SQL", "Firestore", "Google Kubernetes Engine"
  );

  public static final List<String> OTHER_CLOUD_SERVICES = Arrays.asList(
      "IBM Cloud Object Storage", "Oracle Cloud Infrastructure",
      "Alibaba Cloud ECS", "DigitalOcean Droplets", "Cloudflare Workers"
  );

  @JMockConstructor(descI18nKey = DOC_CLOUD_SERVICE_C1,
      example = "@MCloudService()",
      exampleValues = {"Google Kubernetes Engine", "IBM Cloud Object Storage"})
  public MCloudService() {
  }

  @Override
  public String mock() {
    int provider = random.nextInt(100);
    if (provider < 50) {
      // AWS (50% probability)
      return AWS_SERVICES.get(random.nextInt(AWS_SERVICES.size()));
    } else if (provider < 80) {
      // Azure (30% probability)
      return AZURE_SERVICES.get(random.nextInt(AZURE_SERVICES.size()));
    } else if (provider < 95) {
      // GCP (15% probability)
      return GCP_SERVICES.get(random.nextInt(GCP_SERVICES.size()));
    } else {
      // Other providers (5% probability)
      return OTHER_CLOUD_SERVICES.get(random.nextInt(OTHER_CLOUD_SERVICES.size()));
    }
  }
}
