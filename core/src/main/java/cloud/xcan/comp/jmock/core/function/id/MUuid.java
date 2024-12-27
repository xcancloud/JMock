package cloud.xcan.comp.jmock.core.function.id;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ID;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MUUID_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MUUID_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MUUID_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MUUID_PARAMETER_FORMAT;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.core.support.utils.UUIDUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hao.guo
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MUUID_DESC,
    categoryI18nKey = {DOC_CATEGORY_ID}, order = 302)
public class MUuid extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MUUID_PARAMETER_FORMAT)
  private Boolean withoutSeparator;

  /**
   * Max value of Integer allowed
   */
  final static boolean DEFAULT_WITHOUT_SEPARATOR = false;

  @JMockConstructor(descI18nKey = DOC_MUUID_C1,
      example = "@Uuid()",
      exampleValues = {"JIDLOIFIUDASNVHDLODGHYGONLKDHKP"})
  public MUuid() {
    this(DEFAULT_WITHOUT_SEPARATOR);
  }

  @JMockConstructor(descI18nKey = DOC_MUUID_C2,
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
