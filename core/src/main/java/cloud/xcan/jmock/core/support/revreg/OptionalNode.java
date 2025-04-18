package cloud.xcan.jmock.core.support.revreg;

import cloud.xcan.jmock.core.support.revreg.exception.RegexpIllegalException;
import cloud.xcan.jmock.core.support.revreg.exception.TypeNotMatchException;
import cloud.xcan.jmock.core.support.revreg.exception.UninitializedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OptionalNode extends BaseNode {

  private List<Node> children;

  protected OptionalNode(List<String> expressionFragments)
      throws RegexpIllegalException, TypeNotMatchException {
    super(expressionFragments);
  }

  protected OptionalNode(List<String> expressionFragments, boolean initialize)
      throws RegexpIllegalException, TypeNotMatchException {
    super(expressionFragments, initialize);
  }

  @Override
  protected boolean test(String expression, List<String> expressionFragments) {
    for (String fragment : expressionFragments) {
      if ("|".equals(fragment)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected void init(String expression, List<String> expressionFragments)
      throws RegexpIllegalException, TypeNotMatchException {
    children = new ArrayList<>();
    List<String> subFragments = new ArrayList<>();
    for (String fragment : expressionFragments) {
      if ("|".equals(fragment)) {
        children.add(new RegRandom(subFragments));
        subFragments = new ArrayList<>();
        continue;
      }
      subFragments.add(fragment);
    }
    children.add(new RegRandom(subFragments));
  }

  @Override
  protected String random(String expression, List<String> expressionFragments)
      throws UninitializedException, RegexpIllegalException {
    return children.get(new Random().nextInt(children.size())).random();
  }
}
