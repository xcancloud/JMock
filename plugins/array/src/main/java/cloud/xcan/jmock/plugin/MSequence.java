package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_CATEGORY_ARRAY;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_C1;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_C2;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_C3;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_DESC;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_PARAMETER_COUNT;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_PARAMETER_START;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SEQUENCE_PARAMETER_STEP;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Generate an arithmetic sequence (start, start+step, start+2*step, ...).
 *
 * @since 2.0.0
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_SEQUENCE_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARRAY}, order = 122, since = "2.0.0")
public class MSequence extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_SEQUENCE_PARAMETER_START)
  private long start;

  @JMockParameter(descI18nKey = DOC_SEQUENCE_PARAMETER_STEP)
  private long step;

  @JMockParameter(descI18nKey = DOC_SEQUENCE_PARAMETER_COUNT)
  private int count;

  @JMockConstructor(descI18nKey = DOC_SEQUENCE_C1,
      example = "@Sequence(1,1,5)",
      exampleValues = {"[1,2,3,4,5]"})
  public MSequence(Integer start, Integer step, Integer count) {
    this.start = start;
    this.step = step;
    this.count = Math.max(count, 0);
  }

  @JMockConstructor(descI18nKey = DOC_SEQUENCE_C2,
      example = "@Sequence(0,2,4)",
      exampleValues = {"[0,2,4,6]"})
  public MSequence(Integer start, Integer count) {
    this(start, 1, count);
  }

  @JMockConstructor(descI18nKey = DOC_SEQUENCE_C3,
      example = "@Sequence(5)",
      exampleValues = {"[0,1,2,3,4]"})
  public MSequence(Integer count) {
    this(0, 1, count);
  }

  @Override
  public Object mock() {
    List<Long> result = new ArrayList<>(count);
    long current = start;
    for (int i = 0; i < count; i++) {
      result.add(current);
      current += step;
    }
    return result;
  }
}
