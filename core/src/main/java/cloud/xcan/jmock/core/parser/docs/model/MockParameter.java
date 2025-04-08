package cloud.xcan.jmock.core.parser.docs.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
public class MockParameter {

  private String name;

  private String description;

}
