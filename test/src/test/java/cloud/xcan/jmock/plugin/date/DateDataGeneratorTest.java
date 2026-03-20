package cloud.xcan.jmock.plugin.date;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MLocaleDate;
import cloud.xcan.jmock.plugin.MLocaleDateTime;
import cloud.xcan.jmock.plugin.MLocaleTime;
import cloud.xcan.jmock.plugin.MMonth;
import cloud.xcan.jmock.plugin.MTimestamp;
import cloud.xcan.jmock.plugin.MWeek;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class DateDataGeneratorTest {

  @RepeatedTest(20)
  void testMTimestamp_default() {
    Long ts = new MTimestamp().mock();
    assertNotNull(ts);
    assertTrue(ts > 0, "Timestamp should be positive: " + ts);
  }

  @RepeatedTest(10)
  void testMTimestamp_unix() {
    Long ts = new MTimestamp(true).mock();
    assertNotNull(ts);
    assertTrue(ts > 0, "Unix timestamp should be positive: " + ts);
    // Unix timestamps (seconds) are much smaller than millis
    assertTrue(ts < 10_000_000_000L, "Unix timestamp should be in seconds range: " + ts);
  }

  @RepeatedTest(20)
  void testMLocaleDate_default() {
    String date = new MLocaleDate().mock();
    assertNotNull(date);
    assertTrue(date.matches("\\d{4}-\\d{2}-\\d{2}"), "Default date format invalid: " + date);
  }

  @RepeatedTest(10)
  void testMLocaleDate_customFormat() {
    String date = new MLocaleDate("yyyy/MM/dd").mock();
    assertNotNull(date);
    assertTrue(date.matches("\\d{4}/\\d{2}/\\d{2}"), "Custom date format invalid: " + date);
  }

  @RepeatedTest(20)
  void testMLocaleTime_default() {
    String time = new MLocaleTime().mock();
    assertNotNull(time);
    assertTrue(time.matches("\\d{2}:\\d{2}:\\d{2}"), "Default time format invalid: " + time);
  }

  @RepeatedTest(10)
  void testMLocaleTime_customFormat() {
    String time = new MLocaleTime("HH:mm").mock();
    assertNotNull(time);
    assertTrue(time.matches("\\d{2}:\\d{2}"), "Custom time format invalid: " + time);
  }

  @RepeatedTest(20)
  void testMLocaleDateTime_default() {
    String dt = new MLocaleDateTime().mock();
    assertNotNull(dt);
    assertTrue(dt.contains("-") && dt.contains(":"),
        "DateTime should contain date and time parts: " + dt);
  }

  @RepeatedTest(10)
  void testMLocaleDateTime_random() {
    Set<String> values = new HashSet<>();
    MLocaleDateTime gen = new MLocaleDateTime("yyyy-MM-dd HH:mm:ss", true);
    for (int i = 0; i < 20; i++) {
      values.add(gen.mock());
    }
    assertTrue(values.size() >= 5, "Random datetimes should vary, got: " + values.size());
  }

  @RepeatedTest(20)
  void testMWeek_default() {
    String week = new MWeek().mock();
    assertNotNull(week);
    assertTrue(week.length() >= 1, "Week name too short: " + week);
  }

  @Test
  void testMWeek_coverage() {
    Set<String> weeks = new HashSet<>();
    MWeek gen = new MWeek();
    for (int i = 0; i < 100; i++) {
      weeks.add(gen.mock());
    }
    assertTrue(weeks.size() == 7, "Should cover all 7 weekdays, got: " + weeks.size());
  }

  @RepeatedTest(20)
  void testMMonth_default() {
    String month = new MMonth().mock();
    assertNotNull(month);
    assertTrue(month.length() >= 1, "Month name too short: " + month);
  }

  @Test
  void testMMonth_coverage() {
    Set<String> months = new HashSet<>();
    MMonth gen = new MMonth();
    for (int i = 0; i < 100; i++) {
      months.add(gen.mock());
    }
    assertTrue(months.size() == 12, "Should cover all 12 months, got: " + months.size());
  }
}
