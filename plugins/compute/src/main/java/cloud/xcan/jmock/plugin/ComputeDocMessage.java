package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;
import cloud.xcan.jmock.api.i18n.RegisterDocMessage;

public class ComputeDocMessage implements RegisterDocMessage {

  /**
   * Coding order by 50xx.
   */
  public final static String DOC_CATEGORY_COMPUTE = "jmock.func.category.compute";

  public final static String DOC_BROWSER_DESC = "jmock.func.MBrowser.description";
  public final static String DOC_BROWSER_C1 = "jmock.func.MBrowser.C1";

  public final static String DOC_CLOUD_SERVICE_DESC = "jmock.func.MCloudService.description";
  public final static String DOC_CLOUD_SERVICE_C1 = "jmock.func.MCloudService.C1";

  public final static String DOC_OS_DESC = "jmock.func.MOs.description";
  public final static String DOC_OS_C1 = "jmock.func.MOs.C1";

  public final static String DOC_RAM_DESC = "jmock.func.MRam.description";
  public final static String DOC_RAM_C1 = "jmock.func.MRam.C1";

  public final static String DOC_CPU_MODEL_DESC = "jmock.func.MCpuModel.description";
  public final static String DOC_CPU_MODEL_C1 = "jmock.func.MCpuModel.C1";

  public final static String DOC_DATABASE_DESC = "jmock.func.MDatabase.description";
  public final static String DOC_DATABASE_C1 = "jmock.func.MDatabase.C1";

  public final static String DOC_DEVICE_DESC = "jmock.func.MDevice.description";
  public final static String DOC_DEVICE_C1 = "jmock.func.MDevice.C1";

  public final static String DOC_FILENAME_DESC = "jmock.func.MFileName.description";
  public final static String DOC_FILENAME_C1 = "jmock.func.MFileName.C1";

  public final static String DOC_FILE_PATH_DESC = "jmock.func.MFilePath.description";
  public final static String DOC_FILE_PATH_C1 = "jmock.func.MFilePath.C1";

  public final static String DOC_FRAMEWORK_DESC = "jmock.func.MFramework.description";
  public final static String DOC_FRAMEWORK_C1 = "jmock.func.MFramework.C1";

  public final static String DOC_GPU_MODEL_DESC = "jmock.func.MGpuModel.description";
  public final static String DOC_GPU_MODEL_C1 = "jmock.func.MGpuModel.C1";

  public final static String DOC_HTTP_STATUS_DESC = "jmock.func.MHttpStatus.description";
  public final static String DOC_HTTP_STATUS_C1 = "jmock.func.MHttpStatus.C1";

  public final static String DOC_VULNERABILITY_DESC = "jmock.func.MVulnerability.description";
  public final static String DOC_VULNERABILITY_C1 = "jmock.func.MVulnerability.C1";

  // Data keys
  public final static String DATA_BROWSERS = "jmock.data.browsers";
  public final static String DATA_BROWSER_VERSIONS = "jmock.data.browser.versions";
  public final static String DATA_AWS_SERVICES = "jmock.data.cloud.aws";
  public final static String DATA_AZURE_SERVICES = "jmock.data.cloud.azure";
  public final static String DATA_GCP_SERVICES = "jmock.data.cloud.gcp";
  public final static String DATA_OTHER_CLOUD_SERVICES = "jmock.data.cloud.other";
  public final static String DATA_DESKTOP_OS = "jmock.data.os.desktop";
  public final static String DATA_SERVER_OS = "jmock.data.os.server";
  public final static String DATA_MOBILE_OS = "jmock.data.os.mobile";
  public final static String DATA_RAM_SIZES = "jmock.data.ram.sizes";
  public final static String DATA_RAM_TYPES = "jmock.data.ram.types";
  public final static String DATA_RAM_SPEEDS = "jmock.data.ram.speeds";
  public final static String DATA_INTEL_CPUS = "jmock.data.cpu.intel";
  public final static String DATA_AMD_CPUS = "jmock.data.cpu.amd";
  public final static String DATA_APPLE_CPUS = "jmock.data.cpu.apple";
  public final static String DATA_SQL_DATABASES = "jmock.data.db.sql";
  public final static String DATA_NOSQL_DATABASES = "jmock.data.db.nosql";
  public final static String DATA_NEWSQL_DATABASES = "jmock.data.db.newsql";
  public final static String DATA_COMPUTER_NAMES = "jmock.data.device.computer";
  public final static String DATA_MOBILE_DEVICES = "jmock.data.device.mobile";
  public final static String DATA_IOT_DEVICES = "jmock.data.device.iot";
  public final static String DATA_DEVICE_MODIFIERS = "jmock.data.device.modifiers";
  public final static String DATA_FILE_PREFIXES = "jmock.data.file.prefixes";
  public final static String DATA_FILE_SUFFIXES = "jmock.data.file.suffixes";
  public final static String DATA_FILE_EXTENSIONS = "jmock.data.file.extensions";
  public final static String DATA_WINDOWS_PATHS = "jmock.data.path.windows";
  public final static String DATA_UNIX_PATHS = "jmock.data.path.unix";
  public final static String DATA_FRONTEND_FRAMEWORKS = "jmock.data.framework.frontend";
  public final static String DATA_BACKEND_FRAMEWORKS = "jmock.data.framework.backend";
  public final static String DATA_MOBILE_FRAMEWORKS = "jmock.data.framework.mobile";
  public final static String DATA_NVIDIA_GPUS = "jmock.data.gpu.nvidia";
  public final static String DATA_AMD_GPUS = "jmock.data.gpu.amd";
  public final static String DATA_INTEL_GPUS = "jmock.data.gpu.intel";
  public final static String DATA_HTTP_STATUS_CODES = "jmock.data.http.status";
  public final static String DATA_VULNERABILITY_TYPES = "jmock.data.vulnerabilities";

  @Override
  public void register() {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-compute-plugin-messages");
  }
}
