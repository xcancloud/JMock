package cloud.xcan.comp.jmock.core.parser.docs.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
public class MockFunction {

  private String clazz;

  private String name;

  private String description;

  private String[] tags;

  private List<MockConstructor> constructors;

  private List<MockParameter> parameters;

  private int order;

}
