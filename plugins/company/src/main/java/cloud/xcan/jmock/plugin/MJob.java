package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DATA_JOB_LEVELS;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DATA_JOB_TITLES;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_CATEGORY_COMPANY;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_JOB_C1;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_JOB_C2;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_JOB_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.JMockRandom;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Locale;

@JMockFunctionRegister(descI18nKey = DOC_JOB_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPANY}, order = 4004)
public class MJob extends AbstractMockFunction {

  private final String[] jobLevels;
  private final String[] jobTitles;
  private final boolean isEnglish;

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
    this.isEnglish = locale.equals(Locale.ENGLISH);
    this.jobLevels = getString(DATA_JOB_LEVELS, locale).split("\\|");
    this.jobTitles = getString(DATA_JOB_TITLES, locale).split("\\|");
  }

  @Override
  public String mock() {
    StringBuilder title = new StringBuilder();

    // Level (70% probability)
    if (JMockRandom.nextDouble() < 0.7) {
      title.append(jobLevels[JMockRandom.nextInt(jobLevels.length)]);
      if (isEnglish) {
        title.append(" ");
      }
    }

    // Title
    title.append(jobTitles[JMockRandom.nextInt(jobTitles.length)]);

    return title.toString();
  }
}
