package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MCompany.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MJob extends AbstractMockFunction {

  private static final Map<Locale, List<String>> JOB_LEVELS = Map.of(
      Locale.CHINA, Arrays.asList("初级", "高级", "资深", "首席", "主管", "助理", "副", "经理"),
      Locale.ENGLISH,
      Arrays.asList("Junior", "Senior", "Lead", "Chief", "Supervisor", "Assistant", "Associate",
          "Manager")
  );

  private static final Map<Locale, List<String>> JOB_TITLES = Map.of(
      Locale.CHINA,
      Arrays.asList("专员", "经理", "总监", "执行官", "分析师", "工程师", "顾问", "代表", "协调员", "专家"),
      Locale.ENGLISH,
      Arrays.asList("Specialist", "Manager", "Director", "Officer", "Analyst", "Engineer",
          "Consultant", "Representative", "Coordinator", "Expert")
  );

  private final Locale locale;

  public MJob() {
    this(Locale.CHINA);
  }

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
