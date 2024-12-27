package cloud.xcan.comp.jmock.core.function.basic;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_BASIC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MREGEXP_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MREGEXP_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MREGEXP_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MREGEXP_PARAMETER_REGEXP;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_NULL_WEIGHT;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_UNACCEPTABLE_T;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_WEIGHT_T;
import static cloud.xcan.comp.jmock.core.support.utils.RandomStringUtils.randomRegexp;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.calcNullWeight;
import static cloud.xcan.comp.jmock.core.support.utils.StringToTypeUtils.isNullWeight;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author xiaolong.liu
 * @see `https://github.com/stuartwdouglasmidstream/github--mifmif--Generex`
 * @see `/workspace/workspace_3rd/mock/github--mifmif--Generex`
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MREGEXP_DESC,
    categoryI18nKey = {DOC_CATEGORY_BASIC}, order = 110)
public class MRegExp extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MREGEXP_PARAMETER_REGEXP)
  private String regexp;

  @JMockParameter(descI18nKey = DOC_PARAMETER_NULL_WEIGHT)
  private double nullWeight;

  private static final String DEFAULT_REGEXP_VALUE = "[a-z][a-z][0-9]";

  public MRegExp() {
  }

  @JMockConstructor(descI18nKey = DOC_MREGEXP_C1,
      example = "@RegExp([a-z][a-z][0-9])",
      exampleValues = {"kK8", "Cs9"})
  public MRegExp(String regexp) {
    this(regexp, null);
  }

  @JMockConstructor(descI18nKey = DOC_MREGEXP_C2,
      example = "@RegExp([a-z][a-z][0-9],1:2)",
      exampleValues = {"kK8", "null"})
  public MRegExp(String regexp, String nullWeight) {
    if (StringUtils.isBlank(regexp)) {
      ParamParseException.throw0(FPARAM_UNACCEPTABLE_T, new Object[]{"regexp"});
    }
    this.regexp = regexp;
    if (null != nullWeight && !nullWeight.isEmpty()) {
      if (!isNullWeight(nullWeight)) {
        ParamParseException.throw0(FPARAM_WEIGHT_T, new Object[]{"nullWeight"});
      }
      this.nullWeight = calcNullWeight(nullWeight);
    }
  }

  @Override
  public String mock() {
    return randomRegexp(regexp, nullWeight);
  }
}
