package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_CATEGORY_COMPANY;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_COMPANY_C1;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_COMPANY_C2;
import static cloud.xcan.jmock.plugin.CompanyDocMessage.DOC_COMPANY_DESC;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@JMockFunctionRegister(descI18nKey = DOC_COMPANY_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPANY}, order = 4001)
public class MCompany extends AbstractMockFunction {

  public static final SecureRandom random = new SecureRandom();

  public static final List<String> CN_COMPANY_TYPES = Arrays.asList(
      "有限公司", "集团", "股份有限公司", "控股集团", "科技公司", "实业公司", "咨询公司", "国际集团"
  );

  public static final List<String> EN_COMPANY_TYPES = Arrays.asList(
      "LLC", "Inc.", "Corp.", "Group", "Holdings", "Global", "International", "Solutions"
  );

  public static final List<String> CN_COMPANY_WORDS = Arrays.asList(
      "创新", "卓越", "未来", "智慧", "华夏", "东方", "环球", "星辰", "科技", "数据",
      "云", "数智", "恒通", "博雅", "启明", "宏图", "鑫达", "信达", "智联", "创想"
  );

  public static final List<String> EN_COMPANY_WORDS = Arrays.asList(
      "Alpha", "Beta", "Gamma", "Stellar", "Nova", "Quantum", "Apex", "Pinnacle",
      "Horizon", "Vertex", "Summit", "Crest", "Peak", "Dynamic", "Strategic", "Agile",
      "Tech", "Data", "Cloud", "Digital", "Cyber", "Global", "Capital", "Wealth"
  );

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private final Locale locale;

  @JMockConstructor(descI18nKey = DOC_COMPANY_C1,
      example = "@Company()",
      exampleValues = {"云信达咨询公司", "东方有限公司"})
  public MCompany() {
    this(Locale.CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_COMPANY_C2,
      example = "@Company(en)",
      exampleValues = {"Alpha Gamma Digital Corp.", "Gamma Apex Strategic Holdings"})
  public MCompany(Locale locale) {
    this.locale = locale;
  }

  public static void main(String[] args) {
    System.out.println(new MCompany(Locale.ENGLISH).mock());
  }

  @Override
  public String mock() {
    return generateRandomCompanyName(locale);
  }

  public static String generateRandomCompanyName(Locale locale) {
    boolean isChinese = locale.equals(Locale.CHINA);

    List<String> words = isChinese ? CN_COMPANY_WORDS : EN_COMPANY_WORDS;
    List<String> types = isChinese ? CN_COMPANY_TYPES : EN_COMPANY_TYPES;

    StringBuilder name = new StringBuilder();

    // 2-3 words + company type
    int wordCount = 1 + random.nextInt(3);
    Set<String> usedWords = new HashSet<>();

    for (int i = 0; i < wordCount; i++) {
      String word;
      do {
        word = words.get(random.nextInt(words.size()));
      } while (usedWords.contains(word));

      name.append(word);
      if (!isChinese) {
        name.append(" ");
      }
      usedWords.add(word);
    }

    // Add company type
    name.append(types.get(random.nextInt(types.size())));

    return name.toString();
  }
}
