package cloud.xcan.jmock.core.parser.extractor;

import cloud.xcan.jmock.api.AbstractToken;
import java.util.List;

/**
 * Extract function or method call expressions from text (including: xml, json, txt).
 *
 * @param <T> A function expression or method call expression
 */
public interface MockExtractor<T extends AbstractToken> {

  List<T> extract();
}
