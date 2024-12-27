package cloud.xcan.comp.jmock.api.docs.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author xiaolong.liu
 */
@Target({ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JMockConstructor {

  /**
   * MockConstructor description i18n resource configuration key.
   */
  String descI18nKey() default "";

  /**
   * Example of constructor.
   */
  String example() default "";

  /**
   * MockConstructor or parameter example values.
   */
  String[] exampleValues() default {};

}
