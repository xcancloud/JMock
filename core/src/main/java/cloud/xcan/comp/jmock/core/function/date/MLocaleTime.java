package cloud.xcan.comp.jmock.core.function.date;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_DATE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_PARAMETER_ZONEID;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_PARAMETER_FORMAT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_TIME_PARAMETER_RANDOM;
import static cloud.xcan.sdf.spec.utils.ObjectUtils.nullSafe;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * @author bao.zhang
 * @author xiaolong.liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MLOCALE_TIME_DESC,
    categoryI18nKey = {DOC_CATEGORY_DATE}, order = 202)
public class MLocaleTime extends AbstractMockFunction {

  public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
  public static final String DEFAULT_ZONE_ID = "Asia/Shanghai";

  public static final DateTimeFormatter DATE_DEFAULT_FORMAT = DateTimeFormatter.ofPattern(
      DEFAULT_TIME_FORMAT);

  @JMockParameter(descI18nKey = DOC_MLOCALE_TIME_PARAMETER_FORMAT)
  private String format;

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_PARAMETER_ZONEID)
  private ZoneId zoneId;

  @JMockParameter(descI18nKey = DOC_MLOCALE_TIME_PARAMETER_RANDOM)
  private Boolean random;

  public DateTimeFormatter df = DATE_DEFAULT_FORMAT;

  @JMockConstructor(descI18nKey = DOC_MLOCALE_TIME_C1,
      example = "@LocaleTime()",
      exampleValues = {"23:32:13", "23:32:13"})
  public MLocaleTime() {
    this(DEFAULT_TIME_FORMAT, DEFAULT_ZONE_ID, false);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_TIME_C2,
      example = "@LocaleTime(a HH:mm:ss)",
      exampleValues = {"下午 23:34:25", "下午 23:34:25"})
  public MLocaleTime(String format) {
    this(format, DEFAULT_ZONE_ID, false);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_TIME_C3,
      example = "@LocaleTime(a HH:mm:ss)",
      exampleValues = {"下午 23:34:25", "下午 23:34:25"})
  public MLocaleTime(String format, Boolean random) {
    this(format, DEFAULT_ZONE_ID, random);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_TIME_C4,
      example = "@LocaleDate(HH:mm:ss,Asia/Tokyo)",
      exampleValues = {"07:08:21", "07:08:21"})
  public MLocaleTime(String format, String zoneId, Boolean random) {
    if (!StringUtils.equals(format, DEFAULT_TIME_FORMAT)) {
      df = DateTimeFormatter.ofPattern(format);
    }
    this.zoneId = ZoneId.of(zoneId);
    this.random = nullSafe(random, false);
  }

  @Override
  public String mock() {
    if (!random) {
      return df.format(LocalDateTime.now(zoneId));
    } else {
      return df.format(LocalDateTime.now(zoneId).plusSeconds(24 * 60 * 60));
    }
  }
}
