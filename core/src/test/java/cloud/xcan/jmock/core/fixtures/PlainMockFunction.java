package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.MockFunction;

/**
 * Implements {@link MockFunction} without extending
 * {@link cloud.xcan.jmock.api.AbstractMockFunction}.
 */
public class PlainMockFunction implements MockFunction {

  @Override
  public Object mock() {
    return 1;
  }
}
