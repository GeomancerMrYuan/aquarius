package com.ziroom.aquarius.common.annotation;

import com.ziroom.aquarius.common.enums.MethodType;

import java.lang.annotation.*;


@Documented   //注解是否将包含在 JavaDoc 中；
@Retention(RetentionPolicy.RUNTIME)   //什么时候使用该注解，我们定义为运行时；
@Target(ElementType.METHOD)  //注解用于什么地方，我们定义为作用于方法上；
public @interface LogAnnotation {
    // 日志是否存入数据库
    boolean intoDB() default false;

    // 操作类型, 默认是查询
    MethodType methodType() default MethodType.SELECT;

    // 接口描述
    String description() default "";

}
