package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MCompany.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MDepartment extends AbstractMockFunction {

  public static final Map<Locale, List<String>> DEPARTMENT_NAMES = Map.of(
      Locale.CHINA, Arrays.asList(
          "人力资源部", "财务部", "市场部", "销售部", "技术研发部",
          "客户服务部", "行政部", "法务部", "采购部", "生产部",
          "质量监控部", "战略发展部", "国际业务部", "数据科学部", "产品管理部"
      ),
      Locale.ENGLISH, Arrays.asList(
          "Human Resources", "Finance", "Marketing", "Sales", "R&D",
          "Customer Service", "Administration", "Legal", "Procurement", "Production",
          "Quality Assurance", "Strategy & Development", "International Business", "Data Science",
          "Product Management"
      )
  );

  private final Locale locale;

  public MDepartment() {
    this(Locale.CHINA);
  }

  public MDepartment(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomDepartmentName(locale);
  }

  public static String generateRandomDepartmentName(Locale locale) {
    List<String> departments = DEPARTMENT_NAMES.getOrDefault(
        locale, DEPARTMENT_NAMES.get(Locale.ENGLISH)
    );
    return departments.get(random.nextInt(departments.size()));
  }
}
