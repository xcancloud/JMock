package cloud.xcan.jmock.plugin;

import static cloud.xcan.jmock.api.i18n.JMockMessage.PARAM_UNACCEPTABLE_T;
import static cloud.xcan.jmock.api.support.utils.IPUtils.randomUrl;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_CATEGORY_NETWORK;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_C1;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_C2;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_C3;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_DESC;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_PARAMETER_ALLOW_QUERY;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_PARAMETER_DOMAIN;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_PARAMETER_MAX;
import static cloud.xcan.jmock.plugin.NetworkDocMessage.DOC_URL_PARAMETER_PROTOCOL;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import cloud.xcan.jmock.api.i18n.MessageResources;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Bao Zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_URL_DESC,
    categoryI18nKey = {DOC_CATEGORY_NETWORK}, order = 908)
public class MUrl extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_URL_PARAMETER_MAX)
  private Integer max;

  @JMockParameter(descI18nKey = DOC_URL_PARAMETER_PROTOCOL)
  private String protocol;

  @JMockParameter(descI18nKey = DOC_URL_PARAMETER_DOMAIN)
  private String domain;

  @JMockParameter(descI18nKey = DOC_URL_PARAMETER_ALLOW_QUERY)
  private boolean allowQueryParams;

  private transient String[] dictArray;

  final static Integer DEFAULT_MAX_VALUE = 50;
  final static String DEFAULT_PROTOCOL_VALUE = "http";

  final static String DEFAULT_DOMAIN_VALUE = "127.0.0.1:8080";

  final static String DEFAULT_URL_DICT = MessageResources.getString(
      NetworkDocMessage.DATA_URL_PARAM);

  @JMockConstructor(descI18nKey = DOC_URL_C1,
      example = "@Url()",
      exampleValues = {"http://127.0.0.1:8080/P1ZXOF9uI0/", "http://127.0.0.1:8080/Dcm"})
  public MUrl() {
    this(DEFAULT_MAX_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_URL_C2,
      example = "@Url(true)",
      exampleValues = {"http://www.xcan.org:8080/aacj/uics?acc=67&ubc7=8jFc",
          "http://www.xcan.org:8080/aacj/uics?name=8jkc&gggbssiu=78hbss"})
  public MUrl(Integer max) {
    this(max, DEFAULT_PROTOCOL_VALUE, DEFAULT_DOMAIN_VALUE, false);
  }

  @JMockConstructor(descI18nKey = DOC_URL_C3,
      example = "@Url(https,www.xcan.org,user|name,true)",
      exampleValues = {"https://www.xcan.org:8080/aacj/uics?user=67&name=8jFc",
          "https://www.xcan.org:8080/aacj/uics?name=8jkc&user=78hbss"})
  public MUrl(Integer max, String protocol, String domain, boolean allowQueryParams) {
    if (!StringUtils.equalsAny(protocol, "http", "https")) {
      ParamParseException.throw0(PARAM_UNACCEPTABLE_T, new Object[]{"protocol"});
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
