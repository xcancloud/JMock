package cloud.xcan.jmock.plugin.compute;

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
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class ComputerDataGeneratorTest {

  @RepeatedTest(20)
  void testGenerateRandomCloudServiceName() {
    String service = new MCloudService().mock();
    assertNotNull(service);
    assertTrue(service.length() >= 3, "Cloud service name too short: " + service);
  }

  @Test
  void testCloudServiceCoverage() {
    Set<String> services = new HashSet<>();
    MCloudService gen = new MCloudService();
    for (int i = 0; i < 200; i++) {
      services.add(gen.mock());
    }
    assertTrue(services.size() >= 10, "Should cover many cloud services, got: " + services.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomOSName() {
    String os = new MOs().mock();
    assertNotNull(os);
    assertTrue(os.length() >= 3, "OS name too short: " + os);
  }

  @Test
  void testOSCoverage() {
    Set<String> osNames = new HashSet<>();
    MOs gen = new MOs();
    for (int i = 0; i < 200; i++) {
      osNames.add(gen.mock());
    }
    assertTrue(osNames.size() >= 5, "Should cover multiple OS names, got: " + osNames.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomCpuModel() {
    String cpu = new MCpuModel().mock();
    assertNotNull(cpu);
    assertTrue(cpu.length() >= 5, "CPU model too short: " + cpu);
    assertTrue(cpu.startsWith("Intel ") || cpu.startsWith("AMD ") || cpu.contains("Apple")
            || cpu.startsWith("M"),
        "CPU should have known manufacturer prefix: " + cpu);
  }

  @Test
  void testCpuManufacturerDistribution() {
    int intelCount = 0, amdCount = 0, otherCount = 0;
    MCpuModel gen = new MCpuModel();
    for (int i = 0; i < 500; i++) {
      String cpu = gen.mock();
      if (cpu.startsWith("Intel")) {
        intelCount++;
      } else if (cpu.startsWith("AMD")) {
        amdCount++;
      } else {
        otherCount++;
      }
    }
    assertTrue(intelCount > 200, "Intel should be most common, got: " + intelCount);
    assertTrue(amdCount > 50, "AMD should appear frequently, got: " + amdCount);
  }

  @RepeatedTest(20)
  void testGenerateRandomGpuModel() {
    String gpu = new MGpuModel().mock();
    assertNotNull(gpu);
    assertTrue(gpu.length() >= 5, "GPU model too short: " + gpu);
    assertTrue(gpu.startsWith("NVIDIA ") || gpu.startsWith("AMD ") || gpu.startsWith("Intel "),
        "GPU should have known manufacturer prefix: " + gpu);
  }

  @Test
  void testGpuManufacturerDistribution() {
    int nvidiaCount = 0, amdCount = 0, intelCount = 0;
    MGpuModel gen = new MGpuModel();
    for (int i = 0; i < 500; i++) {
      String gpu = gen.mock();
      if (gpu.startsWith("NVIDIA")) {
        nvidiaCount++;
      } else if (gpu.startsWith("AMD")) {
        amdCount++;
      } else if (gpu.startsWith("Intel")) {
        intelCount++;
      }
    }
    assertTrue(nvidiaCount > 250, "NVIDIA should be most common, got: " + nvidiaCount);
    assertTrue(amdCount > 20, "AMD should appear, got: " + amdCount);
  }

  @RepeatedTest(20)
  void testGenerateRandomRamConfig() {
    String ram = new MRam().mock();
    assertNotNull(ram);
    // Format: "XGB TYPE SPEEDMHz"
    assertTrue(ram.matches("\\d+GB \\S+ \\d+MHz"), "RAM format invalid: " + ram);
  }

  @Test
  void testRamCoverage() {
    Set<String> configs = new HashSet<>();
    MRam gen = new MRam();
    for (int i = 0; i < 100; i++) {
      configs.add(gen.mock());
    }
    assertTrue(configs.size() >= 5, "Should generate varied RAM configs, got: " + configs.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomBrowserName() {
    String browser = new MBrowser().mock();
    assertNotNull(browser);
    assertTrue(browser.length() >= 3, "Browser name too short: " + browser);
  }

  @Test
  void testBrowserCoverage() {
    Set<String> browsers = new HashSet<>();
    MBrowser gen = new MBrowser();
    for (int i = 0; i < 100; i++) {
      String b = gen.mock();
      // Extract base browser name (before version number)
      browsers.add(b.split(" ")[0]);
    }
    assertTrue(browsers.size() >= 3, "Should cover multiple browsers, got: " + browsers.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomHttpStatusCode() {
    String status = new MHttpStatus().mock();
    assertNotNull(status);
    // Format: "XXX Description"
    assertTrue(status.matches("\\d{3} .+"), "HTTP status format invalid: " + status);
    int code = Integer.parseInt(status.substring(0, 3));
    assertTrue(code >= 100 && code < 600, "HTTP status code out of range: " + code);
  }

  @Test
  void testHttpStatusCoverage() {
    Set<Integer> codes = new HashSet<>();
    MHttpStatus gen = new MHttpStatus();
    for (int i = 0; i < 200; i++) {
      String status = gen.mock();
      codes.add(Integer.parseInt(status.substring(0, 3)));
    }
    assertTrue(codes.size() >= 5, "Should cover multiple HTTP status codes, got: " + codes.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomFrameworkName() {
    String framework = new MFramework().mock();
    assertNotNull(framework);
    assertTrue(framework.length() >= 2, "Framework name too short: " + framework);
  }

  @Test
  void testFrameworkCoverage() {
    Set<String> frameworks = new HashSet<>();
    MFramework gen = new MFramework();
    for (int i = 0; i < 100; i++) {
      frameworks.add(gen.mock());
    }
    assertTrue(frameworks.size() >= 5,
        "Should cover multiple frameworks, got: " + frameworks.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomFileName() {
    String filename = new MFileName().mock();
    assertNotNull(filename);
    assertTrue(filename.contains("."), "Filename should have extension: " + filename);
    String[] parts = filename.split("\\.");
    assertTrue(parts.length >= 2, "Filename should have name and extension: " + filename);
    assertTrue(parts[0].length() >= 2, "Filename prefix too short: " + filename);
    assertTrue(parts[parts.length - 1].length() >= 1, "Extension too short: " + filename);
  }

  @RepeatedTest(20)
  void testGenerateRandomFilePath() {
    String path = new MFilePath().mock();
    assertNotNull(path);
    assertTrue(path.length() >= 5, "File path too short: " + path);
    assertTrue(path.contains("."), "File path should contain a filename with extension: " + path);

    // Should be either Windows or Unix path
    boolean isWindows = path.matches("[A-Z]:\\\\.*");
    boolean isUnix = path.startsWith("/");
    assertTrue(isWindows || isUnix, "Path should be Windows or Unix format: " + path);
  }

  @RepeatedTest(20)
  void testGenerateRandomDeviceName() {
    String device = new MDevice().mock();
    assertNotNull(device);
    assertTrue(device.length() >= 2, "Device name too short: " + device);
  }

  @Test
  void testDeviceCoverage() {
    Set<String> devices = new HashSet<>();
    MDevice gen = new MDevice();
    for (int i = 0; i < 200; i++) {
      devices.add(gen.mock());
    }
    assertTrue(devices.size() >= 5, "Should cover multiple device types, got: " + devices.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomDatabaseName() {
    String db = new MDatabase().mock();
    assertNotNull(db);
    assertTrue(db.length() >= 2, "Database name too short: " + db);
  }

  @Test
  void testDatabaseCoverage() {
    Set<String> databases = new HashSet<>();
    MDatabase gen = new MDatabase();
    for (int i = 0; i < 100; i++) {
      databases.add(gen.mock());
    }
    assertTrue(databases.size() >= 5, "Should cover multiple databases, got: " + databases.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomVulnerabilityType() {
    String vuln = new MVulnerability().mock();
    assertNotNull(vuln);
    assertTrue(vuln.length() >= 3, "Vulnerability type too short: " + vuln);
  }

  @Test
  void testVulnerabilityCoverage() {
    Set<String> vulns = new HashSet<>();
    MVulnerability gen = new MVulnerability();
    for (int i = 0; i < 100; i++) {
      vulns.add(gen.mock());
    }
    assertTrue(vulns.size() >= 5,
        "Should cover multiple vulnerability types, got: " + vulns.size());
  }
}
