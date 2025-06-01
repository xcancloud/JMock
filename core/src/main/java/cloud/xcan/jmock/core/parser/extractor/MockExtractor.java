package cloud.xcan.jmock.core.parser.extractor;

import cloud.xcan.jmock.api.FunctionToken;
import java.util.List;

public interface MockExtractor {

  List<FunctionToken> extract();
}
