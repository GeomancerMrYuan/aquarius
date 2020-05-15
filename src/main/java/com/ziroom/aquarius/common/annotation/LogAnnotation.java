package com.ziroom.aquarius.common.annotation;

import com.ziroom.aquarius.common.enums.MethodType;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogAnnotation {
    // 日志是否存入数据库
    boolean intoDB() default false;
    
    // 操作类型, 默认是查询
    MethodType methodType() default MethodType.SELECT;

    // 接口描述
    String description() default "";

}