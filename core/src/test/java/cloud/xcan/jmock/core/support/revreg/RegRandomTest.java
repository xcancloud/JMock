package cloud.xcan.jmock.core.support.revreg;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class RegRandomTest {

  @Test
  public void testSimpleLiteral() {
    String regex = "test";
    String result = RegRandom.random(regex);
    assertEquals("test", result);
  }

  @Test
  public void testCharacterClasses() {
    // Digit character class
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random("\\d");
      assertTrue(result.matches("\\d"));
    }

    // Word character class
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random("\\w");
      assertTrue(result.matches("\\w"));
    }

    // Custom character class
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random("[aeiou]");
      assertTrue(result.matches("[aeiou]"));
    }

    // Range character class
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random("[a-z]");
      assertTrue(result.matches("[a-z]"));
    }

    // Negated character class
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random("[^0-9]");
      assertFalse(result.matches("[0-9]"));
    }
  }

  @Test
  public void testQuantifiers() {
    // Fixed quantity
    String result = RegRandom.random("a{3}");
    assertEquals("aaa", result);

    // Range quantity
    for (int i = 0; i < 10; i++) {
      result = RegRandom.random("b{2,4}");
      assertTrue(result.matches("b{2,4}"));
    }

    // Question mark (zero or one)
    for (int i = 0; i < 10; i++) {
      result = RegRandom.random("c?");
      assertTrue(result.matches("c?"));
    }

    // Asterisk (zero or more)
    for (int i = 0; i < 10; i++) {
      result = RegRandom.random("d*");
      assertTrue(result.matches("d*"));
    }

    // Plus (one or more)
    for (int i = 0; i < 10; i++) {
      result = RegRandom.random("e+");
      assertTrue(result.matches("e+"));
    }
  }

  @Test
  public void testGroupsAndAlternation() {
    // Simple group
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random("(red|green|blue)");
      assertTrue(result.matches("red|green|blue"));
    }
  }

  @Test
  public void testEscapes() {
    // Escaping special characters
    String result = RegRandom.random("\\.");
    assertEquals(".", result);

    result = RegRandom.random("\\*");
    assertEquals("*", result);

    result = RegRandom.random("\\\\");
    assertEquals("\\", result);

    result = RegRandom.random("\\[");
    assertEquals("[", result);

    result = RegRandom.random("\\]");
    assertEquals("]", result);
  }


  @Test
  public void testWildcard() {
    // Wildcard with quantifier
    for (int i = 0; i < 10; i++) {
      String result = RegRandom.random(".{3,5}");
      assertTrue(result.length() >= 3 && result.length() <= 5);
    }
  }

  @Test
  public void testEmptyInput() {
    String result = RegRandom.random("");
    assertEquals("", result);
  }

  @Test
  public void testSpecialCases() {
    // Nested group
    String result = RegRandom.random("((a|b)c)");
    assertTrue(result.matches("(a|b)c"));

    // Complex character class
    result = RegRandom.random("[a-zA-Z0-9_]");
    assertTrue(result.matches("[a-zA-Z0-9_]"));

    // Quantifier followed by other characters
    result = RegRandom.random("a+b");
    assertTrue(result.matches("a+b"));
  }

  @Test
  public void testLiteral() {
    String regex = "hello";
    String result = RegRandom.random(regex);
    assertEquals(regex, result);
  }

  @Test
  public void testDigit() {
    Pattern pattern = Pattern.compile("\\d");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("\\d");
      assertTrue(pattern.matcher(s).matches());
    }
  }

  @Test
  public void testQuantifier() {
    Pattern pattern = Pattern.compile("[a-c]{2,5}");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("[a-c]{2,5}");
      assertTrue(pattern.matcher(s).matches());
      assertTrue(s.length() >= 2 && s.length() <= 5);
    }
  }

  @Test
  public void testCharacterClass() {
    Pattern pattern = Pattern.compile("[A-Z]");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("[A-Z]");
      assertTrue(pattern.matcher(s).matches());
      assertTrue(s.charAt(0) >= 'A' && s.charAt(0) <= 'Z');
    }
  }

  @Test
  public void testNegatedCharacterClass() {
    Pattern pattern = Pattern.compile("[^0-9]");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("[^0-9]");
      assertTrue(pattern.matcher(s).matches());
      assertFalse(Character.isDigit(s.charAt(0)));
    }
  }

  @Test
  public void testEscapedCharacters() {
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("\\*\\+\\?\\.");
      assertEquals("*+?.", s);
    }
  }

  @Test
  public void testAlternation() {
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("cat|dog");
      assertTrue(s.equals("cat") || s.equals("dog"));
    }
  }

  @Test
  public void testComplexExpression() {
    Pattern pattern = Pattern.compile("User-\\d{3,5}[A-Z]{2}");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("User-\\d{3,5}[A-Z]{2}");
      assertTrue(pattern.matcher(s).matches());
      assertTrue(s.startsWith("User-"));
      int numLength = s.length() - 7; // "User-XXXYY".length - 2 letters
      assertTrue(numLength >= 3 && numLength <= 5);
    }

    testPredefinedClass("\\d", "0123456789");
    testPredefinedClass("\\w", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_");
    testPredefinedClass("\\s", " \t\n\r\f");
  }

  private void testPredefinedClass(String regex, String allowed) {
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random(regex);
      assertEquals(1, s.length());
      assertTrue(allowed.indexOf(s.charAt(0)) >= 0);
    }
  }

  @Test
  public void testGroupWithQuantifier1() {
    Pattern pattern = Pattern.compile("(abc)+");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("(abc)+");
      assertTrue(pattern.matcher(s).matches());
      assertTrue(s.length() >= 3 && s.length() % 3 == 0);
      assertTrue(s.matches("(abc)+"));
    }
  }

  @Test
  public void testRangeQuantifier2() {
    Pattern pattern = Pattern.compile("a{3,6}");
    for (int i = 0; i < 100; i++) {
      String s = RegRandom.random("a{3,6}");
      assertTrue(pattern.matcher(s).matches());
      assertTrue(s.length() >= 3 && s.length() <= 6);
      assertTrue(s.matches("^a+$"));
    }
  }
}
