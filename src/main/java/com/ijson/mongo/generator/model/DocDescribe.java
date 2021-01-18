package com.ijson.mongo.generator.model;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DocDescribe {
    String value() default "";
}
