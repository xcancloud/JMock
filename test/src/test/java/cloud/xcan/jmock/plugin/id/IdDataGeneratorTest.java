package cloud.xcan.jmock.plugin.id;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MGuid;
import cloud.xcan.jmock.plugin.MIncId;
import cloud.xcan.jmock.plugin.MSnowId;
import cloud.xcan.jmock.plugin.MUuid;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class IdDataGeneratorTest {

  @RepeatedTest(20)
  void testMUuid_default() {
    String uuid = new MUuid().mock();
    assertNotNull(uuid);
    assertTrue(uuid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"),
        "UUID format invalid: " + uuid);
  }

  @RepeatedTest(10)
  void testMUuid_withoutSeparator() {
    String uuid = new MUuid(true).mock();
    assertNotNull(uuid);
    assertEquals(32, uuid.length(), "UUID without separator should be 32 chars: " + uuid);
    assertTrue(uuid.matches("[0-9a-f]{32}"), "UUID should be hex chars: " + uuid);
  }

  @Test
  void testMUuid_uniqueness() {
    Set<String> uuids = new HashSet<>();
    MUuid gen = new MUuid();
    for (int i = 0; i < 100; i++) {
      uuids.add(gen.mock());
    }
    assertEquals(100, uuids.size(), "All UUIDs should be unique");
  }

  @RepeatedTest(20)
  void testMGuid_default() {
    String guid = new MGuid().mock();
    assertNotNull(guid);
    assertTrue(guid.matches("[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}"),
        "GUID format invalid: " + guid);
  }

  @RepeatedTest(10)
  void testMGuid_withoutSeparator() {
    String guid = new MGuid(true).mock();
    assertNotNull(guid);
    assertEquals(32, guid.length(), "GUID without separator should be 32 chars");
  }

  @Test
  void testMIncId_default() {
    MIncId gen = new MIncId();
    Long first = gen.mock();
    Long second = gen.mock();
    Long third = gen.mock();
    assertNotNull(first);
    assertNotNull(second);
    assertNotNull(third);
    assertTrue(second > first, "IncId should increment");
    assertTrue(third > second, "IncId should increment");
  }

  @Test
  void testMIncId_customStep() {
    MIncId gen = new MIncId(100, 5);
    Long first = gen.mock();
    Long second = gen.mock();
    assertEquals(100L, first, "Initial value should be 100");
    assertEquals(105L, second, "Step should be 5");
  }

  @RepeatedTest(20)
  void testMSnowId_default() {
    Long id = new MSnowId().mock();
    assertNotNull(id);
    assertTrue(id > 0, "SnowflakeId should be positive: " + id);
  }

  @Test
  void testMSnowId_uniqueness() {
    Set<Long> ids = new HashSet<>();
    MSnowId gen = new MSnowId();
    for (int i = 0; i < 100; i++) {
      ids.add(gen.mock());
    }
    assertEquals(100, ids.size(), "All snowflake IDs should be unique");
  }
}
