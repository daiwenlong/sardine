package pers.dwl.sardine.ioc.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 标识需要注入的属性
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(FIELD)
public @interface Inject {
	
	Class<?> value() default String.class;

}
