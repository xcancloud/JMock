package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_CATEGORY_ARRAY;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_REPEAT_C1;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_REPEAT_C2;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_REPEAT_DESC;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_REPEAT_PARAMETER_COUNT;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_REPEAT_PARAMETER_VALUE;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Generate an array by repeating a value or expression N times.
 *
 * @since 2.0.0
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_REPEAT_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARRAY}, order = 121, since = "2.0.0")
public class MRepeat extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_REPEAT_PARAMETER_VALUE, type = "String", required = true)
  private String value;

  @JMockParameter(descI18nKey = DOC_REPEAT_PARAMETER_COUNT, type = "Integer", defaultValue = "1")
  private Integer count;

  @JMockConstructor(descI18nKey = DOC_REPEAT_C1,
      example = "@Repeat(hello,3)",
      exampleValues = {"[\"hello\",\"hello\",\"hello\"]"})
  public MRepeat(String value, Integer count) {
    this.value = value;
    this.count = Math.max(count, 0);
  }

  @JMockConstructor(descI18nKey = DOC_REPEAT_C2,
      example = "@Repeat(hello)",
      exampleValues = {"[\"hello\"]"})
  public MRepeat(String value) {
    this(value, 1);
  }

  @Override
  public Object mock() {
    List<String> result = new ArrayList<>(count);
    for (int i = 0; i < count; i++) {
      result.add(value);
    }
    return result;
  }
}
