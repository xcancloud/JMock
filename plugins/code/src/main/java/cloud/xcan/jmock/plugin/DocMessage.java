package cloud.xcan.jmock.plugin;

import cloud.xcan.jmock.api.i18n.MessageResources;

public class DocMessage {

  static {
    MessageResources.RESOURCE_BUNDLE.add("i18n/jmock-code-plugin-messages");
  }

  /**
   * Coding order by 30xx.
   */
  public final static String DOC_CATEGORY_CODE = "jmock.func.category.code";

  public final static String DOC_CODE_SNIPPET_DESC = "jmock.func.MCodeSnippet.description";
  public final static String DOC_CODE_PARAMETER_LANGUAGE = "jmock.func.MCodeSnippet.parameter.language";
  public final static String DOC_CODE_SNIPPET_C1 = "jmock.func.MCodeSnippet.C1";
  public final static String DOC_CODE_SNIPPET_C2 = "jmock.func.MCodeSnippet.C2";

  public final static String DOC_ERROR_TYPE_DESC = "jmock.func.MErrorType.description";
  public final static String DOC_ERROR_TYPE_C1 = "jmock.func.MErrorType.C1";

  public final static String DOC_HTML_DESC = "jmock.func.MHtml.description";
  public final static String DOC_HTML_C1 = "jmock.func.MHtml.C1";

  public final static String DOC_MARKDOWN_DESC = "jmock.func.MMarkdown.description";
  public final static String DOC_MARKDOWN_C1 = "jmock.func.MMarkdown.C1";

  public final static String DOC_PROGRAMMING_LANGUAGE_DESC = "jmock.func.MProgrammingLanguage.description";
  public final static String DOC_PROGRAMMING_LANGUAGE_C1 = "jmock.func.MProgrammingLanguage.C1";

}
