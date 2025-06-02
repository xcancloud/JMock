package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.MCodeSnippet.random;

import cloud.xcan.jmock.api.AbstractMockFunction;

public class MHtml extends AbstractMockFunction {

  public static final String[] HEADING_TAGS = {"h1", "h2", "h3", "h4", "h5", "h6"};
  public static final String[] TEXT_CONTENT = {
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit.",
      "Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.",
      "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris.",
      "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore."
  };
  public static final String[] BUTTON_TEXTS = {
      "Submit", "Save", "Cancel", "Delete", "Update", "Next", "Back", "Search"
  };
  public static final String[] FORM_LABELS = {
      "Username", "Email", "Password", "Address", "Phone", "Quantity", "Message"
  };
  public static final String[] IMAGE_ALTS = {
      "Landscape", "Portrait", "Product", "Diagram", "Screenshot", "Infographic"
  };
  public static final String[] TABLE_HEADERS = {
      "ID", "Name", "Price", "Quantity", "Date", "Status", "Category"
  };
  public static final String[] CSS_CLASSES = {
      "container", "wrapper", "card", "header", "footer", "sidebar", "content",
      "nav", "menu", "btn", "form", "table", "grid", "row", "col"
  };

  public static final String[] COLORS = {
      "#3498db", "#2ecc71", "#e74c3c", "#f39c12", "#9b59b6",
      "#1abc9c", "#d35400", "#c0392b", "#16a085", "#8e44ad"
  };

  public static final String IMAGE_SERVICE = "https://picsum.photos";

  @Override
  public String mock() {
    return generateRandomHtmlDocument();
  }

  public static String generateRandomHtmlDocument() {
    StringBuilder html = new StringBuilder();

    html.append("<!DOCTYPE html>\n");
    html.append("<html lang=\"en\">\n");

    html.append("<head>\n");
    html.append("  <meta charset=\"UTF-8\">\n");
    html.append("  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
    html.append("  <title>").append(generateRandomTitle()).append("</title>\n");
    html.append("  <style>\n");
    html.append(generateRandomCss());
    html.append("  </style>\n");
    html.append("</head>\n");

    html.append("<body>\n");
    html.append(generateRandomBodyContent());
    html.append("</body>\n");

    html.append("</html>");

    return html.toString();
  }

  public static String generateRandomTitle() {
    String[] prefixes = {"Home", "About", "Services", "Products", "Contact", "Blog", "Support",
        "Documentation"};
    String[] suffixes = {"Page", "Portal", "Dashboard", "Hub", "Center", "System"};

    return prefixes[random.nextInt(prefixes.length)] + " " +
        suffixes[random.nextInt(suffixes.length)];
  }

  public static String generateRandomCss() {
    StringBuilder css = new StringBuilder();

    css.append("    body {\n");
    css.append("      font-family: ").append(getRandomFontFamily()).append(";\n");
    css.append("      line-height: 1.6;\n");
    css.append("      color: #333;\n");
    css.append("      max-width: 1200px;\n");
    css.append("      margin: 0 auto;\n");
    css.append("      padding: 20px;\n");
    css.append("      background-color: #f9f9f9;\n");
    css.append("    }\n\n");

    css.append("    .container {\n");
    css.append("      background-color: white;\n");
    css.append("      border-radius: 8px;\n");
    css.append("      box-shadow: 0 2px 10px rgba(0,0,0,0.1);\n");
    css.append("      padding: 20px;\n");
    css.append("      margin-bottom: 30px;\n");
    css.append("    }\n\n");

    css.append("    h1, h2, h3 {\n");
    css.append("      color: ").append(COLORS[random.nextInt(COLORS.length)]).append(";\n");
    css.append("      margin-top: 0;\n");
    css.append("    }\n\n");

    css.append("    .btn {\n");
    css.append("      display: inline-block;\n");
    css.append("      padding: 10px 20px;\n");
    css.append("      background-color: ").append(COLORS[random.nextInt(COLORS.length)])
        .append(";\n");
    css.append("      color: white;\n");
    css.append("      text-decoration: none;\n");
    css.append("      border-radius: 4px;\n");
    css.append("      border: none;\n");
    css.append("      cursor: pointer;\n");
    css.append("      font-weight: bold;\n");
    css.append("      transition: background-color 0.3s;\n");
    css.append("    }\n\n");

    css.append("    .btn:hover {\n");
    css.append("      background-color: ").append(COLORS[random.nextInt(COLORS.length)])
        .append(";\n");
    css.append("    }\n\n");

    css.append("    table {\n");
    css.append("      width: 100%;\n");
    css.append("      border-collapse: collapse;\n");
    css.append("      margin: 20px 0;\n");
    css.append("    }\n\n");

    css.append("    th, td {\n");
    css.append("      border: 1px solid #ddd;\n");
    css.append("      padding: 12px;\n");
    css.append("      text-align: left;\n");
    css.append("    }\n\n");

    css.append("    th {\n");
    css.append("      background-color: #f2f2f2;\n");
    css.append("    }\n\n");

    css.append("    .form-group {\n");
    css.append("      margin-bottom: 15px;\n");
    css.append("    }\n\n");

    css.append("    label {\n");
    css.append("      display: block;\n");
    css.append("      margin-bottom: 5px;\n");
    css.append("      font-weight: bold;\n");
    css.append("    }\n\n");

    css.append("    input, textarea, select {\n");
    css.append("      width: 100%;\n");
    css.append("      padding: 10px;\n");
    css.append("      border: 1px solid #ddd;\n");
    css.append("      border-radius: 4px;\n");
    css.append("    }\n");

    return css.toString();
  }

  public static String getRandomFontFamily() {
    String[] fonts = {
        "'Segoe UI', Tahoma, Geneva, Verdana, sans-serif",
        "'Helvetica Neue', Arial, sans-serif",
        "Georgia, 'Times New Roman', Times, serif",
        "'Courier New', Courier, monospace"
    };
    return fonts[random.nextInt(fonts.length)];
  }

  public static String generateRandomBodyContent() {
    StringBuilder body = new StringBuilder();

    body.append("<div class=\"container\">\n");

    body.append("  <").append(HEADING_TAGS[random.nextInt(2)]) // h1-h3
        .append(">").append(generateRandomHeading()).append("</")
        .append(HEADING_TAGS[random.nextInt(2)]).append(">\n");

    int paragraphCount = 2 + random.nextInt(3);
    for (int i = 0; i < paragraphCount; i++) {
      body.append("  <p>").append(generateRandomParagraph()).append("</p>\n");
    }

    int elementsToAdd = 3 + random.nextInt(3);
    for (int i = 0; i < elementsToAdd; i++) {
      switch (random.nextInt(5)) {
        case 0:
          body.append(generateRandomList());
          break;
        case 1:
          body.append(generateRandomTable());
          break;
        case 2:
          body.append(generateRandomForm());
          break;
        case 3:
          body.append(generateRandomImage());
          break;
        case 4:
          body.append(generateRandomButton());
          break;
      }
    }

    body.append("</div>\n");
    return body.toString();
  }

  public static String generateRandomHeading() {
    String[] prefixes = {"Welcome to", "About", "Our", "Introduction to", "The Future of"};
    String[] subjects = {"Product", "Service", "Company", "Technology", "Solution", "Platform"};
    String[] suffixes = {"Overview", "Features", "Benefits", "Vision", "Mission"};

    return prefixes[random.nextInt(prefixes.length)] + " " +
        subjects[random.nextInt(subjects.length)] + " " +
        suffixes[random.nextInt(suffixes.length)];
  }

  public static String generateRandomParagraph() {
    StringBuilder paragraph = new StringBuilder();

    int sentenceCount = 3 + random.nextInt(4);
    for (int i = 0; i < sentenceCount; i++) {
      paragraph.append(TEXT_CONTENT[random.nextInt(TEXT_CONTENT.length)]);

      if (random.nextDouble() < 0.3) {
        String[] styles = {"strong", "em", "u", "code"};
        String style = styles[random.nextInt(styles.length)];
        paragraph.insert(paragraph.length() - 1, "<" + style + ">");
        paragraph.insert(paragraph.length() - 1, "</" + style + ">");
      }

      if (i < sentenceCount - 1) {
        paragraph.append(" ");
      }
    }

    return paragraph.toString();
  }

  public static String generateRandomList() {
    StringBuilder list = new StringBuilder();

    String listType = random.nextBoolean() ? "ul" : "ol";
    list.append("  <").append(listType).append(">\n");

    int itemCount = 3 + random.nextInt(4);
    for (int i = 0; i < itemCount; i++) {
      list.append("    <li>").append(generateRandomListItem()).append("</li>\n");
    }

    list.append("  </").append(listType).append(">\n");
    return list.toString();
  }

  public static String generateRandomListItem() {
    String[] prefixes = {"Important", "Key", "Primary", "Essential", "Critical"};
    String[] subjects = {"feature", "benefit", "aspect", "element", "consideration"};
    String[] verbs = {"saves time", "improves efficiency", "reduces costs", "enhances security"};

    return prefixes[random.nextInt(prefixes.length)] + " " +
        subjects[random.nextInt(subjects.length)] + " that " +
        verbs[random.nextInt(verbs.length)];
  }

  public static String generateRandomTable() {
    StringBuilder table = new StringBuilder();

    table.append("  <table>\n");
    table.append("    <thead>\n");
    table.append("      <tr>\n");

    int colCount = 3 + random.nextInt(3);
    for (int i = 0; i < colCount; i++) {
      table.append("        <th>").append(TABLE_HEADERS[random.nextInt(TABLE_HEADERS.length)])
          .append("</th>\n");
    }

    table.append("      </tr>\n");
    table.append("    </thead>\n");
    table.append("    <tbody>\n");

    int rowCount = 3 + random.nextInt(4);
    for (int i = 0; i < rowCount; i++) {
      table.append("      <tr>\n");

      for (int j = 0; j < colCount; j++) {
        table.append("        <td>").append(generateRandomTableCell()).append("</td>\n");
      }

      table.append("      </tr>\n");
    }

    table.append("    </tbody>\n");
    table.append("  </table>\n");
    return table.toString();
  }

  public static String generateRandomTableCell() {
    if (random.nextBoolean()) {
      String[] items = {"Product A", "Service B", "Item C", "Component D", "Feature E"};
      return items[random.nextInt(items.length)];
    } else {
      return String.valueOf(10 + random.nextInt(91)); // 10-100
    }
  }

  public static String generateRandomForm() {
    StringBuilder form = new StringBuilder();

    form.append("  <form>\n");

    int fieldCount = 3 + random.nextInt(3);
    for (int i = 0; i < fieldCount; i++) {
      form.append("    <div class=\"form-group\">\n");
      form.append("      <label for=\"field").append(i).append("\">")
          .append(FORM_LABELS[random.nextInt(FORM_LABELS.length)]).append("</label>\n");

      switch (random.nextInt(4)) {
        case 0:
          form.append("      <input type=\"text\" id=\"field").append(i).append("\">\n");
          break;
        case 1:
          form.append("      <input type=\"email\" id=\"field").append(i).append("\">\n");
          break;
        case 2:
          form.append("      <input type=\"password\" id=\"field").append(i).append("\">\n");
          break;
        case 3:
          form.append("      <textarea id=\"field").append(i).append("\" rows=\"4\"></textarea>\n");
          break;
      }

      form.append("    </div>\n");
    }

    form.append("    <button type=\"submit\" class=\"btn\">")
        .append(BUTTON_TEXTS[random.nextInt(BUTTON_TEXTS.length)]).append("</button>\n");

    form.append("  </form>\n");
    return form.toString();
  }

  public static String generateRandomImage() {
    int width = 400 + random.nextInt(401); // 400-800
    int height = 300 + random.nextInt(301); // 300-600

    return "  <img src=\"" + IMAGE_SERVICE + "/" + width + "/" + height + "\" " +
        "alt=\"" + IMAGE_ALTS[random.nextInt(IMAGE_ALTS.length)] + "\" " +
        "class=\"responsive-img\">\n";
  }

  public static String generateRandomButton() {
    return "  <button class=\"btn\">" +
        BUTTON_TEXTS[random.nextInt(BUTTON_TEXTS.length)] + "</button>\n";
  }
}
