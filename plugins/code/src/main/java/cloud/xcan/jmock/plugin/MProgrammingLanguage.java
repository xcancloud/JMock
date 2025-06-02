package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MCodeSnippet.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;
import java.util.List;

public class MProgrammingLanguage extends AbstractMockFunction {

  public static final List<String> PROGRAMMING_LANGUAGES = Arrays.asList(
      "Python", "JavaScript", "Java", "C++", "C#", "TypeScript", "Go", "Rust",
      "Swift", "Kotlin", "PHP", "Ruby", "Scala", "Dart", "Elixir", "Clojure"
  );

  @Override
  public String mock() {
    return generateRandomProgrammingLanguage();
  }

  public static String generateRandomProgrammingLanguage(){
    return PROGRAMMING_LANGUAGES.get(random.nextInt(PROGRAMMING_LANGUAGES.size()));
  }

}
