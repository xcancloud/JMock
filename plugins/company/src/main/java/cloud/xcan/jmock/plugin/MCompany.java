package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

  private final Locale locale;

  public MCompany() {
    this(Locale.CHINA);
  }

  public MCompany(Locale locale) {
    this.locale = locale;
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

    // 1-3 words + company type
    int wordCount = 1 + random.nextInt(3);
    Set<String> usedWords = new HashSet<>();

    for (int i = 0; i < wordCount; i++) {
      String word;
      do {
        word = words.get(random.nextInt(words.size()));
      } while (usedWords.contains(word));

      name.append(word);
      usedWords.add(word);
    }

    // Add company type
    name.append(types.get(random.nextInt(types.size())));

    return name.toString();
  }
}
