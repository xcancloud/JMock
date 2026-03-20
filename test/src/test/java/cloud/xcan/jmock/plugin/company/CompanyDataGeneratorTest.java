package cloud.xcan.jmock.plugin.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MCompany;
import cloud.xcan.jmock.plugin.MDepartment;
import cloud.xcan.jmock.plugin.MIndustry;
import cloud.xcan.jmock.plugin.MJob;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class CompanyDataGeneratorTest {

  @RepeatedTest(20)
  void testGenerateRandomCompanyName_CN() {
    String name = new MCompany().mock();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }

  @RepeatedTest(20)
  void testGenerateRandomCompanyName_EN() {
    String name = new MCompany("en").mock();
    assertNotNull(name);
    assertFalse(name.isEmpty());
  }

  @Test
  void testCompanyNameUniqueness() {
    Set<String> names = new HashSet<>();
    MCompany company = new MCompany();
    for (int i = 0; i < 100; i++) {
      names.add(company.mock());
    }
    assertTrue(names.size() >= 50, "Should generate at least 50 distinct Chinese company names, got: " + names.size());
  }

  @RepeatedTest(20)
  void testGenerateRandomIndustryCategory_CN() {
    String category = new MIndustry().mock();
    assertNotNull(category);
    assertFalse(category.isEmpty());
  }

  @RepeatedTest(20)
  void testGenerateRandomIndustryCategory_EN() {
    String category = new MIndustry("en").mock();
    assertNotNull(category);
    assertFalse(category.isEmpty());
  }

  @Test
  void testIndustryCategoryCoverage() {
    Set<String> cnCategories = new HashSet<>();
    Set<String> enCategories = new HashSet<>();
    MIndustry cnIndustry = new MIndustry();
    MIndustry enIndustry = new MIndustry("en");

    for (int i = 0; i < 50; i++) {
      cnCategories.add(cnIndustry.mock());
      enCategories.add(enIndustry.mock());
    }

    assertTrue(cnCategories.size() >= 5, "Should cover multiple Chinese industry categories");
    assertTrue(enCategories.size() >= 5, "Should cover multiple English industry categories");
  }

  @RepeatedTest(20)
  void testGenerateRandomDepartmentName_CN() {
    String dept = new MDepartment().mock();
    assertNotNull(dept);
    assertFalse(dept.isEmpty());
  }

  @RepeatedTest(20)
  void testGenerateRandomDepartmentName_EN() {
    String dept = new MDepartment("en").mock();
    assertNotNull(dept);
    assertFalse(dept.isEmpty());
  }

  @Test
  void testDepartmentNameCoverage() {
    Set<String> cnDepts = new HashSet<>();
    Set<String> enDepts = new HashSet<>();
    MDepartment cnDept = new MDepartment();
    MDepartment enDept = new MDepartment("en");

    for (int i = 0; i < 50; i++) {
      cnDepts.add(cnDept.mock());
      enDepts.add(enDept.mock());
    }

    assertTrue(cnDepts.size() >= 5, "Should cover multiple Chinese departments");
    assertTrue(enDepts.size() >= 5, "Should cover multiple English departments");
  }

  @RepeatedTest(20)
  void testGenerateRandomJobTitle_CN() {
    String title = new MJob().mock();
    assertNotNull(title);
    assertFalse(title.isEmpty());
  }

  @RepeatedTest(20)
  void testGenerateRandomJobTitle_EN() {
    String title = new MJob("en").mock();
    assertNotNull(title);
    assertFalse(title.isEmpty());
  }

  @Test
  void testJobTitleVariety() {
    Set<String> cnTitles = new HashSet<>();
    Set<String> enTitles = new HashSet<>();
    MJob cnJob = new MJob();
    MJob enJob = new MJob("en");

    for (int i = 0; i < 50; i++) {
      cnTitles.add(cnJob.mock());
      enTitles.add(enJob.mock());
    }

    assertTrue(cnTitles.size() >= 10, "Should generate a variety of Chinese job titles, got: " + cnTitles.size());
    assertTrue(enTitles.size() >= 10, "Should generate a variety of English job titles, got: " + enTitles.size());
  }
}
