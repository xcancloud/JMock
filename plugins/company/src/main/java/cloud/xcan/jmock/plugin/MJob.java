package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_CATEGORY_COMPANY;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_JOB_C1;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_JOB_C2;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_JOB_DESC;
import static cloud.xcan.jmock.plugin.MCompany.random;
import static java.util.Arrays.asList;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@JMockFunctionRegister(descI18nKey = DOC_JOB_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPANY}, order = 4004)
public class MJob extends AbstractMockFunction {

  private static final Map<Locale, List<String>> JOB_LEVELS = Map.of(
      Locale.CHINA, asList("初级", "高级", "资深", "首席", "主管", "助理", "副", "经理"),
      Locale.ENGLISH,
      asList("Junior", "Senior", "Lead", "Chief", "Supervisor", "Assistant", "Associate", "Manager")
  );

  private static final Map<Locale, List<String>> JOB_TITLES = Map.of(
      Locale.CHINA,
      asList("专员", "经理", "总监", "执行官", "分析师", "工程师", "顾问", "代表", "协调员",
          "专家"),
      Locale.ENGLISH,
      asList("Specialist", "Manager", "Director", "Officer", "Analyst", "Engineer",
          "Consultant", "Representative", "Coordinator", "Expert")
  );

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_JOB_C1,
      example = "@Job()",
      exampleValues = {"高级协调员", "总监"})
  public MJob() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_JOB_C2,
      example = "@Job(en)",
      exampleValues = {"Assistant Specialist", "Associate Coordinator"})
  public MJob(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomJobTitle(locale);
  }

  public static String generateRandomJobTitle(Locale locale) {
    List<String> levels = JOB_LEVELS.get(locale);
    List<String> titles = JOB_TITLES.get(locale);

    StringBuilder title = new StringBuilder();

    // Level (70% probability)
    if (random.nextDouble() < 0.7) {
      title.append(levels.get(random.nextInt(levels.size())));
      if (locale.equals(Locale.ENGLISH)) {
        title.append(" ");
      }
    }

    // Title
    title.append(titles.get(random.nextInt(titles.size())));

    return title.toString();
  }
}
