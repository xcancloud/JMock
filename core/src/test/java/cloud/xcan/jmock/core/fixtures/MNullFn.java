package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.AbstractMockFunction;

public class MNullFn extends AbstractMockFunction {

  @Override
  public Object mock() {
    return null;
  }
}
