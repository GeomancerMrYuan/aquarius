package com.ziroom.aquarius.common.aspect;

import com.alibaba.fastjson.JSON;
import com.ziroom.aquarius.common.annotation.LogAnnotation;
import com.ziroom.aquarius.common.util.IpUtil;
import com.ziroom.aquarius.common.util.UUIDUtil;
import com.ziroom.aquarius.system.entity.WebLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
public class WebLogAspect {

    @Autowired
    private HttpServletRequest request;


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
        request.setAttribute("startTime", System.currentTimeMillis());
        //设置线程uid
        Thread.currentThread().setName("request_uuid:" + UUIDUtil.UUID32() + "_" + Thread.currentThread().getId());
        //打印请求日志,IP/URL/Method和参数
        log.info("请求地址:{},IP:{},method:{}", request.getRequestURL().toString(), IpUtil.getIpAddress(request), request.getMethod());
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        log.info("请求入参:{}", JSON.toJSONString(getParameter(method, joinPoint.getArgs())));
    }

    /**
     * @Description 打印返回结果
     * @Date 2020-05-15 12:29
     * @Created by yuanpeng
     */
    @AfterReturning(value = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        long startTime = (long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.info("返回结果:{}", JSON.toJSONString(ret));
        log.info("接口耗时:{}ms", endTime - startTime);

    }

    /**
     * @Description 含有LogAnnotation注解的请求, 添加请求日志到数据库
     * @Date 2020-05-15 12:29
     * @Created by yuanpeng
     */
    @Around("webLog() && @annotation(logAnnotation)")
    public Object doAround(ProceedingJoinPoint joinPoint, LogAnnotation logAnnotation) throws Throwable {
        //获取请求参数
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        //打印controller耗时,及返回结果
        Object result = joinPoint.proceed();

        //记录请求信息存储的数据库
        if (logAnnotation.intoDB()) {
            WebLog webLog = new WebLog();
            webLog.setBasePath(request.getRequestURI());
            webLog.setIp(request.getRemoteUser());
            webLog.setParameter(getParameter(method, joinPoint.getArgs()));
            webLog.setResult(result);
            webLog.setMethod(logAnnotation.methodType().getName());
            webLog.setDescription(logAnnotation.description());
            webLog.setUsername("可动态指定");
        }
        return result;
    }

    /**
     * 根据方法和传入的参数获取请求参数
     */
    private Object getParameter(Method method, Object[] args) {
        List<Object> argList = new ArrayList<>();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            //将RequestBody注解修饰的参数作为请求参数
            RequestBody requestBody = parameters[i].getAnnotation(RequestBody.class);
            if (requestBody != null) {
                argList.add(args[i]);
            }
            //将RequestParam注解修饰的参数作为请求参数
            RequestParam requestParam = parameters[i].getAnnotation(RequestParam.class);
            if (requestParam != null) {
                Map<String, Object> map = new HashMap<>();
                String key = parameters[i].getName();
                if (!StringUtils.isEmpty(requestParam.value())) {
                    key = requestParam.value();
                }
                map.put(key, args[i]);
                argList.add(map);
            }
        }
        if (argList.size() == 0) {
            return null;
        } else if (argList.size() == 1) {
            return argList.get(0);
        } else {
            return argList;
        }
    }
}
