package cloud.xcan.jmock.api.docs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author XiaoLong Liu
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JMockFunctionRegister {

  /**
   * Function category i18n resource configuration key for group and search.
   * <p>
   * Available values：BASIC, ID, USER, LOCATION, APP, IT, CAR, CRYPTO, GEO, COMMERCE ...
   * <p>
   * Usage：classification=PERSONAL,LOCATION
   */
  String[] categoryI18nKey();

  /**
   * Function description i18n resource configuration key.
   */
  String descI18nKey();

  /**
   * The order of control functions in the api or web interface, smaller and higher.
   */
  int order() default 10000;

}
