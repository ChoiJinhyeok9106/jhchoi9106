package kr.co.codefarm.svcm.commons.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(ElementType.PARAMETER)
public @interface ParameterUsage {
	
	ParameterType type() default ParameterType.NORMAL;

}
