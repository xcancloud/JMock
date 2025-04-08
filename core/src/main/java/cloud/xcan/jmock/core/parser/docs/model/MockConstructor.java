package cloud.xcan.jmock.core.parser.docs.model;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class MockConstructor {

  private String instance;

  private String description;

  private List<MockParameter> parameters;

  private String example;

  private String[] exampleValues;

}
