package cloud.xcan.comp.jmock.core.function.network;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_PARAMETER_ALLOW_QUERY;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_PARAMETER_DOMAIN;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_PARAMETER_MAX;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MURL_PARAMETER_PROTOCOL;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_UNACCEPTABLE_T;
import static cloud.xcan.comp.jmock.core.support.utils.IPUtils.randomUrl;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import cloud.xcan.comp.jmock.api.i18n.JMockMessage;
import cloud.xcan.comp.jmock.api.i18n.MessageResources;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MURL_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 908)
public class MUrl extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MURL_PARAMETER_MAX)
  private Integer max;

  @JMockParameter(descI18nKey = DOC_MURL_PARAMETER_PROTOCOL)
  private String protocol;

  @JMockParameter(descI18nKey = DOC_MURL_PARAMETER_DOMAIN)
  private String domain;

  @JMockParameter(descI18nKey = DOC_MURL_PARAMETER_ALLOW_QUERY)
  private boolean allowQueryParams;

  private transient String[] dictArray;

  final static Integer DEFAULT_MAX_VALUE = 50;
  final static String DEFAULT_PROTOCOL_VALUE = "http";

  final static String DEFAULT_DOMAIN_VALUE = "127.0.0.1:8080";

  final static String DEFAULT_URL_DICT = MessageResources.getString(JMockMessage.FDATA_URL_PARAM);

  @JMockConstructor(descI18nKey = DOC_MURL_C1,
      example = "@Url()",
      exampleValues = {"http://127.0.0.1:8080/P1ZXOF9uI0/", "http://127.0.0.1:8080/Dcm"})
  public MUrl() {
    this(DEFAULT_MAX_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MURL_C2,
      example = "@Url(true)",
      exampleValues = {"http://www.xcan.org:8080/aacj/uics?acc=67&ubc7=8jFc",
          "http://www.xcan.org:8080/aacj/uics?name=8jkc&gggbssiu=78hbss"})
  public MUrl(Integer max) {
    this(max, DEFAULT_PROTOCOL_VALUE, DEFAULT_DOMAIN_VALUE, false);
  }

  @JMockConstructor(descI18nKey = DOC_MURL_C3,
      example = "@Url(https,www.xcan.org,user|name,true)",
      exampleValues = {"https://www.xcan.org:8080/aacj/uics?user=67&name=8jFc",
          "https://www.xcan.org:8080/aacj/uics?name=8jkc&user=78hbss"})
  public MUrl(Integer max, String protocol, String domain, boolean allowQueryParams) {
    if (!StringUtils.equalsAny(protocol, "http", "https")) {
      ParamParseException.throw0(FPARAM_UNACCEPTABLE_T, new Object[]{"protocol"});
    }
    this.max = max;
    this.protocol = protocol;
    this.domain = domain;
    this.allowQueryParams = allowQueryParams;
    this.dictArray = DEFAULT_URL_DICT.split("\\|");
  }

  @Override
  public String mock() {
    return randomUrl(max, protocol, domain, dictArray, allowQueryParams);
  }
}
