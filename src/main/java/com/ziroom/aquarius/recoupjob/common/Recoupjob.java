package com.ziroom.aquarius.recoupjob.common;

import com.ziroom.aquarius.common.enums.MethodType;

import java.lang.annotation.*;


@Documented   //注解是否将包含在 JavaDoc 中；
@Retention(RetentionPolicy.RUNTIME)   //什么时候使用该注解，我们定义为运行时；
@Target(ElementType.METHOD)  //注解用于什么地方，我们定义为作用于方法上；
public @interface Recoupjob {
    // 接口描述
    String jobDesc() default "";

}
