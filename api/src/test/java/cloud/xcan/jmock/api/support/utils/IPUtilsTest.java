package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class IPUtilsTest {

  @RepeatedTest(5)
  void ipV4() {
    String ip = IPUtils.ipV4();
    assertNotNull(ip);
    assertTrue(ip.matches("\\d{1,3}(\\.\\d{1,3}){3}"));
  }

  @RepeatedTest(3)
  void ipV6() {
    assertNotNull(IPUtils.ipV6());
  }

  @RepeatedTest(3)
  void mac() {
    assertNotNull(IPUtils.mac());
  }

  @Test
  void randomUrl_noQuery() {
    String url = IPUtils.randomUrl("https", "example.com", new String[]{"a", "b"}, false);
    assertTrue(url.startsWith("https://example.com/"));
  }

  @Test
  void randomUrl_withQuery() {
    String url = IPUtils.randomUrl("http", "x.test", new String[]{"p", "q"}, true);
    assertTrue(url.startsWith("http://x.test"));
  }

  @Test
  void randomUrl_maxLength() {
    String url = IPUtils.randomUrl(40, "https", "short.io", new String[]{"k"}, false);
    assertTrue(url.length() <= 40);
  }

  @Test
  void randomUrl_maxLength_withQuery() {
    String url = IPUtils.randomUrl(80, "https", "q.io", new String[]{"a", "b"}, true);
    assertTrue(url.length() <= 80);
  }
}
