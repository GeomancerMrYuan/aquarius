package com.ziroom.aquarius.common.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 配置日志切面--适用于自定义的日志:如日志模块往数据库中新增用户操作记录
 * @author yuanpeng
 * @Date Created in 2020年04月21日 18:54
 * @version 1.0
 * @since 1.0
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 配置切点
     */
    @Pointcut("execution(* com.ziroom.aquarius.system.controller.*.*(..))")
    public void logPoint(){

    }
    /**
     * 配置前置通知
     */
    public void logBefore() {

    }
}
