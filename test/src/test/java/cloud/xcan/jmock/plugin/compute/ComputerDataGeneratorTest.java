package cloud.xcan.jmock.plugin.compute;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MBrowser;
import cloud.xcan.jmock.plugin.MCloudService;
import cloud.xcan.jmock.plugin.MCpuModel;
import cloud.xcan.jmock.plugin.MDatabase;
import cloud.xcan.jmock.plugin.MDevice;
import cloud.xcan.jmock.plugin.MFileName;
import cloud.xcan.jmock.plugin.MFilePath;
import cloud.xcan.jmock.plugin.MFramework;
import cloud.xcan.jmock.plugin.MGpuModel;
import cloud.xcan.jmock.plugin.MHttpStatus;
import cloud.xcan.jmock.plugin.MOs;
import cloud.xcan.jmock.plugin.MRam;
import cloud.xcan.jmock.plugin.MVulnerability;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class ComputerDataGeneratorTest {

  @RepeatedTest(20)
  void testGenerateRandomCloudServiceName() {
    String service = new MCloudService().mock();
    assertNotNull(service);
    assertTrue(service.length() > 5);

    // Verify it's from one of the providers
    boolean valid = MCloudService.AWS_SERVICES.contains(service) ||
        MCloudService.AZURE_SERVICES.contains(service) ||
        MCloudService.GCP_SERVICES.contains(service) ||
        MCloudService.OTHER_CLOUD_SERVICES.contains(service);
    assertTrue(valid, "Cloud service not in predefined list: " + service);
  }

  @RepeatedTest(20)
  void testGenerateRandomOSName() {
    String os = new MOs().mock();
    assertNotNull(os);
    assertTrue(os.length() > 5);

    // Verify it's from one of the categories
    boolean valid = MOs.DESKTOP_OS.contains(os) || MOs.SERVER_OS.contains(os)
        || MOs.MOBILE_OS.contains(os);
    assertTrue(valid, "OS not in predefined list: " + os);
  }

  @Test
  void testOSCategoryDistribution() {
    int desktopCount = 0, serverCount = 0, mobileCount = 0;
    for (int i = 0; i < 1000; i++) {
      String os = new MOs().mock();
      if (MOs.DESKTOP_OS.contains(os)) {
        desktopCount++;
      } else if (MOs.SERVER_OS.contains(os)) {
        serverCount++;
      } else if (MOs.MOBILE_OS.contains(os)) {
        mobileCount++;
      }
    }

    // Check distribution roughly matches expected probabilities
    assertTrue(desktopCount > 500 && desktopCount < 700, "Desktop OS distribution");
    assertTrue(mobileCount > 200 && mobileCount < 300, "Mobile OS distribution");
    assertTrue(serverCount > 100 && serverCount < 200, "Server OS distribution");
  }

  @RepeatedTest(20)
  void testGenerateRandomCpuModel() {
    String cpu = new MCpuModel().mock();
    assertNotNull(cpu);
    assertTrue(cpu.length() > 5);

    // Verify manufacturer prefix
    assertTrue(cpu.startsWith("Intel ") || cpu.startsWith("AMD ") ||
        cpu.contains("Apple M"));
  }

  @Test
  void testCpuManufacturerDistribution() {
    int intelCount = 0, amdCount = 0, appleCount = 0;
    for (int i = 0; i < 1000; i++) {
      String cpu = new MCpuModel().mock();
      if (cpu.startsWith("Intel")) {
        intelCount++;
      } else if (cpu.startsWith("AMD")) {
        amdCount++;
      } else if (cpu.contains("Apple")) {
        appleCount++;
      }
    }

    // Check distribution roughly matches expected probabilities
    assertTrue(intelCount > 650 && intelCount < 750, "Intel CPU distribution");
    assertTrue(amdCount > 200 && amdCount < 300, "AMD CPU distribution");
    assertTrue(appleCount > 30 && appleCount < 70, "Apple CPU distribution");
  }

  @RepeatedTest(20)
  void testGenerateRandomGpuModel() {
    String gpu = new MGpuModel().mock();
    assertNotNull(gpu);
    assertTrue(gpu.length() > 5);

    // Verify manufacturer prefix
    assertTrue(gpu.startsWith("NVIDIA ") || gpu.startsWith("AMD ") ||
        gpu.startsWith("Intel "));
  }

  @Test
  void testGpuManufacturerDistribution() {
    int nvidiaCount = 0, amdCount = 0, intelCount = 0;
    for (int i = 0; i < 1000; i++) {
      String gpu = new MGpuModel().mock();
      if (gpu.startsWith("NVIDIA")) {
        nvidiaCount++;
      } else if (gpu.startsWith("AMD")) {
        amdCount++;
      } else if (gpu.startsWith("Intel")) {
        intelCount++;
      }
    }

    // Check distribution roughly matches expected probabilities
    assertTrue(nvidiaCount > 700 && nvidiaCount < 800, "NVIDIA GPU distribution");
    assertTrue(amdCount > 150 && amdCount < 250, "AMD GPU distribution");
    assertTrue(intelCount > 30 && intelCount < 70, "Intel GPU distribution");
  }

  @RepeatedTest(20)
  void testGenerateRandomRamConfig() {
    String ram = new MRam().mock();
    assertNotNull(ram);

    // Verify format: "XGB TYPE SPEEDMHz"
    assertTrue(ram.matches("\\d+GB (DDR4|DDR5|LPDDR4X|LPDDR5) \\d+MHz"));

    // Extract components
    String[] parts = ram.split(" ");
    int size = Integer.parseInt(parts[0].replace("GB", ""));
    String type = parts[1];
    int speed = Integer.parseInt(parts[2].replace("MHz", ""));

    // Verify valid values
    assertTrue(MRam.RAM_SIZES.contains(size), "Invalid RAM size");
    assertTrue(MRam.RAM_TYPES.contains(type), "Invalid RAM type");
    assertTrue(MRam.RAM_SPEEDS.contains(speed), "Invalid RAM speed");
  }

  @RepeatedTest(20)
  void testGenerateRandomBrowserName() {
    String browser = new MBrowser().mock();
    assertNotNull(browser);
    assertTrue(browser.length() > 3);

    // Verify it contains a known browser name
    boolean valid = false;
    for (String b : MBrowser.BROWSERS) {
      if (browser.contains(b)) {
        valid = true;
        break;
      }
    }
    assertTrue(valid, "Browser not in predefined list: " + browser);

    // If version is included, verify format
    if (browser.contains(" ")) {
      String versionPart = browser.split(" ")[1];
      assertTrue(versionPart.matches("\\d+"), "Invalid version format");
    }
  }

  @RepeatedTest(20)
  void testGenerateRandomHttpStatusCode() {
    String status = new MHttpStatus().mock();
    assertNotNull(status);

    // Verify format: "XXX Description"
    assertTrue(status.matches("\\d{3} [A-Za-z -]+"));

    // Extract code and description
    int code = Integer.parseInt(status.substring(0, 3));
    String description = status.substring(4);

    // Verify valid status code
    assertTrue(code >= 200 && code < 600, "Invalid HTTP status code");
    assertTrue(MHttpStatus.HTTP_STATUS_CODES.containsKey(code),
        "Unknown status code: " + code);
    assertEquals(MHttpStatus.HTTP_STATUS_CODES.get(code), description);
  }

  @RepeatedTest(20)
  void testGenerateRandomFrameworkName() {
    String framework = new MFramework().mock();
    assertNotNull(framework);
    assertTrue(framework.length() > 3);

    // Verify it's from one of the categories
    boolean valid = MFramework.FRONTEND_FRAMEWORKS.contains(framework) ||
        MFramework.BACKEND_FRAMEWORKS.contains(framework) ||
        MFramework.MOBILE_FRAMEWORKS.contains(framework);
    assertTrue(valid, "Framework not in predefined list: " + framework);
  }

  @RepeatedTest(20)
  void testGenerateRandomFileName() {
    String filename = new MFileName().mock();
    assertNotNull(filename);
    assertTrue(filename.length() > 5);

    // Verify format: name.extension
    assertTrue(filename.contains("."));
    String[] parts = filename.split("\\.");
    assertEquals(2, parts.length);
    assertTrue(parts[0].length() >= 3);
    assertTrue(MFileName.FILE_EXTENSIONS.contains(parts[1]));

    // Verify prefix
    boolean validPrefix = false;
    for (String prefix : MFileName.FILE_PREFIXES) {
      if (parts[0].startsWith(prefix)) {
        validPrefix = true;
        break;
      }
    }
    assertTrue(validPrefix, "Invalid file prefix: " + parts[0]);
  }

  @RepeatedTest(20)
  void testGenerateRandomFilePath() {
    String path = new MFilePath().mock();
    assertNotNull(path);
    assertTrue(path.length() > 10);

    // Verify it ends with a filename
    assertTrue(path.contains("."));

    // Verify OS-specific path format
    if (path.contains(":\\")) {
      // Windows path
      System.out.println(path);
    } else {
      // Unix path
      assertTrue(path.startsWith("/"));
      assertTrue(path.matches("/[^/]+(/[^/]+)*/[^/]+\\.\\w+"));
    }
  }

  @RepeatedTest(20)
  void testGenerateRandomDeviceName() {
    String device = new MDevice().mock();
    assertNotNull(device);
    assertTrue(device.length() > 3);

    // Verify it's from one of the categories
    boolean valid = false;
    for (String d : MDevice.COMPUTER_NAMES) {
      if (device.contains(d)) {
        valid = true;
        break;
      }
    }
    if (!valid) {
      for (String d : MDevice.MOBILE_DEVICES) {
        if (device.contains(d)) {
          valid = true;
          break;
        }
      }
    }
    if (!valid) {
      for (String d : MDevice.IOT_DEVICES) {
        if (device.contains(d)) {
          valid = true;
          break;
        }
      }
    }
    assertTrue(valid, "Device not in predefined categories: " + device);
  }

  @RepeatedTest(20)
  void testGenerateRandomDatabaseName() {
    String db = new MDatabase().mock();
    assertNotNull(db);
    assertTrue(db.length() > 3);

    // Verify it's from one of the categories
    boolean valid = MDatabase.SQL_DATABASES.contains(db) ||
        MDatabase.NOSQL_DATABASES.contains(db) ||
        MDatabase.NEWSQL_DATABASES.contains(db);
    assertTrue(valid, "Database not in predefined list: " + db);
  }

  @RepeatedTest(20)
  void testGenerateRandomVulnerabilityType() {
    String vuln = new MVulnerability().mock();
    assertNotNull(vuln);
    assertTrue(vuln.length() > 5);

    // Verify it's from the predefined list
    assertTrue(MVulnerability.VULNERABILITY_TYPES.contains(vuln),
        "Vulnerability not in list: " + vuln);
  }


}
