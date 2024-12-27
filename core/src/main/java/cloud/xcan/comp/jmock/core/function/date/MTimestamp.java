package cloud.xcan.comp.jmock.core.function.date;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_DATE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTIMESTAMP_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTIMESTAMP_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTIMESTAMP_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MTIMESTAMP_PARAMETER_UNIX;
import static cloud.xcan.sdf.spec.utils.ObjectUtils.nullSafe;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hao.guo
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MTIMESTAMP_DESC,
    categoryI18nKey = {DOC_CATEGORY_DATE}, order = 201)
public class MTimestamp extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MTIMESTAMP_PARAMETER_UNIX)
  private Boolean unix;

  @JMockConstructor(descI18nKey = DOC_MTIMESTAMP_C1,
      example = "@Timestamp()",
      exampleValues = {"1653432546438"})
  public MTimestamp() {
    this(false);
  }

  @JMockConstructor(descI18nKey = DOC_MTIMESTAMP_C2,
      example = "@Timestamp()",
      exampleValues = {"1653432546438"})
  public MTimestamp(Boolean unix) {
    this.unix = nullSafe(unix, false);
  }

  @Override
  public Long mock() {
    return unix ?  System.currentTimeMillis() / 1000 : System.currentTimeMillis();
  }

}
