package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.AbstractMockFunction;

/**
 * Only (int,int) constructor — used to exercise constructor failure / fallback paths.
 */
public class MStrict extends AbstractMockFunction {

  private final int sum;

  public MStrict(int a, int b) {
    this.sum = a + b;
  }

  @Override
  public Object mock() {
    return sum;
  }
}
