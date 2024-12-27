package cloud.xcan.comp.jmock.core.function.user;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_USER;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMOBILE_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMOBILE_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MMOBILE_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_PARAMETER_LOCALE;
import static cloud.xcan.comp.jmock.core.support.utils.RandomStringUtils.randomNum;
import static java.util.Locale.CHINA;
import static java.util.Locale.ENGLISH;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
import java.util.Locale;
import lombok.Getter;
import lombok.Setter;

/**
 * @author bao.zhang
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MMOBILE_DESC,
    categoryI18nKey = {DOC_CATEGORY_USER}, order = 805)
public class MMobile extends AbstractMockFunction {

  public static final String EN_DEFAULT_PRE_CODE = "07";
  public static final String[] DEFAULT_PRE_NUM = new String[]{"134", "135", "136", "137", "138",
      "139", "147", "150", "151", "152", "157", "158", "159", "178", "182", "183", "184", "187",
      "188", "198", "130", "131", "132", "155", "156", "166", "185", "186", "145", "176", "133",
      "153", "177", "173", "180", "181", "189", "199", "165", "170", "171"};
  public static final int EN_DEFAULT_NUM = 9;
  public static final int CN_DEFAULT_NUM = 8;

  private int num;

  @JMockParameter(descI18nKey = DOC_PARAMETER_LOCALE)
  private Locale locale;

  private String prefix;

  private String[] dictArray;

  @JMockConstructor(descI18nKey = DOC_MMOBILE_C1,
      example = "@Mobile()",
      exampleValues = {"13293932848", "18764113305"})
  public MMobile() {
    this(CHINA);
  }

  @JMockConstructor(descI18nKey = DOC_MMOBILE_C2,
      example = "@Mobile(en)",
      exampleValues = {"07790421505", "07866557530"})
  public MMobile(Locale locale) {
    this.locale = locale;
    if (CHINA.equals(locale)) {
      this.num = CN_DEFAULT_NUM;
    } else if (ENGLISH.equals(locale)) {
      prefix = EN_DEFAULT_PRE_CODE;
      num = EN_DEFAULT_NUM;
    }
  }

  @Override
  public String mock() {
    if (CHINA.equals(locale)) {
      Integer length = RandomUtils.nextInt(0, DEFAULT_PRE_NUM.length);
      prefix = DEFAULT_PRE_NUM[length];
    }
    return prefix + randomNum(this.num);
  }
}
