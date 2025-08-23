package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.plugin.IDDocMessage.DOC_CATEGORY_ID;
import static cloud.xcan.jmock.plugin.IDDocMessage.DOC_UUID_C1;
import static cloud.xcan.jmock.plugin.IDDocMessage.DOC_UUID_C2;
import static cloud.xcan.jmock.plugin.IDDocMessage.DOC_UUID_DESC;
import static cloud.xcan.jmock.plugin.IDDocMessage.DOC_UUID_PARAMETER_FORMAT;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.support.utils.UUIDUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hao Guo
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_UUID_DESC,
    categoryI18nKey = {DOC_CATEGORY_ID}, order = 302)
public class MUuid extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_UUID_PARAMETER_FORMAT)
  private Boolean withoutSeparator;

  /**
   * Max value of Integer allowed
   */
  final static boolean DEFAULT_WITHOUT_SEPARATOR = false;

  @JMockConstructor(descI18nKey = DOC_UUID_C1,
      example = "@Uuid()",
      exampleValues = {"JIDLOIFIUDASNVHDLODGHYGONLKDHKP"})
  public MUuid() {
    this(DEFAULT_WITHOUT_SEPARATOR);
  }

  @JMockConstructor(descI18nKey = DOC_UUID_C2,
      example = "@Uuid(true)",
      exampleValues = {"JIDLO-IFIUD-ASNVH-DLODG-HYGON-LKDHKP"})
  public MUuid(Boolean withoutSeparator) {
    this.withoutSeparator = withoutSeparator;
  }

  @Override
  public String mock() {
    return UUIDUtils.randomStringUUID(withoutSeparator);
  }
}
