package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.AbstractMockFunction;
import java.util.List;

public class MNestedList extends AbstractMockFunction {

  @Override
  public Object mock() {
    return List.of(List.of("inner"));
  }
}
