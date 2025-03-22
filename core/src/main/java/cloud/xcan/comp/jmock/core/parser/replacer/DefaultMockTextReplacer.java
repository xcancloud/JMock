package cloud.xcan.comp.jmock.core.parser.replacer;


import static org.apache.commons.lang3.ObjectUtils.isEmpty;

import cloud.xcan.comp.jmock.core.parser.extractor.DefaultMockTextExtractor;

/**
 * @author XiaoLong Liu
 */
public final class DefaultMockTextReplacer extends DefaultMockExpressionReplacer {

  @Override
  public synchronized String replace(String content) throws Exception {
    if (isEmpty(content)) {
      return content;
    }
    return replace(content, new DefaultMockTextExtractor(content).extract());
  }
}
