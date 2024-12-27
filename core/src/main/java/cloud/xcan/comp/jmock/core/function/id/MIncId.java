package cloud.xcan.comp.jmock.core.function.id;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_ID;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MINCID_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MINCID_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MINCID_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MINCID_PARAMETER_INIT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MINCID_PARAMETER_STEP;
import static cloud.xcan.comp.jmock.api.i18n.JMockMessage.FPARAM_MIN_T;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.api.exception.ParamParseException;
import java.util.concurrent.atomic.LongAdder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hao.guo
 * @author xiaolong.liu
*/
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MINCID_DESC,
    categoryI18nKey = {DOC_CATEGORY_ID}, order = 303)
public class MIncId extends AbstractMockFunction {

  @JMockParameter(descI18nKey = DOC_MINCID_PARAMETER_INIT)
  private Integer init;

  @JMockParameter(descI18nKey = DOC_MINCID_PARAMETER_STEP)
  private int step;

  private LongAdder longAdder = new LongAdder();

  final static int DEFAULT_INIT_VALUE = 1;

  final static int DEFAULT_STEP_VALUE = 1;

  @JMockConstructor(descI18nKey = DOC_MINCID_C1,
      example = "@IncId()",
      exampleValues = {"1"})
  public MIncId() {
    this(DEFAULT_INIT_VALUE, DEFAULT_STEP_VALUE);
  }

  @JMockConstructor(descI18nKey = DOC_MINCID_C2,
      example = "@IncId(5,2)",
      exampleValues = {"5"})
  public MIncId(Integer init, Integer step) {
    if (step != null && step < DEFAULT_STEP_VALUE) {
      ParamParseException.throw0(FPARAM_MIN_T, new Object[]{"step", DEFAULT_STEP_VALUE});
    }
    this.step = step == null ? DEFAULT_STEP_VALUE : step;
    this.longAdder.add(init == null ? DEFAULT_INIT_VALUE : init);
  }

  @Override
  public synchronized Long mock() {
    long value = longAdder.longValue();
    longAdder.add(step);
    return value;
  }
}
