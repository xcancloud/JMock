package cloud.xcan.jmock.core.parser.docs.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class DocsModelUnitTest {

  @Test
  void mockFunction_chain() {
    MockFunction f = new MockFunction()
        .setClazz("c")
        .setName("@X()")
        .setDescription("d")
        .setTags(new String[]{"t"})
        .setOrder(3);
    assertEquals("c", f.getClazz());
    assertEquals("@X()", f.getName());
    assertEquals("d", f.getDescription());
    assertEquals(3, f.getOrder());
  }

  @Test
  void mockParameter_and_constructor() {
    MockParameter p = new MockParameter().setName("n").setDescription("pd");
    assertEquals("n", p.getName());

    MockConstructor c = new MockConstructor()
        .setInstance("@X()")
        .setDescription("cd")
        .setParameters(List.of(p))
        .setExample("ex")
        .setExampleValues(new String[]{"v"});
    assertEquals("@X()", c.getInstance());
    assertEquals(1, c.getParameters().size());
    assertEquals("ex", c.getExample());
  }
}
