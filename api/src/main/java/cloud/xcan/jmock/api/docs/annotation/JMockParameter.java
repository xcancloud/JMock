package cloud.xcan.jmock.api.docs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author XiaoLong Liu
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JMockParameter {

  /**
   * MockParameter description i18n resource configuration key.
   */
  String descI18nKey();

  /**
   * The type of the parameter (for documentation).
   */
  String type() default "";

  /**
   * Default value if the parameter is not provided.
   */
  String defaultValue() default "";

  /**
   * Whether this parameter is required.
   */
  boolean required() default false;

}

}
