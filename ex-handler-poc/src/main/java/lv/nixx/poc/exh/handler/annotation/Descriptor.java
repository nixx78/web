package lv.nixx.poc.exh.handler.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

@Retention(RetentionPolicy.RUNTIME)
@Target({METHOD, TYPE})
public @interface Descriptor {

    String action() default "";
    String entity() default "";
    String description() default "";

}
