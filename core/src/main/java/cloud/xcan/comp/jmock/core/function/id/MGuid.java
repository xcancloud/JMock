package cloud.xcan.comp.jmock.core.function.id;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ID;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MGUID_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MGUID_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MGUID_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MGUID_PARAMETER_FORMAT;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hao.guo
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MGUID_DESC,
    categoryI18nKey = {DOC_CATEGORY_ID}, order = 301)
public class MGuid extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MGUID_PARAMETER_FORMAT)
  private Boolean withoutSeparator;

  private MUuid mUuid;

  @JMockConstructor(descI18nKey = DOC_MGUID_C1,
      example = "@Guid()",
      exampleValues = {"JIDLOIFIUDASNVHDLODGHYGONLKDHKP"})
  public MGuid() {
    this(MUuid.DEFAULT_WITHOUT_SEPARATOR);
  }

  @JMockConstructor(descI18nKey = DOC_MGUID_C2,
      example = "@Guid(true)",
      exampleValues = {"JIDLO-IFIUD-ASNVH-DLODG-HYGON-LKDHKP"})
  public MGuid(Boolean withoutSeparator) {
    this.withoutSeparator = withoutSeparator;
    this.mUuid = new MUuid(withoutSeparator);
  }

  @Override
  public String mock() {
    return mUuid.mock();
  }
}
