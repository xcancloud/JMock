package cloud.xcan.jmock.core.parser;

import static cloud.xcan.angus.spec.utils.ObjectUtils.isEmpty;
import static cloud.xcan.jmock.api.TokenChars.FUNC_IDENTIFIER;
import static cloud.xcan.jmock.api.i18n.MessageResources.getString;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

import cloud.xcan.angus.spec.experimental.Assert;
import cloud.xcan.angus.spec.locale.SupportedLanguage;
import cloud.xcan.angus.spec.utils.JsonUtils;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.core.environment.DefaultEvalEnvironment;
import cloud.xcan.jmock.core.environment.Environment;
import cloud.xcan.jmock.core.parser.docs.model.MockConstructor;
import cloud.xcan.jmock.core.parser.docs.model.MockFunction;
import cloud.xcan.jmock.core.parser.docs.model.MockParameter;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockFunctionDocParser {

  private static final int DEFAULT_CASE_DAY = 30;

  /**
   * Load mock functions in environment.
   */
  private final Environment environment;

  /**
   * Cache doc parsing results.
   */
  private final Cache<String, List<MockFunction>> caches;

  public MockFunctionDocParser() {
    this(new DefaultEvalEnvironment(), DEFAULT_CASE_DAY, TimeUnit.DAYS);
  }

  public MockFunctionDocParser(Environment environment, long duration, TimeUnit unit) {
    this.environment = environment;
    this.caches = Caffeine.newBuilder()
        .expireAfterWrite(duration, unit).maximumSize(SupportedLanguage.values().length)
        .build();
  }

  public List<MockFunction> parse(SupportedLanguage language) {
    List<MockFunction> mockFunctions = new ArrayList<>();
    Map<String, Class<? extends cloud.xcan.jmock.api.MockFunction>> mfClazz = environment.getMockClass();
    if (isEmpty(mfClazz)) {
      return mockFunctions;
    }
    for (Entry<String, Class<? extends cloud.xcan.jmock.api.MockFunction>> entry : mfClazz.entrySet()) {
      JMockFunctionRegister functionAnnotation = entry.getValue().getAnnotation(
          JMockFunctionRegister.class);
      // Ignore MockFunction that have not been annotated by @JMockFunctionRegister
      if (Objects.nonNull(functionAnnotation)) {
        // Parsing function information
        MockFunction mockFunction = new MockFunction();
        mockFunction.setName(
                FUNC_IDENTIFIER + entry.getValue().getSimpleName().substring(1).concat("()"))
            .setClazz(entry.getValue().getTypeName())
            .setDescription(getString(functionAnnotation.descI18nKey(), language.toLocale()))
            .setTags(Stream.of(functionAnnotation.categoryI18nKey())
                .map(x -> getString(x, language.toLocale())).toArray(String[]::new))
            .setOrder(functionAnnotation.order());

        // Parsing parameters information
        List<MockParameter> mockParameters = new ArrayList<>();
        Field[] parameters = entry.getValue().getDeclaredFields();
        if (isNotEmpty(parameters)) {
          for (Field parameter : parameters) {
            parameter.setAccessible(true);
            JMockParameter parameterAnnotation = parameter.getAnnotation(JMockParameter.class);
            // Ignore parameter that have not been annotated by @JMockParameter
            if (Objects.nonNull(parameterAnnotation)) {
              MockParameter mockParameter = new MockParameter();
              mockParameter.setName(parameter.getName())
                  .setDescription(getString(parameterAnnotation.descI18nKey(),
                      language.toLocale()));
              mockParameters.add(mockParameter);
            }
          }
          mockFunction.setParameters(mockParameters);
        }

        // Parsing constructors information
        List<MockConstructor> mockConstructors = new ArrayList<>();
        java.lang.reflect.Constructor<?>[] constructors = entry.getValue().getConstructors();
        Assert.assertNotEmpty(constructors, "Mock function constructor is required");
        for (Constructor<?> constructor : constructors) {
          constructor.setAccessible(false);
          JMockConstructor constructAnnotation = constructor.getAnnotation(JMockConstructor.class);
          // Ignore constructors that have not been annotated by @JMockConstructor
          if (Objects.nonNull(constructAnnotation)) {
            MockConstructor funcConstructor = new MockConstructor();
            Parameter[] constructorParameters = constructor.getParameters();
            List<String> parameterNames = Arrays.stream(constructorParameters)
                .map(Parameter::getName).collect(Collectors.toList());
            if (isNotEmpty(parameterNames)) {
              String parameterNameStr = String.join(",", parameterNames);
              String funcConstructorInstance = mockFunction.getName()
                  .replace("(", "").replace(")", "")
                  .concat("(" + parameterNameStr + ")");
              funcConstructor.setInstance(funcConstructorInstance)
                  .setDescription(getString(constructAnnotation.descI18nKey(), language.toLocale()))
                  .setParameters(mockParameters.stream()
                      .filter(x -> parameterNames.contains(x.getName()))
                      .collect(Collectors.toList()))
                  .setExample(constructAnnotation.example())
                  .setExampleValues(constructAnnotation.exampleValues());
            } else {
              // No parameter constructor
              funcConstructor.setInstance(mockFunction.getName())
                  .setDescription(getString(constructAnnotation.descI18nKey(), language.toLocale()))
                  .setParameters(null)
                  .setExample(constructAnnotation.example())
                  .setExampleValues(constructAnnotation.exampleValues());
            }
            mockConstructors.add(funcConstructor);
          }
          mockFunction.setConstructors(mockConstructors);
        }
        mockFunctions.add(mockFunction);
      }
    }

    // Sort by order
    return mockFunctions.stream()
        .sorted(Comparator.comparingInt(MockFunction::getOrder))
        .collect(Collectors.toList());
  }

  public List<MockFunction> load(SupportedLanguage language) {
    return caches.get(language.getValue(), key -> parse(language) /* Create and cache */);
  }

  public List<MockFunction> reload(SupportedLanguage language) {
    List<MockFunction> docs = parse(language);
    caches.put(language.name(), docs);
    return docs;
  }

  public static void main(String[] args) throws IOException {
    MockFunctionDocParser generator = new MockFunctionDocParser();
    List<MockFunction> mockFunctions = generator.parse(SupportedLanguage.zh_CN);
    String absolutePath = MockFunctionDocParser.class.getClassLoader().getResource("").getPath();
    String filePath = absolutePath + "JMockFunction.json";
    System.out.println(filePath);
    FileOutputStream fos = new FileOutputStream(filePath);
    fos.write(JsonUtils.toJson(mockFunctions).getBytes());
    fos.close();
  }
}
