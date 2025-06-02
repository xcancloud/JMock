package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MCodeSnippet.random;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.Arrays;

public class MMarkdown extends AbstractMockFunction {

  public static final String[] HEADINGS = {
      "Introduction", "Features", "Installation", "Usage", "Examples",
      "Configuration", "Troubleshooting", "FAQ", "Advanced Topics", "Conclusion"
  };

  public static final String[] TECHNOLOGIES = {
      "Docker", "Kubernetes", "Java", "Python", "JavaScript", "React",
      "TensorFlow", "Spring Boot", "PostgreSQL", "Redis", "Elasticsearch"
  };

  public static final String[] ADJECTIVES = {
      "flexible", "powerful", "scalable", "secure", "robust",
      "intuitive", "high-performance", "lightweight", "modular", "extensible"
  };

  public static final String[] VERBS = {
      "simplifies", "accelerates", "enhances", "streamlines", "automates",
      "optimizes", "facilitates", "enables", "transforms", "redefines"
  };

  public static final String[] PARAGRAPH_SENTENCES = {
      "This project provides a comprehensive solution for modern development challenges.",
      "With its intuitive design, you can achieve significant productivity gains.",
      "The architecture is built on proven best practices and industry standards.",
      "Integration with existing systems is straightforward and well-documented.",
      "Performance benchmarks demonstrate significant improvements over alternatives.",
      "Security considerations are at the forefront of the design philosophy.",
      "Extensive customization options allow tailoring to specific use cases.",
      "Community support and active development ensure long-term viability.",
      "The modular design allows for selective adoption of components.",
      "Compatibility with multiple platforms extends the potential use cases."
  };

  public static final String[] CODE_LANGUAGES = {
      "java", "python", "javascript", "typescript", "shell",
      "sql", "yaml", "json", "xml", "dockerfile"
  };

  public static final String[] PROGRAMMING_TERMS = {
      "function", "class", "variable", "loop", "conditional",
      "interface", "implementation", "algorithm", "optimization", "abstraction"
  };

  public static final String[] TABLE_HEADERS = {
      "Parameter", "Type", "Default", "Description", "Required"
  };

  public static final String[] JAVA_CODE = {
      "public class Main {\n    public static void main(String[] args) {\n        System.out.println(\"Hello, World!\");\n    }\n}",
      "public interface Service {\n    void execute();\n}",
      "@SpringBootApplication\npublic class Application {\n    public static void main(String[] args) {\n        SpringApplication.run(Application.class, args);\n    }\n}"
  };

  public static final String[] PYTHON_CODE = {
      "def main():\n    print(\"Hello, World!\")\n\nif __name__ == \"__main__\":\n    main()",
      "class Calculator:\n    def add(self, a, b):\n        return a + b",
      "import pandas as pd\n\ndf = pd.read_csv('data.csv')\nprint(df.head())"
  };

  public static final String[] JAVASCRIPT_CODE = {
      "console.log('Hello, World!');",
      "function sum(a, b) {\n    return a + b;\n}",
      "const express = require('express');\nconst app = express();\n\napp.get('/', (req, res) => {\n    res.send('Hello World!');\n});\n\napp.listen(3000);"
  };

  @Override
  public String mock() {
    return generateRandomMarkdown();
  }

  public static String generateRandomMarkdown() {
    StringBuilder markdown = new StringBuilder();

    markdown.append(generateTitle())
        .append("\n\n")
        .append(generateBadges())
        .append("\n\n");

    markdown.append(generateTableOfContents())
        .append("\n\n");

    int sections = 4 + random.nextInt(4);
    for (int i = 0; i < sections; i++) {
      markdown.append(generateSection())
          .append("\n\n");

      if (random.nextDouble() < 0.3 && i < sections - 1) {
        markdown.append("---\n\n");
      }
    }

    return markdown.toString();
  }

  public static String generateTitle() {
    String tech = TECHNOLOGIES[random.nextInt(TECHNOLOGIES.length)];
    return "# " + tech + " Project Overview";
  }

  public static String generateBadges() {
    String[] badges = {
        "![License](https://img.shields.io/badge/license-MIT-blue.svg)",
        "![Version](https://img.shields.io/badge/version-1.0.0-green.svg)",
        "![Build Status](https://img.shields.io/badge/build-passing-brightgreen.svg)",
        "![Coverage](https://img.shields.io/badge/coverage-95%25-yellowgreen.svg)",
        "![Dependencies](https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg)"
    };

    return String.join(" ", Arrays.copyOfRange(badges, 0, 3 + random.nextInt(3)));
  }

  public static String generateTableOfContents() {
    StringBuilder toc = new StringBuilder("## Table of Contents\n\n");

    for (String heading : Arrays.copyOfRange(HEADINGS, 0, 3 + random.nextInt(5))) {
      toc.append("- [").append(heading).append("](#")
          .append(heading.toLowerCase().replace(" ", "-"))
          .append(")\n");
    }

    toc.append("- [Contributing](#contributing)\n");
    toc.append("- [License](#license)\n");

    return toc.toString();
  }

  public static String generateSection() {
    StringBuilder section = new StringBuilder();

    String heading = HEADINGS[random.nextInt(HEADINGS.length)];
    section.append("## ").append(heading).append("\n\n");

    switch (heading.toLowerCase()) {
      case "introduction":
        section.append(generateIntroduction());
        break;
      case "installation":
        section.append(generateInstallation());
        break;
      case "usage":
        section.append(generateUsage());
        break;
      case "examples":
        section.append(generateExamples());
        break;
      case "configuration":
        section.append(generateConfiguration());
        break;
      default:
        section.append(generateGeneralContent());
    }

    return section.toString();
  }

  public static String generateIntroduction() {
    StringBuilder intro = new StringBuilder();

    intro.append(generateParagraph(3, 5))
        .append("\n\n");

    intro.append("### Key Benefits\n\n");
    intro.append(generateList(4, 6))
        .append("\n\n");

    intro.append("### Technology Stack\n\n");
    intro.append(generateTechStackTable())
        .append("\n\n");

    return intro.toString();
  }

  public static String generateInstallation() {
    StringBuilder install = new StringBuilder();

    install.append("Before you begin, ensure you have the following prerequisites:\n\n");
    install.append(generateList(3, 5))
        .append("\n\n");

    install.append("### Installation Steps\n\n");

    int steps = 3 + random.nextInt(4);
    for (int i = 0; i < steps; i++) {
      install.append("#### Step ").append(i + 1).append("\n\n");
      install.append(generateParagraph(1, 2))
          .append("\n\n");

      if (random.nextDouble() < 0.8) {
        install.append(generateCodeBlock())
            .append("\n\n");
      }
    }

    return install.toString();
  }

  public static String generateUsage() {
    StringBuilder usage = new StringBuilder();

    usage.append("### Basic Usage\n\n");
    usage.append(generateParagraph(2, 4))
        .append("\n\n");
    usage.append(generateCodeBlock())
        .append("\n\n");

    usage.append("### Advanced Features\n\n");
    usage.append(generateParagraph(2, 3))
        .append("\n\n");

    usage.append(generateFeatureTable())
        .append("\n\n");

    usage.append("### Common Patterns\n\n");
    usage.append(generateCodeBlockWithExplanation())
        .append("\n\n");

    return usage.toString();
  }

  public static String generateExamples() {
    StringBuilder examples = new StringBuilder();

    int exampleCount = 2 + random.nextInt(3);
    for (int i = 0; i < exampleCount; i++) {
      examples.append("### Example ").append(i + 1).append("\n\n");
      examples.append(generateParagraph(1, 2))
          .append("\n\n");
      examples.append(generateCodeBlock())
          .append("\n\n");

      examples.append("**Explanation:**\n\n");
      examples.append(generateParagraph(1, 3))
          .append("\n\n");
    }

    return examples.toString();
  }

  public static String generateConfiguration() {
    StringBuilder config = new StringBuilder();

    config.append("### Configuration Options\n\n");
    config.append("The following options can be specified in the configuration file:\n\n");
    config.append(generateConfigTable())
        .append("\n\n");

    config.append("### Example Configuration\n\n");
    config.append(generateCodeBlock("yaml"))
        .append("\n\n");

    return config.toString();
  }

  public static String generateGeneralContent() {
    StringBuilder content = new StringBuilder();

    content.append(generateParagraph(2, 4))
        .append("\n\n");

    double elementChance = random.nextDouble();
    if (elementChance < 0.3) {
      content.append(generateBlockQuote())
          .append("\n\n");
    } else if (elementChance < 0.6) {
      content.append(generateCallout())
          .append("\n\n");
    }

    if (random.nextDouble() < 0.5) {
      content.append(generateList(3, 5))
          .append("\n\n");
    }

    if (random.nextDouble() < 0.4) {
      content.append(generateSimpleTable())
          .append("\n\n");
    }

    return content.toString();
  }

  public static String generateParagraph(int minSentences, int maxSentences) {
    StringBuilder paragraph = new StringBuilder();

    int sentenceCount = minSentences + random.nextInt(maxSentences - minSentences + 1);
    for (int i = 0; i < sentenceCount; i++) {
      String sentence = PARAGRAPH_SENTENCES[random.nextInt(PARAGRAPH_SENTENCES.length)];

      if (random.nextDouble() < 0.3) {
        String tech = TECHNOLOGIES[random.nextInt(TECHNOLOGIES.length)];
        sentence = sentence.replace("project", "**" + tech + "**");
      }

      if (random.nextDouble() < 0.2) {
        String term = PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
        sentence = sentence.replace("design", "`" + term + "`");
      }

      if (random.nextDouble() < 0.15) {
        String linkText = "documentation";
        sentence = sentence.replace(linkText, "[" + linkText + "](#documentation)");
      }

      paragraph.append(sentence);

      if (i < sentenceCount - 1) {
        paragraph.append(" ");
      }
    }

    return paragraph.toString();
  }

  public static String generateList(int minItems, int maxItems) {
    StringBuilder list = new StringBuilder();

    boolean ordered = random.nextBoolean();
    int itemCount = minItems + random.nextInt(maxItems - minItems + 1);

    for (int i = 0; i < itemCount; i++) {
      if (ordered) {
        list.append(i + 1).append(". ");
      } else {
        list.append("- ");
      }

      String item;
      if (random.nextDouble() < 0.3) {
        item = PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)] + ":\n  " +
            (random.nextBoolean() ? "- " : "  - ") +
            PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      } else if (random.nextDouble() < 0.2) {
        item = "- [ ] " + VERBS[random.nextInt(VERBS.length)] + " " +
            PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      } else {
        item = ADJECTIVES[random.nextInt(ADJECTIVES.length)] + " " +
            PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      }

      list.append(item).append("\n");
    }

    return list.toString();
  }

  public static String generateCodeBlock() {
    return generateCodeBlock(CODE_LANGUAGES[random.nextInt(CODE_LANGUAGES.length)]);
  }

  public static String generateCodeBlock(String language) {
    String code = switch (language) {
      case "java" -> JAVA_CODE[random.nextInt(JAVA_CODE.length)];
      case "python" -> PYTHON_CODE[random.nextInt(PYTHON_CODE.length)];
      case "javascript" -> JAVASCRIPT_CODE[random.nextInt(JAVASCRIPT_CODE.length)];
      case "shell" -> "curl -O https://example.com/install.sh\nchmod +x install.sh\n./install.sh";
      case "yaml" -> "server:\n  port: 8080\n\ndatabase:\n  host: localhost\n  name: example";
      default -> "console.log('Hello, World!');";
    };
    return "```" + language + "\n" + code + "\n```";
  }

  public static String generateCodeBlockWithExplanation() {
    StringBuilder block = new StringBuilder();

    block.append(generateParagraph(1, 2))
        .append("\n\n");
    block.append(generateCodeBlock())
        .append("\n\n");
    block.append(generateParagraph(1, 2));

    return block.toString();
  }

  public static String generateTechStackTable() {
    StringBuilder table = new StringBuilder();

    table.append("| Technology | Purpose | Maturity |\n");
    table.append("|------------|---------|----------|\n");

    int rows = 4 + random.nextInt(4); // 4-7行
    for (int i = 0; i < rows; i++) {
      String tech = TECHNOLOGIES[random.nextInt(TECHNOLOGIES.length)];
      String purpose = ADJECTIVES[random.nextInt(ADJECTIVES.length)] + " " +
          PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      String maturity = random.nextBoolean() ? "Production-ready" : "Experimental";

      table.append("| ").append(tech).append(" | ").append(purpose).append(" | ").append(maturity)
          .append(" |\n");
    }

    return table.toString();
  }

  public static String generateFeatureTable() {
    StringBuilder table = new StringBuilder();

    table.append("| Feature | Description | Status |\n");
    table.append("|---------|-------------|--------|\n");

    int rows = 5 + random.nextInt(4); // 5-8行
    for (int i = 0; i < rows; i++) {
      String feature = PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      String description = VERBS[random.nextInt(VERBS.length)] + " " +
          PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      String status = random.nextBoolean() ? "✅ Implemented" : "🔄 Planned";

      table.append("| ").append(feature).append(" | ").append(description).append(" | ")
          .append(status).append(" |\n");
    }

    return table.toString();
  }

  public static String generateConfigTable() {
    StringBuilder table = new StringBuilder();

    table.append("| ").append(String.join(" | ", TABLE_HEADERS)).append(" |\n");
    table.append("|");
    for (int i = 0; i < TABLE_HEADERS.length; i++) {
      table.append("---|");
    }
    table.append("\n");

    int rows = 5 + random.nextInt(6);
    for (int i = 0; i < rows; i++) {
      String param = "`config." + PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)] + "`";
      String type = random.nextBoolean() ? "string" : "integer";
      String defaultValue =
          random.nextBoolean() ? "`null`" : (random.nextBoolean() ? "`0`" : "`\"\"`");
      String description = ADJECTIVES[random.nextInt(ADJECTIVES.length)] + " configuration for " +
          PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
      String required = random.nextBoolean() ? "Yes" : "No";

      table.append("| ").append(param).append(" | ")
          .append(type).append(" | ")
          .append(defaultValue).append(" | ")
          .append(description).append(" | ")
          .append(required).append(" |\n");
    }

    return table.toString();
  }

  public static String generateSimpleTable() {
    StringBuilder table = new StringBuilder();

    int columns = 3;
    int rows = 3 + random.nextInt(4);

    table.append("|");
    for (int i = 0; i < columns; i++) {
      table.append(" Header ").append(i + 1).append(" |");
    }
    table.append("\n|");
    for (int i = 0; i < columns; i++) {
      table.append("---|");
    }
    table.append("\n");

    for (int i = 0; i < rows; i++) {
      table.append("|");
      for (int j = 0; j < columns; j++) {
        String cellContent;
        if (i == 0 && j == 0) {
          cellContent = "**Key**";
        } else if (j == 0) {
          cellContent = PROGRAMMING_TERMS[random.nextInt(PROGRAMMING_TERMS.length)];
        } else if (j == 1) {
          cellContent = ADJECTIVES[random.nextInt(ADJECTIVES.length)];
        } else {
          cellContent = VERBS[random.nextInt(VERBS.length)];
        }
        table.append(" ").append(cellContent).append(" |");
      }
      table.append("\n");
    }
    return table.toString();
  }

  public static String generateBlockQuote() {
    return "> **Note**: " + generateParagraph(1, 1);
  }

  public static String generateCallout() {
    String[] calloutTypes = {"info", "warning", "tip", "danger"};
    String type = calloutTypes[random.nextInt(calloutTypes.length)];
    return "> [!" + type + "]\n> " + generateParagraph(1, 2);
  }

}
