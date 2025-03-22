package cloud.xcan.comp.jmock.core.function.date;

import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_CATEGORY_DATE;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_C1;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_C2;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_C3;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_C4;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_DESC;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_PARAMETER_FORMAT;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_PARAMETER_RANDOM;
import static cloud.xcan.comp.jmock.api.i18n.JMockFuncDocMessage.DOC_MLOCALE_DATE_PARAMETER_ZONEID;
import static cloud.xcan.sdf.spec.utils.ObjectUtils.nullSafe;

import cloud.xcan.comp.jmock.api.AbstractMockFunction;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockConstructor;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockFunctionRegister;
import cloud.xcan.comp.jmock.api.docs.annotation.JMockParameter;
import cloud.xcan.comp.jmock.core.support.utils.RandomUtils;
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
@JMockFunctionRegister(descI18nKey = DOC_MLOCALE_DATE_DESC,
    categoryI18nKey = {DOC_CATEGORY_DATE}, order = 203)
public class MLocaleDate extends AbstractMockFunction {

  public static final String DEFAULT_FORMAT = "yyyy-MM-dd";
  public static final String DEFAULT_ZONE_ID = "Asia/Shanghai";

  public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern(
      DEFAULT_FORMAT);

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_PARAMETER_FORMAT)
  private String format;

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_PARAMETER_ZONEID)
  private ZoneId zoneId;

  @JMockParameter(descI18nKey = DOC_MLOCALE_DATE_PARAMETER_RANDOM)
  private Boolean random;

  private DateTimeFormatter df = DEFAULT_DATE_FORMAT;

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_C1,
      example = "@LocaleDate()",
      exampleValues = {"2022-01-01", "2022-01-01"})
  public MLocaleDate() {
    this(DEFAULT_FORMAT, DEFAULT_ZONE_ID, false);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_C2,
      example = "@LocaleDate(yyyy yy y MM M dd d)",
      exampleValues = {"2022 22 2022 05 5 12 12", "2022 22 2022 05 5 12 12"})
  public MLocaleDate(String format) {
    this(format, DEFAULT_ZONE_ID, false);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_C3,
      example = "@LocaleDate(yyyy yy y MM M dd d)",
      exampleValues = {"2022 22 2022 05 5 12 12", "2022 22 2022 05 5 12 12"})
  public MLocaleDate(String format, Boolean random) {
    this(format, DEFAULT_ZONE_ID, random);
  }

  @JMockConstructor(descI18nKey = DOC_MLOCALE_DATE_C4,
      example = "@LocaleDate(yyyy-MM-dd,Asia/Shanghai)",
      exampleValues = {"2022-01-01", "2022-01-01"})
  public MLocaleDate(String format, String zoneId, Boolean random) {
    if (!StringUtils.equals(format, DEFAULT_FORMAT)) {
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
      boolean isFuture = System.currentTimeMillis() % 2 == 0;
      return isFuture ? df.format(LocalDateTime.now(zoneId).plusDays(RandomUtils.nextInt(0, 3650)))
          : df.format(LocalDateTime.now(zoneId).minusDays(RandomUtils.nextInt(0, 3650)));
    }
  }
}
