package cloud.xcan.jmock.core.function.web;

import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_WEB;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOLOR_C1;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOLOR_C2;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOLOR_DESC;
import static cloud.xcan.jmock.api.i18n.JMockFuncDocMessage.DOC_MCOLOR_PARAMETER_FORMAT;
import static cloud.xcan.angus.spec.utils.ObjectUtils.nullSafe;

import cloud.xcan.jmock.api.AbstractMockFunction;
import cloud.xcan.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.jmock.core.support.utils.ColorUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MCOLOR_DESC,
    categoryI18nKey = {DOC_CATEGORY_WEB}, order = 1002)
public class MColor extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MCOLOR_PARAMETER_FORMAT)
  private String format;

  private static final String[] FORMAT = {"rgb", "hsl", "hwb", "lch", "cmyk"};

  @JMockConstructor(descI18nKey = DOC_MCOLOR_C1,
      example = "@Color()",
      exampleValues = {"rgb(88, 245, 14)", "rgb(97, 69, 216)"})
  public MColor() {
    this(FORMAT[0]);
  }

  @JMockConstructor(descI18nKey = DOC_MCOLOR_C2,
      example = "@Color(hwb)",
      exampleValues = {"hwb(108, 67%, 45%)", "hwb(133, 2%, 93%)"})
  public MColor(String format) {
    this.format = nullSafe(format, FORMAT[0]);
  }

  @Override
  public Object mock() {
    if (FORMAT[0].equalsIgnoreCase(format)) {
      return ColorUtil.randomRgbColor();
    } else if (FORMAT[1].equalsIgnoreCase(format)) {
      return ColorUtil.randomHslColor();
    } else if (FORMAT[2].equalsIgnoreCase(format)) {
      return ColorUtil.randomHwbColor();
    } else if (FORMAT[3].equalsIgnoreCase(format)) {
      return ColorUtil.randomLchColor();
    } else if (FORMAT[4].equalsIgnoreCase(format)) {
      return ColorUtil.randomCmykColor();
    }
    return ColorUtil.randomRgbColor();
  }
}
