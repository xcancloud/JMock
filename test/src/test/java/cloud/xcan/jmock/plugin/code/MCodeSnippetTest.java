package cloud.xcan.jmock.plugin.code;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cloud.xcan.jmock.plugin.MCodeSnippet;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

class MCodeSnippetTest {

  @Test
  void testGetSupportedLanguages() {
    List<MCodeSnippet.Language> languages = MCodeSnippet.getSupportedLanguages();

    assertNotNull(languages);
    assertFalse(languages.isEmpty());
    assertTrue(languages.size() >= 6);

    assertTrue(languages.contains(MCodeSnippet.Language.PYTHON));
    assertTrue(languages.contains(MCodeSnippet.Language.JAVA));
    assertTrue(languages.contains(MCodeSnippet.Language.JAVASCRIPT));
    assertTrue(languages.contains(MCodeSnippet.Language.CPP));
  }

  @Test
  void testGetLanguageName() {
    assertEquals("Python",
        MCodeSnippet.getLanguageName(MCodeSnippet.Language.PYTHON));
    assertEquals("Java", MCodeSnippet.getLanguageName(MCodeSnippet.Language.JAVA));
    assertEquals("JavaScript",
        MCodeSnippet.getLanguageName(MCodeSnippet.Language.JAVASCRIPT));
  }

  @RepeatedTest(10)
  void testGenerateClass() {
    for (MCodeSnippet.Language lang : Arrays.asList(
        MCodeSnippet.Language.PYTHON,
        MCodeSnippet.Language.JAVA,
        MCodeSnippet.Language.TYPESCRIPT,
        MCodeSnippet.Language.CSHARP,
        MCodeSnippet.Language.SWIFT,
        MCodeSnippet.Language.GO
    )) {
      String snippet = MCodeSnippet.generateClass(lang);
      assertNotNull(snippet);
      assertFalse(snippet.isEmpty());

      assertTrue(snippet.contains("class") || snippet.contains("type"));
    }
  }

  @RepeatedTest(10)
  void testGenerateLoop() {
    for (MCodeSnippet.Language lang : MCodeSnippet.getSupportedLanguages()) {
      String snippet = MCodeSnippet.generateLoop(lang);
      assertNotNull(snippet);
      assertFalse(snippet.isEmpty());

      assertTrue(snippet.contains("for ") || snippet.contains("while ") ||
          snippet.contains("range"));
    }
  }

  @RepeatedTest(10)
  void testGenerateConditional() {
    for (MCodeSnippet.Language lang : MCodeSnippet.getSupportedLanguages()) {
      String snippet = MCodeSnippet.generateConditional(lang);
      assertNotNull(snippet);
      assertFalse(snippet.isEmpty());

      assertTrue(snippet.contains("if ") || snippet.contains("switch"));

      assertTrue(snippet.contains(">") || snippet.contains("<"));
    }
  }

  @Test
  void testGenerateIdentifier() {
    for (int i = 0; i < 100; i++) {
      String identifier = MCodeSnippet.generateIdentifier("variable");
      assertNotNull(identifier);
      assertFalse(identifier.isEmpty());
      assertFalse(identifier.contains(" "));
    }
  }

  @Test
  void testGenerateFunctionEdgeCase() {
    for (MCodeSnippet.Language lang : MCodeSnippet.getSupportedLanguages()) {
      String snippet = MCodeSnippet.generateFunction(lang);
      assertTrue(snippet.contains("{") && snippet.contains("}"),
          "Template placeholders missing in " + lang);
    }
  }

}
