package cloud.xcan.jmock.api.support.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Locale;
import org.junit.jupiter.api.Test;

class StringToTypeUtilsTest {

  @Test
  void isInt() {
    assertTrue(StringToTypeUtils.isInt("0"));
    assertTrue(StringToTypeUtils.isInt("-42"));
    assertFalse(StringToTypeUtils.isInt("1.2"));
    assertFalse(StringToTypeUtils.isInt(null));
    assertFalse(StringToTypeUtils.isInt(""));
  }

  @Test
  void isLong() {
    assertTrue(StringToTypeUtils.isLong("99L"));
    assertTrue(StringToTypeUtils.isLong("0l"));
    assertFalse(StringToTypeUtils.isLong("99"));
    assertFalse(StringToTypeUtils.isLong("xL"));
  }

  @Test
  void isFloat() {
    assertTrue(StringToTypeUtils.isFloat("1.5f"));
    assertTrue(StringToTypeUtils.isFloat("2F"));
    assertFalse(StringToTypeUtils.isFloat("1.5"));
  }

  @Test
  void isDouble() {
    assertTrue(StringToTypeUtils.isDouble("3.14d"));
    assertTrue(StringToTypeUtils.isDouble("1D"));
    assertFalse(StringToTypeUtils.isDouble("3.14"));
  }

  @Test
  void isNumeric() {
    assertTrue(StringToTypeUtils.isNumeric("10"));
    assertTrue(StringToTypeUtils.isNumeric("1.5"));
    assertTrue(StringToTypeUtils.isNumeric("2f"));
    assertFalse(StringToTypeUtils.isNumeric("abc"));
  }

  @Test
  void isNumericOrDouble() {
    assertTrue(StringToTypeUtils.isNumericOrDouble("12"));
    assertTrue(StringToTypeUtils.isNumericOrDouble("1a"));
  }

  @Test
  void isBool() {
    assertTrue(StringToTypeUtils.isBool("true"));
    assertTrue(StringToTypeUtils.isBool("FALSE"));
    assertFalse(StringToTypeUtils.isBool("yes"));
  }

  @Test
  void isWeight() {
    assertTrue(StringToTypeUtils.isWeight("1:2"));
    assertFalse(StringToTypeUtils.isWeight("1"));
    assertFalse(StringToTypeUtils.isWeight("a:b"));
    assertFalse(StringToTypeUtils.isWeight(null));
  }

  @Test
  void isLocale() {
    assertTrue(StringToTypeUtils.isLocale("en"));
    assertTrue(StringToTypeUtils.isLocale("ZH_cn"));
    assertFalse(StringToTypeUtils.isLocale("fr"));
  }

  @Test
  void isNullWeight() {
    assertTrue(StringToTypeUtils.isNullWeight("0.5"));
    assertTrue(StringToTypeUtils.isNullWeight("1:9"));
    assertFalse(StringToTypeUtils.isNullWeight("x"));
    assertFalse(StringToTypeUtils.isNullWeight(null));
  }

  @Test
  void calcNullWeight() {
    assertEquals(0.25, StringToTypeUtils.calcNullWeight("1:3"), 1e-9);
    assertEquals(0.5, StringToTypeUtils.calcNullWeight("0.5"), 1e-9);
    assertEquals(0.0, StringToTypeUtils.calcNullWeight("bad"), 1e-9);
  }

  @Test
  void checkAndGet() {
    assertNull(StringToTypeUtils.checkAndGet(null));
    assertNull(StringToTypeUtils.checkAndGet(""));
    assertNull(StringToTypeUtils.checkAndGet("null"));
    assertEquals(42, StringToTypeUtils.checkAndGet("42"));
    assertEquals(2.5f, StringToTypeUtils.checkAndGet("2.5f"));
    assertEquals(10L, StringToTypeUtils.checkAndGet("10L"));
    assertEquals(Boolean.TRUE, StringToTypeUtils.checkAndGet("true"));
    assertEquals(Locale.ENGLISH, StringToTypeUtils.checkAndGet("en"));
    assertEquals(Locale.CHINA, StringToTypeUtils.checkAndGet("zh_CN"));
    assertEquals("plain", StringToTypeUtils.checkAndGet("plain"));
  }
}
