package lv.nixx.poc.exh.handler.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RequestDescriptor {

    String action() default "";
    String entity() default "";

}
