package cloud.xcan.jmock.plugin.company;

import static cloud.xcan.jmock.plugin.MCompany.generateRandomCompanyName;
import static cloud.xcan.jmock.plugin.MDepartment.generateRandomDepartmentName;
import static cloud.xcan.jmock.plugin.MIndustry.generateRandomIndustryCategory;
import static cloud.xcan.jmock.plugin.MJob.generateRandomJobTitle;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CompanyDataGeneratorTest {

  @RepeatedTest(20)
  void testGenerateRandomCompanyName_CN() {
    String name = generateRandomCompanyName(Locale.CHINA);
    assertNotNull(name);
  }

  @RepeatedTest(20)
  void testGenerateRandomCompanyName_EN() {
    String name = generateRandomCompanyName(Locale.ENGLISH);
    assertNotNull(name);
  }

  @Test
  void testCompanyNameUniqueness() {
    Set<String> names = new HashSet<>();
    for (int i = 0; i < 100; i++) {
      names.add(generateRandomCompanyName(Locale.CHINA));
    }
    assertTrue(names.size() >= 80, "Should generate at least 80 distinct Chinese company names");
  }

  @RepeatedTest(20)
  void testGenerateRandomIndustryCategory_CN() {
    String category = generateRandomIndustryCategory(Locale.CHINA);
    assertNotNull(category);
    assertTrue(category.length() >= 2 && category.length() <= 6);
  }

  @RepeatedTest(20)
  void testGenerateRandomIndustryCategory_EN() {
    String category = generateRandomIndustryCategory(Locale.ENGLISH);
    assertNotNull(category);
    assertTrue(category.length() >= 5 && category.length() <= 25);
  }

  @Test
  void testIndustryCategoryCoverage() {
    Set<String> cnCategories = new HashSet<>();
    Set<String> enCategories = new HashSet<>();

    for (int i = 0; i < 50; i++) {
      cnCategories.add(generateRandomIndustryCategory(Locale.CHINA));
      enCategories.add(generateRandomIndustryCategory(Locale.ENGLISH));
    }

    assertTrue(cnCategories.size() >= 10, "Should cover multiple Chinese industry categories");
    assertTrue(enCategories.size() >= 10, "Should cover multiple English industry categories");
  }

  @RepeatedTest(20)
  void testGenerateRandomDepartmentName_CN() {
    String dept = generateRandomDepartmentName(Locale.CHINA);
    assertNotNull(dept);
    assertTrue(dept.endsWith("部"));
  }

  @RepeatedTest(20)
  void testGenerateRandomDepartmentName_EN() {
    String dept = generateRandomDepartmentName(Locale.ENGLISH);
    assertNotNull(dept);
    assertFalse(dept.isEmpty());
  }

  @Test
  void testDepartmentNameValidity() {
    String cnDept = generateRandomDepartmentName(Locale.CHINA);
    assertTrue(cnDept.contains("部") && !cnDept.contains("Inc"));

    String enDept = generateRandomDepartmentName(Locale.ENGLISH);
    assertTrue(enDept.contains(" ") || enDept.contains("&"));
  }

  @RepeatedTest(20)
  void testGenerateRandomJobTitle_CN() {
    String title = generateRandomJobTitle(Locale.CHINA);
    assertNotNull(title);
  }

  @RepeatedTest(20)
  void testGenerateRandomJobTitle_EN() {
    String title = generateRandomJobTitle(Locale.ENGLISH);
    assertNotNull(title);
  }

  @Test
  void testJobTitleStructure() {
    String cnTitle = generateRandomJobTitle(Locale.CHINA);
    assertFalse(cnTitle.contains(" "), "Chinese job titles should not contain spaces");

    String enTitle = generateRandomJobTitle(Locale.ENGLISH);
    assertTrue(enTitle.contains(" "), "English job titles should contain spaces");
  }

  @Test
  void testJobTitleVariety() {
    Set<String> cnTitles = new HashSet<>();
    Set<String> enTitles = new HashSet<>();

    for (int i = 0; i < 50; i++) {
      cnTitles.add(generateRandomJobTitle(Locale.CHINA));
      enTitles.add(generateRandomJobTitle(Locale.ENGLISH));
    }

    assertTrue(cnTitles.size() >= 30, "Should generate a variety of Chinese job titles");
    assertTrue(enTitles.size() >= 30, "Should generate a variety of English job titles");
  }
}
