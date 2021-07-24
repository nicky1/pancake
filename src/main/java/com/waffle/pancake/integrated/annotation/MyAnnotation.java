package com.waffle.pancake.integrated.annotation;

import java.lang.annotation.*;

/**
 * @author yixiaoshuang
 * @date 2021/3/16 21:08
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String name() default "zhangsan";
}


class TestAnn {

    @MyAnnotation(name = "lisi")
    private String name;
}