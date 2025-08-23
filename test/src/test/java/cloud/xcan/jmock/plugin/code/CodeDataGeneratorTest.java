package cloud.xcan.jmock.plugin.code;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MErrorType;
import cloud.xcan.jmock.plugin.MProgrammingLanguage;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CodeDataGeneratorTest {

  @RepeatedTest(20)
  void testGenerateRandomProgrammingLanguage() {
    String language = new MProgrammingLanguage().mock();
    assertNotNull(language);
    assertTrue(language.length() >= 2);
    assertTrue(MProgrammingLanguage.PROGRAMMING_LANGUAGES.contains(language));
  }

  @Test
  void testLanguageCoverage() {
    Set<String> languages = new HashSet<>();
    for (int i = 0; i < 50; i++) {
      languages.add(new MProgrammingLanguage().mock());
    }
    assertTrue(languages.size() >= 10, "Should cover multiple languages");
  }

  @RepeatedTest(20)
  void testGenerateRandomErrorType() {
    String error = new MErrorType().mock();
    assertNotNull(error);
    assertTrue(error.endsWith("Error") || error.endsWith("Exception"));
    assertTrue(MErrorType.ERROR_TYPES.contains(error));
  }

  @Test
  void testErrorTypeCoverage() {
    Set<String> errors = new HashSet<>();
    for (int i = 0; i < 50; i++) {
      errors.add(new MErrorType().mock());
    }
    assertTrue(errors.size() >= 8, "Should cover multiple error types");
  }

}
