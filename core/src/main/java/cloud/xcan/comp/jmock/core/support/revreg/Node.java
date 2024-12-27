package cloud.xcan.comp.jmock.core.support.revreg;

import cloud.xcan.comp.jmock.core.support.revreg.exception.RegexpIllegalException;
import cloud.xcan.comp.jmock.core.support.revreg.exception.TypeNotMatchException;
import cloud.xcan.comp.jmock.core.support.revreg.exception.UninitializedException;

public interface Node {

  String getExpression();

  String random() throws UninitializedException, RegexpIllegalException;

  boolean test();

  void init() throws RegexpIllegalException, TypeNotMatchException;

  boolean isInitialized();
}
