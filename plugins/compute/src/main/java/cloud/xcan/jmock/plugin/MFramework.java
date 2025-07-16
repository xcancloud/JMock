package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_CATEGORY_COMPUTE;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FRAMEWORK_C1;
import static cloud.xcan.jmock.plugin.ComputeDocMessage.DOC_FRAMEWORK_DESC;
import static cloud.xcan.jmock.plugin.MBrowser.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_FRAMEWORK_DESC,
    categoryI18nKey = {DOC_CATEGORY_COMPUTE}, order = 5010)
public class MFramework extends AbstractMockFunction {

  public static final List<String> FRONTEND_FRAMEWORKS = Arrays.asList(
      "React", "Angular", "Vue.js", "Svelte", "Ember.js", "Backbone.js"
  );

  public static final List<String> BACKEND_FRAMEWORKS = Arrays.asList(
      "Spring Boot", "Django", "Flask", "Express.js", "Ruby on Rails",
      "Laravel", "ASP.NET Core", "FastAPI", "NestJS"
  );

  public static final List<String> MOBILE_FRAMEWORKS = Arrays.asList(
      "React Native", "Flutter", "Xamarin", "Ionic", "SwiftUI", "Jetpack Compose"
  );

  @JMockConstructor(descI18nKey = DOC_FRAMEWORK_C1,
      example = "@Framework()",
      exampleValues = {"React Native", "Ruby on Rails"})
  public MFramework() {
  }

  @Override
  public String mock() {
    int category = random.nextInt(100);
    if (category < 40) {
      // Frontend frameworks (40% probability)
      return FRONTEND_FRAMEWORKS.get(random.nextInt(FRONTEND_FRAMEWORKS.size()));
    } else if (category < 80) {
      // Backend frameworks (40% probability)
      return BACKEND_FRAMEWORKS.get(random.nextInt(BACKEND_FRAMEWORKS.size()));
    } else {
      // Mobile frameworks (20% probability)
      return MOBILE_FRAMEWORKS.get(random.nextInt(MOBILE_FRAMEWORKS.size()));
    }
  }
}
