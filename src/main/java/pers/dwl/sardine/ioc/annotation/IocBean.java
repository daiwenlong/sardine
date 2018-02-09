package pers.dwl.sardine.ioc.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 定义bean类 -标识该类交给IOC管理
 * @author dwl
 *
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
public @interface IocBean {

}
