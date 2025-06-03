package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_BOOL_C1;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_CATEGORY_CODE;
import static cloud.xcan.jmock.plugin.DocMessage.DOC_PROGRAMMING_LANGUAGE_DESC;
import static cloud.xcan.jmock.plugin.MCodeSnippet.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import java.util.Arrays;
import java.util.List;

@JMockFunctionRegister(descI18nKey = DOC_PROGRAMMING_LANGUAGE_DESC, categoryI18nKey = {
    DOC_CATEGORY_CODE}, order = 3005)
public class MProgrammingLanguage extends AbstractMockFunction {

  public static final List<String> PROGRAMMING_LANGUAGES = Arrays.asList(
      "Python", "JavaScript", "Java", "C++", "C#", "TypeScript", "Go", "Rust",
      "Swift", "Kotlin", "PHP", "Ruby", "Scala", "Dart", "Elixir", "Clojure"
  );

  @JMockConstructor(descI18nKey = DOC_BOOL_C1,
      example = "@ProgrammingLanguage()",
      exampleValues = {"Kotlin", "Scala", "JavaScript"})
  public MProgrammingLanguage() {
  }

  @Override
  public String mock() {
    return generateRandomProgrammingLanguage();
  }

  public static String generateRandomProgrammingLanguage(){
    return PROGRAMMING_LANGUAGES.get(random.nextInt(PROGRAMMING_LANGUAGES.size()));
  }

}
