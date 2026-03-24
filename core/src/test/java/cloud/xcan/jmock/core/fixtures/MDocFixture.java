package cloud.xcan.jmock.core.fixtures;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;

@JMockFunctionRegister(
    descI18nKey = "jmock.parser.func.constructor.mismatch.t",
    categoryI18nKey = {"jmock.parser.func.constructor.mismatch.t"},
    order = 5)
public class MDocFixture extends AbstractMockFunction {

  @JMockParameter(descI18nKey = "jmock.parser.func.constructor.mismatch.t")
  private String sampleParam;

  @JMockConstructor(
      descI18nKey = "jmock.parser.func.constructor.mismatch.t",
      example = "@DocFixture()",
      exampleValues = {"x"})
  public MDocFixture() {
  }

  @Override
  public Object mock() {
    return "doc";
  }
}
