package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.AbstractMockFunction;

/**
 * Test double: no-arg or single string constructor.
 */
public class MEcho extends AbstractMockFunction {

  private final String value;

  public MEcho() {
    this("default");
  }

  public MEcho(String value) {
    this.value = value;
  }

  @Override
  public Object mock() {
    return value;
  }
}
