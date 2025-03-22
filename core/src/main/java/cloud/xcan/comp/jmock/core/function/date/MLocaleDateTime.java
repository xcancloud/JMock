package cloud.xcan.comp.jmock.core.function.date;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_DATE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_PARAMETER_ZONEID;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_PARAMETER_FORMAT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_TIME_PARAMETER_RANDOM;
import static cloud.xcan.comp.jmock.core.support.utils.RandomUtils.nextInt;
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
 * @author XiaoLong Liu
 */
@Setter
@Getter
@JMockFunctionRegister(descI18nKey = DOC_MLOCALE_DATE_TIME_DESC,
    categoryI18nKey = {DOC_CATEGORY_DATE}, order = 204)
public class MLocaleDateTime extends AbstractMockFunction {

  private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final String DEFAULT_ZONE_ID = "Asia/Shanghai";

  static final DateTimeFormatter DATE_DEFAULT_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_FORMAT);

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_TIME_PARAMETER_FORMAT)
  private String format;

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_PARAMETER_ZONEID)
  private ZoneId zoneId;

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_TIME_PARAMETER_RANDOM)
  private Boolean random;

  private DateTimeFormatter df = DATE_DEFAULT_FORMAT;

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_TIME_C1,
      example = "@LocaleDateTime()",
      exampleValues = {"2022-01-01 23:34:25", "2022-01-01 23:34:25"})
  public MLocaleDateTime() {
    this(DEFAULT_FORMAT, DEFAULT_ZONE_ID, false);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_TIME_C2,
      example = "@LocaleDateTime(yy-MM-dd a HH:mm:ss)",
      exampleValues = {"22-05-12 下午 23:40:22", "22-05-12 下午 23:40:22"})
  public MLocaleDateTime(String format) {
    this(format, DEFAULT_ZONE_ID, false);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_TIME_C3,
      example = "@LocaleDateTime(yy-MM-dd a HH:mm:ss)",
      exampleValues = {"22-05-12 下午 23:40:22", "22-05-12 下午 23:40:22"})
  public MLocaleDateTime(String format, Boolean random) {
    this(format, DEFAULT_ZONE_ID, random);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_TIME_C4,
      example = "@LocaleDateTime(yyyy-MM-dd HH:mm:ss,Asia/Shanghai)",
      exampleValues = {"2022-01-01 23:34:25", "2022-01-01 23:34:25"})
  public MLocaleDateTime(String format, String zoneId, Boolean random) {
    if (!StringUtils.equals(format, DEFAULT_FORMAT)) {
      df = DateTimeFormatter.ofPattern(format);
    }
    this.zoneId = ZoneId.of(zoneId);
    this.random = nullSafe(random, false);
  }

  @Override
  public String mock() {
    if (!random) {
      return df.format(LocalDateTime.now(this.zoneId));
    } else {
      boolean isFuture = System.currentTimeMillis() % 2 == 0;
      return isFuture ? df.format(LocalDateTime.now(zoneId)
          .plusDays(nextInt(0, 10 * 365)).plusSeconds(24 * 60 * 60))
          : df.format(LocalDateTime.now(zoneId)
              .minusDays(nextInt(0, 10 * 365)).plusSeconds(24 * 60 * 60));
    }
  }
}
