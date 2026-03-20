package cloud.xcan.jmock.plugin.network;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MAppName;
import cloud.xcan.jmock.plugin.MAppVersion;
import cloud.xcan.jmock.plugin.MIPv4;
import cloud.xcan.jmock.plugin.MIPv6;
import cloud.xcan.jmock.plugin.MMac;
import cloud.xcan.jmock.plugin.MPort;
import cloud.xcan.jmock.plugin.MProtocol;
import cloud.xcan.jmock.plugin.MUrl;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class NetworkDataGeneratorTest {

  @RepeatedTest(20)
  void testMIPv4_default() {
    String ip = new MIPv4().mock();
    assertNotNull(ip);
    assertTrue(ip.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}"),
        "IPv4 format invalid: " + ip);
  }

  @RepeatedTest(20)
  void testMIPv6_default() {
    String ip = new MIPv6().mock();
    assertNotNull(ip);
    assertTrue(ip.contains(":"), "IPv6 should contain colons: " + ip);
  }

  @RepeatedTest(20)
  void testMMac_default() {
    String mac = new MMac().mock();
    assertNotNull(mac);
    assertTrue(mac.matches("([0-9A-Fa-f]{2}[:-]){5}[0-9A-Fa-f]{2}"),
        "MAC format invalid: " + mac);
  }

  @RepeatedTest(20)
  void testMPort_default() {
    Integer port = new MPort().mock();
    assertNotNull(port);
    assertTrue(port >= 0 && port <= 65535, "Port out of range: " + port);
  }

  @Test
  void testMPort_customRange() {
    MPort gen = new MPort(8000, 9000);
    for (int i = 0; i < 100; i++) {
      Integer port = gen.mock();
      assertTrue(port >= 8000 && port <= 9000, "Port out of custom range: " + port);
    }
  }

  @RepeatedTest(20)
  void testMProtocol_default() {
    String protocol = new MProtocol().mock();
    assertNotNull(protocol);
    assertFalse(protocol.isEmpty());
  }

  @Test
  void testMProtocol_variety() {
    Set<String> protocols = new HashSet<>();
    MProtocol gen = new MProtocol();
    for (int i = 0; i < 50; i++) {
      protocols.add(gen.mock());
    }
    assertTrue(protocols.size() >= 3, "Should cover multiple protocols, got: " + protocols.size());
  }

  @RepeatedTest(20)
  void testMUrl_default() {
    String url = new MUrl().mock();
    assertNotNull(url);
    assertTrue(url.startsWith("http://") || url.startsWith("https://"),
        "URL should start with http(s)://: " + url);
  }

  @Test
  void testMUrl_variety() {
    Set<String> urls = new HashSet<>();
    MUrl gen = new MUrl();
    for (int i = 0; i < 50; i++) {
      urls.add(gen.mock());
    }
    assertTrue(urls.size() >= 10, "Should generate varied URLs, got: " + urls.size());
  }

  @RepeatedTest(20)
  void testMAppName_default() {
    String name = new MAppName().mock();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }

  @RepeatedTest(20)
  void testMAppVersion_default() {
    String version = new MAppVersion().mock();
    assertNotNull(version);
    assertTrue(version.matches(".*\\d+\\.\\d+.*"), "Version should contain digits: " + version);
  }
}
