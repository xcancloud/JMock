package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MCompany.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MIndustry extends AbstractMockFunction {

  private static final Map<Locale, List<String>> INDUSTRY_CATEGORIES = Map.of(
      Locale.CHINA, Arrays.asList(
          "信息技术", "金融服务", "医疗健康", "教育培训", "电子商务",
          "智能制造", "文化传媒", "房地产", "能源环保", "物流运输",
          "餐饮服务", "旅游酒店", "农业科技", "汽车制造", "生物医药"
      ),
      Locale.ENGLISH, Arrays.asList(
          "Information Technology", "Financial Services", "Healthcare", "Education", "E-commerce",
          "Manufacturing", "Media & Entertainment", "Real Estate", "Energy & Environment",
          "Logistics", "Food & Beverage", "Travel & Hospitality", "Agriculture Technology",
          "Automotive", "Biotechnology"
      )
  );

  private final Locale locale;

  public MIndustry() {
    this(Locale.CHINA);
  }

  public MIndustry(Locale locale) {
    this.locale = locale;
  }

  @Override
  public String mock() {
    return generateRandomIndustryCategory(locale);
  }

  public static String generateRandomIndustryCategory(Locale locale) {
    List<String> categories = INDUSTRY_CATEGORIES.getOrDefault(
        locale, INDUSTRY_CATEGORIES.get(Locale.ENGLISH)
    );
    return categories.get(random.nextInt(categories.size()));
  }

}
