package cloud.xcan.jmock.plugin.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MAge;
import cloud.xcan.jmock.plugin.MEducation;
import cloud.xcan.jmock.plugin.MEmail;
import cloud.xcan.jmock.plugin.MFirstname;
import cloud.xcan.jmock.plugin.MGender;
import cloud.xcan.jmock.plugin.MLandline;
import cloud.xcan.jmock.plugin.MLastname;
import cloud.xcan.jmock.plugin.MMobile;
import cloud.xcan.jmock.plugin.MName;
import cloud.xcan.jmock.plugin.MPassword;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class UserDataGeneratorTest {

  @RepeatedTest(20)
  void testMAge_default() {
    Integer age = new MAge().mock();
    assertNotNull(age);
    assertTrue(age >= 0 && age <= 150, "Age out of range: " + age);
  }

  @Test
  void testMAge_customRange() {
    MAge gen = new MAge(18, 65);
    for (int i = 0; i < 100; i++) {
      Integer age = gen.mock();
      assertTrue(age >= 18 && age <= 65, "Age out of custom range: " + age);
    }
  }

  @RepeatedTest(20)
  void testMName_default() {
    String name = new MName().mock();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }

  @Test
  void testMName_variety() {
    Set<String> names = new HashSet<>();
    MName gen = new MName();
    for (int i = 0; i < 50; i++) {
      names.add(gen.mock());
    }
    assertTrue(names.size() >= 10, "Should generate varied names, got: " + names.size());
  }

  @RepeatedTest(20)
  void testMFirstname_default() {
    String name = new MFirstname().mock();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }

  @RepeatedTest(20)
  void testMLastname_default() {
    String name = new MLastname().mock();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }

  @RepeatedTest(20)
  void testMGender_default() {
    String gender = new MGender().mock();
    assertNotNull(gender);
    assertFalse(gender.isEmpty());
  }

  @Test
  void testMGender_coverage() {
    Set<String> genders = new HashSet<>();
    MGender gen = new MGender();
    for (int i = 0; i < 50; i++) {
      genders.add(gen.mock());
    }
    assertTrue(genders.size() >= 2, "Should cover at least 2 genders, got: " + genders.size());
  }

  @RepeatedTest(20)
  void testMEmail_default() {
    String email = new MEmail().mock();
    assertNotNull(email);
    assertTrue(email.contains("@"), "Email should contain @: " + email);
    assertTrue(email.contains("."), "Email should contain domain: " + email);
  }

  @RepeatedTest(10)
  void testMEmail_customSuffix() {
    String email = new MEmail("@test.com").mock();
    assertNotNull(email);
    assertTrue(email.endsWith("@test.com"), "Email should end with custom suffix: " + email);
  }

  @RepeatedTest(20)
  void testMMobile_default() {
    String mobile = new MMobile().mock();
    assertNotNull(mobile);
    assertTrue(mobile.length() >= 7, "Mobile too short: " + mobile);
  }

  @RepeatedTest(20)
  void testMLandline_default() {
    String landline = new MLandline().mock();
    assertNotNull(landline);
    assertTrue(landline.length() >= 7, "Landline too short: " + landline);
  }

  @RepeatedTest(20)
  void testMEducation_default() {
    String edu = new MEducation().mock();
    assertNotNull(edu);
    assertFalse(edu.isEmpty());
  }

  @Test
  void testMEducation_variety() {
    Set<String> edus = new HashSet<>();
    MEducation gen = new MEducation();
    for (int i = 0; i < 50; i++) {
      edus.add(gen.mock());
    }
    assertTrue(edus.size() >= 3, "Should cover multiple education levels, got: " + edus.size());
  }

  @RepeatedTest(20)
  void testMPassword_default() {
    String pwd = new MPassword().mock();
    assertNotNull(pwd);
    assertTrue(pwd.length() >= 6, "Password too short: " + pwd);
  }

  @Test
  void testMPassword_customRange() {
    MPassword gen = new MPassword(12, 20);
    for (int i = 0; i < 100; i++) {
      String pwd = gen.mock();
      assertTrue(pwd.length() >= 12 && pwd.length() <= 20,
          "Password length out of range: " + pwd.length());
    }
  }

  @Test
  void testMPassword_uniqueness() {
    Set<String> passwords = new HashSet<>();
    MPassword gen = new MPassword();
    for (int i = 0; i < 50; i++) {
      passwords.add(gen.mock());
    }
    assertTrue(passwords.size() >= 40, "Passwords should be unique, got: " + passwords.size());
  }
}
