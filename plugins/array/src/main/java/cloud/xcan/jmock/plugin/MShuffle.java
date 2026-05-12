package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_CATEGORY_ARRAY;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SHUFFLE_C1;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SHUFFLE_DESC;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_SHUFFLE_PARAMETER_ITEMS;

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
 * Randomly shuffle the input items and return them in random order.
 *
 * @since 2.0.0
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_SHUFFLE_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARRAY}, order = 123, since = "2.0.0")
public class MShuffle extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_SHUFFLE_PARAMETER_ITEMS, type = "String", required = true)
  private String items;

  private List<String> itemList;

  @JMockConstructor(descI18nKey = DOC_SHUFFLE_C1,
      example = "@Shuffle(a|b|c|d)",
      exampleValues = {"[\"c\",\"a\",\"d\",\"b\"]"})
  public MShuffle(String items) {
    this.items = items;
    this.itemList = new ArrayList<>(List.of(items.split("\\|")));
  }

  @Override
  public Object mock() {
    List<String> shuffled = new ArrayList<>(itemList);
    Collections.shuffle(shuffled);
    return shuffled;
  }
}
