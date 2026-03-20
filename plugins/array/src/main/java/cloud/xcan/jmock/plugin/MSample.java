package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_CATEGORY_ARRAY;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SAMPLE_C1;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SAMPLE_DESC;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SAMPLE_PARAMETER_COUNT;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SAMPLE_PARAMETER_ITEMS;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * Sample N unique items from the input list without replacement.
 *
 * @since 2.0.0
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_SAMPLE_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARRAY}, order = 124, since = "2.0.0")
public class MSample extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_SAMPLE_PARAMETER_ITEMS)
  private String items;

  @JMockParameter(descI18nKey = DOC_SAMPLE_PARAMETER_COUNT)
  private Integer count;

  private List<String> itemList;

  @JMockConstructor(descI18nKey = DOC_SAMPLE_C1,
      example = "@Sample(a|b|c|d|e,3)",
      exampleValues = {"[\"c\",\"a\",\"e\"]"})
  public MSample(String items, Integer count) {
    this.items = items;
    this.itemList = new ArrayList<>(List.of(items.split("\\|")));
    this.count = Math.min(Math.max(count, 0), this.itemList.size());
  }

  @Override
  public Object mock() {
    List<String> shuffled = new ArrayList<>(itemList);
    Collections.shuffle(shuffled);
    return shuffled.subList(0, count);
  }
}
