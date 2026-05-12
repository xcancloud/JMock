package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_CATEGORY_ARRAY;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_ONEOF_C1;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_ONEOF_DESC;
import static cloud.xcan.jmock.plugin.ArrayDocMessage.DOC_ONEOF_PARAMETER_ITEMS;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;

/**
 * Randomly select one item from the input list.
 *
 * @since 2.0.0
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_ONEOF_DESC,
    categoryI18nKey = {DOC_CATEGORY_ARRAY}, order = 125, since = "2.0.0")
public class MOneOf extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_ONEOF_PARAMETER_ITEMS, type = "String", required = true)
  private String items;

  private List<String> itemList;

  @JMockConstructor(descI18nKey = DOC_ONEOF_C1,
      example = "@OneOf(apple|banana|cherry)",
      exampleValues = {"banana"})
  public MOneOf(String items) {
    this.items = items;
    this.itemList = List.of(items.split("\\|"));
  }

  @Override
  public Object mock() {
    return itemList.get(ThreadLocalRandom.current().nextInt(itemList.size()));
  }
}
