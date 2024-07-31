package kr.co.codefarm.svcm.commons.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
//@Async
public @interface AlarmRelay {
	
	AlarmType type() default AlarmType.COLLECTOR;

}
