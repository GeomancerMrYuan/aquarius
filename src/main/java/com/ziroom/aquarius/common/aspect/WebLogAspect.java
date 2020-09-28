package com.ziroom.aquarius.common.aspect;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 配置日志切面--适用于自定义的日志:如日志模块往数据库中新增用户操作记录
 *
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月21日 18:54
 * @since 1.0
 */
@Aspect
@Component
@Order(1)
@Profile({"dev", "test"})
@Slf4j
public class WebLogAspect {
    /**
     *切点
     */
    @Pointcut("execution(public * com.ziroom.aquarius.system.controller.*.*(..))")
    public void webLog() {

    }

    /**
     * @Description 打印ip及入参
     * @Date 2020-05-15 12:29
     * @Created by yuanpeng
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        //设置线程uid
        Thread.currentThread().setName("request_uuid:" + IdUtil.randomUUID() + "_" + Thread.currentThread().getId());
        //打印请求日志,IP/URL/Method和参数
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", new Gson().toJson(joinPoint.getArgs()));
    }

    /**
     * @Description 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点；
     * @Date 2020-05-15 12:29
     * @Created by yuanpeng
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        // 打印出参
        log.info("Response Args  : {}", new Gson().toJson(result));
        // 执行耗时
        log.info("Time-Consuming : {} ms", System.currentTimeMillis() - startTime);
        return result;
    }

    /**
     * @Description 打印返回结果
     * @Date 2020-05-15 12:29
     * @Created by yuanpeng
     */
    @AfterReturning("webLog()")
    public void doAfterReturning() throws Throwable {
        // 接口结束后换行，方便分割查看
        log.info("=========================================== End ===========================================" + System.lineSeparator());

    }
}
