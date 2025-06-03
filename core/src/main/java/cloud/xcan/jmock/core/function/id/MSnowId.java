package cloud.xcan.jmock.core.function.id;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ID;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SNOWID_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SNOWID_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SNOWID_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SNOWID_PARAMETER_DCID;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_SNOWID_PARAMETER_MID;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.core.support.utils.SnowIdUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hao.guo
 * @author XiaoLong Liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_SNOWID_DESC,
    categoryI18nKey = {DOC_CATEGORY_ID}, order = 304)
public class MSnowId extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_SNOWID_PARAMETER_DCID)
  private long dcId;

  @JMockParameter(descI18nKey = DOC_SNOWID_PARAMETER_MID)
  private long mId;

  private final static Integer DEFAULT_DC_ID = 1;

  private final static Integer DEFAULT_M_ID = 1;

  private final static int MAX_DC_ID = 31;

  private final static int MAX_M_ID = 31;

  private SnowIdUtils snowIdUtils;

  @JMockConstructor(descI18nKey = DOC_SNOWID_C1,
      example = "@SnowId()",
      exampleValues = {"16685359784"})
  public MSnowId() {
    this(DEFAULT_DC_ID, DEFAULT_M_ID);
  }

  @JMockConstructor(descI18nKey = DOC_SNOWID_C2,
      example = "@SnowId(1,1)",
      exampleValues = {"16685359784"})
  public MSnowId(Integer dcId, Integer mid) {
    if (dcId != null && (dcId > MAX_DC_ID || dcId < 0)) {
      throw new RuntimeException(
          String.format("dataCenterId can't be greater than %d or less than 0", dcId));
    }
    if (mid != null && (mid > MAX_M_ID || mid < 0)) {
      throw new RuntimeException(
          String.format("workerId can't be greater than %d or less than 0", mid));
    }
    this.dcId = dcId == null ? DEFAULT_DC_ID : dcId;
    this.mId = mid == null ? DEFAULT_M_ID : mid;
    this.snowIdUtils = new SnowIdUtils(this.mId, this.dcId);
  }

  @Override
  public Long mock() {
    return snowIdUtils.nextId();
  }
}
