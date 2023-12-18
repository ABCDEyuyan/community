package com.zl.community.annotation;

import java.lang.annotation.*;

/**
 * @Author : ZL
 * 操作日志注解
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
//注解，目标在方法上
@Target(ElementType.METHOD)
public @interface LogOperation {
    /** 直接使用value那么使用注解的时候可以不用写value= */
    String value() default "";
}
