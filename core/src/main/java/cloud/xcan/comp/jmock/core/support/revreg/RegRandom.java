package cloud.xcan.comp.jmock.core.support.revreg;

import cloud.xcan.comp.jmock.core.support.revreg.exception.RegexpIllegalException;
import cloud.xcan.comp.jmock.core.support.revreg.exception.TypeNotMatchException;
import cloud.xcan.comp.jmock.core.support.revreg.exception.UninitializedException;
import java.util.List;

/**
 * @see `https://github.com/stuartwdouglasmidstream/github--mifmif--Generex`
 * @see `/workspace/workspace_3rd/mock/github--mifmif--Generex`
 */
public class RegRandom extends BaseNode {

  private Node proxyNode;

  public RegRandom(String expression) throws RegexpIllegalException, TypeNotMatchException {
    super(expression);
  }

  protected RegRandom(List<String> expressionFragments)
      throws RegexpIllegalException, TypeNotMatchException {
    super(expressionFragments);
  }

  @Override
  protected void init(String expression, List<String> expressionFragments)
      throws RegexpIllegalException, TypeNotMatchException {
    if (expressionFragments.isEmpty()) {
      return;
    }
    Node[] nodes = new Node[]{
        new OptionalNode(expressionFragments, false),
        new SingleNode(expressionFragments, false),
        new RepeatNode(expressionFragments, false),
        new LinkNode(expressionFragments, false)
    };
    for (Node node : nodes) {
      if (node.test()) {
        proxyNode = node;
        proxyNode.init();
        break;
      }
    }
  }

  @Override
  protected String random(String expression, List<String> expressionFragments)
      throws UninitializedException, RegexpIllegalException {
    if (proxyNode == null) {
      return "";
    }
    return proxyNode.random();
  }

}
