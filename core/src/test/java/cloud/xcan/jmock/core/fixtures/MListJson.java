package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.List;

/**
 * Returns a list so {@link cloud.xcan.jmock.core.engine.MockRenderer} exercises JSON formatting.
 */
public class MListJson extends AbstractMockFunction {

  @Override
  public Object mock() {
    return List.of("a\"b", "x\ny", 99);
  }
}
