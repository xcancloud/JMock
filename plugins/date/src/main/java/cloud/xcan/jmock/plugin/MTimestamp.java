package cloud.xcan.jmock.plugin;

import static cloud.xcan.angus.spec.utils.ObjectUtils.nullSafe;
import static cloud.xcan.jmock.plugin.DateDocMessage.DOC_CATEGORY_DATE;
import static cloud.xcan.jmock.plugin.DateDocMessage.DOC_TIMESTAMP_C1;
import static cloud.xcan.jmock.plugin.DateDocMessage.DOC_TIMESTAMP_C2;
import static cloud.xcan.jmock.plugin.DateDocMessage.DOC_TIMESTAMP_DESC;
import static cloud.xcan.jmock.plugin.DateDocMessage.DOC_TIMESTAMP_PARAMETER_UNIX;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Hao Guo
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_TIMESTAMP_DESC,
    categoryI18nKey = {DOC_CATEGORY_DATE}, order = 201)
public class MTimestamp extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_TIMESTAMP_PARAMETER_UNIX)
  private Boolean unix;

  @JMockConstructor(descI18nKey = DOC_TIMESTAMP_C1,
      example = "@Timestamp()",
      exampleValues = {"1653432546438"})
  public MTimestamp() {
    this(false);
  }

  @JMockConstructor(descI18nKey = DOC_TIMESTAMP_C2,
      example = "@Timestamp()",
      exampleValues = {"1653432546438"})
  public MTimestamp(Boolean unix) {
    this.unix = nullSafe(unix, false);
  }

  @Override
  public Long mock() {
    return unix ? System.currentTimeMillis() / 1000 : System.currentTimeMillis();
  }

}
