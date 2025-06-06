package cloud.xcan.jmock.core.function.user;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_C4;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_PARAMETER_MAX;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_PARAMETER_MIN;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_EMAIL_PARAMETER_SUFFIX;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.jmock.core.support.utils.RandomEmailUtils.email;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.i18n.JMockMessage;
import cloud.xcan.jmock.api.i18n.MessageResources;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_EMAIL_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 806)
public class MEmail extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_EMAIL_PARAMETER_MIN)
  private int min;

  @JMockParameter(descI18nKey = DOC_EMAIL_PARAMETER_MAX)
  private int max;

  @JMockParameter(descI18nKey = DOC_EMAIL_PARAMETER_SUFFIX)
  private String suffix;

  final static int DEFAULT_MIN_VALUE = 6;

  final static int DEFAULT_MAX_VALUE = 20;

  private String[] suffixArray;

  @JMockConstructor(descI18nKey = DOC_EMAIL_C1,
      example = "@Email()",
      exampleValues = {"WF8W5NFeMyrMKAx@153.com", "CcbZYdmDEtWGEY1@yahoo.com.cn"})
  public MEmail() {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, null);
  }

  @JMockConstructor(descI18nKey = DOC_EMAIL_C2,
      example = "@Email(@xcan.cloud)",
      exampleValues = {"WF8W5NFeMyrMKAx@xcan.cloud", "CcbZYdmDEtWGEY1@xcan.cloud"})
  public MEmail(String suffix) {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, suffix);
  }

  @JMockConstructor(descI18nKey = DOC_EMAIL_C3,
      example = "@Email(5,10)",
      exampleValues = {"zjs7cb@hotmail.com", "jsGH6sc@yahoo.com"})
  public MEmail(int min, int max) {
    this(min, max, null);
  }

  @JMockConstructor(descI18nKey = DOC_EMAIL_C4,
      example = "@Email(5,5,@xcan.cloud)",
      exampleValues = {"4g4Fv@xcan.cloud", "Hv8jh@xcan.cloud"})
  public MEmail(int min, int max, String suffix) {
    if (StringUtils.isBlank(suffix)) {
      String appName = MessageResources.getString(JMockMessage.FDATA_EMAIL);
      this.suffixArray = appName.split("\\|");
    } else {
      this.suffixArray = suffix.split("\\|");
    }
    if (max > 65535) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"max", 65535});
    }
    if (min < 1) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", 1});
    }
    if (max < min) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", 1});
    }
    this.min = min;
    this.max = max;
  }

  @Override
  public String mock() {
    return email(min, max, suffixArray);
  }
}
