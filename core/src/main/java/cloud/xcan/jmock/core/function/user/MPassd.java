package cloud.xcan.jmock.core.function.user;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_C3;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_C4;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_PARAMETER_ALLOW_DIGITS;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_PARAMETER_ALLOW_LOWERCASE;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_PARAMETER_ALLOW_SPECIALCHAR;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_PARAMETER_ALLOW_UPPERCASE;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_PARAMETER_MAX;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MPASSD_PARAMETER_MIN;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MAX_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;
import static cloud.xcan.jmock.api.i18n.JMockMessage.PARSER_FUNC_PARAM_ILLEGAL;
import static cloud.xcan.jmock.core.support.utils.RandomStringUtils.randomPassd;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.api.exception.ParamParseException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MPASSD_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 802)
public class MPassd extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MPASSD_PARAMETER_MIN)
  private int min;

  @JMockParameter(descI18nKey = DOC_MPASSD_PARAMETER_MAX)
  private int max;

  @JMockParameter(descI18nKey = DOC_MPASSD_PARAMETER_ALLOW_UPPERCASE)
  private boolean allowUpperCase;

  @JMockParameter(descI18nKey = DOC_MPASSD_PARAMETER_ALLOW_LOWERCASE)
  private boolean allowLowerCase;

  @JMockParameter(descI18nKey = DOC_MPASSD_PARAMETER_ALLOW_DIGITS)
  private boolean allowDigits;

  @JMockParameter(descI18nKey = DOC_MPASSD_PARAMETER_ALLOW_SPECIALCHAR)
  private boolean allowSpecialChar;

  private transient char[] chars;

  final static int DEFAULT_MIN_VALUE = 6;

  final static int DEFAULT_MAX_VALUE = 20;

  final static char[] DEFAULT_UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

  final static char[] DEFAULT_LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

  final static char[] DEFAULT_NUM_CHARS = "1234567890".toCharArray();

  final static char[] DEFAULT_SPECIAL_CHARS = "`-=[];',./~!@#$%^&\\*()\\_+{}:\"<>?".toCharArray();

  @JMockConstructor(descI18nKey = DOC_MPASSD_C1,
      example = "@Passd()",
      exampleValues = {"PKA1BGmN", "iD5p27p2w"})
  public MPassd() {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, true, true, true, false);
  }

  @JMockConstructor(descI18nKey = DOC_MPASSD_C2,
      example = "@Passd(5,8)",
      exampleValues = {"llbxB6Z", "qLQlgRz"})
  public MPassd(int min, int max) {
    this(min, max, true, true, true, false);
  }

  @JMockConstructor(descI18nKey = DOC_MPASSD_C3,
      example = "@Passd(true,true,true,true)",
      exampleValues = {"H/70~{E:nmB%e", "'KIJbW2DP`\\<s3b+ze"})
  public MPassd(boolean allowUpperCase, boolean allowLowerCase,
      boolean allowDigits, boolean allowSpecialChar) {
    this(DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE, allowUpperCase, allowLowerCase, allowDigits,
        allowSpecialChar);
  }

  @JMockConstructor(descI18nKey = DOC_MPASSD_C4,
      example = "@Passd(5,8,true,true,true,true)",
      exampleValues = {"H-+:6", "@7^KQf8"})
  public MPassd(int min, int max, boolean allowUpperCase, boolean allowLowerCase,
      boolean allowDigits, boolean allowSpecialChar) {
    this.min = min;
    this.max = max;
    if (max > 65535) {
      ParamParseException.throw0(FPARAM_MAX_T, new Object[]{"max", 65535});
    }
    if (min < 1) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", 1});
    }
    if (max < min) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"min", 1});
    }
    if (!allowUpperCase && !allowLowerCase && !allowDigits && !allowSpecialChar) {
      ParamParseException.throw0(PARSER_FUNC_PARAM_ILLEGAL);
    }
    StringBuilder sb = new StringBuilder(100);
    if (allowUpperCase) {
      sb.append(DEFAULT_UPPERCASE_CHARS);
      chars = sb.toString().toCharArray();
    }
    if (allowLowerCase) {
      sb.append(DEFAULT_LOWERCASE_CHARS);
      chars = sb.toString().toCharArray();
    }
    if (allowDigits) {
      sb.append(DEFAULT_NUM_CHARS);
      chars = sb.toString().toCharArray();
    }
    if (allowSpecialChar) {
      sb.append(DEFAULT_SPECIAL_CHARS);
      chars = sb.toString().toCharArray();
    }
  }

  @Override
  public String mock() {
    return randomPassd(min, max, chars);
  }
}
