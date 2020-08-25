package com.ziroom.aquarius.common.aspect;

import cn.hutool.core.util.IdUtil;
import com.google.gson.Gson;
import com.ziroom.aquarius.common.annotation.LogAnnotation;
import com.ziroom.aquarius.system.service.IWebLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

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
     * 换行符
     */
    private static final String LINE_SEPARATOR = System.lineSeparator();
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IWebLogService webLogService;

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
        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
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
        log.info("=========================================== End ===========================================" + LINE_SEPARATOR);

    }

    /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    if(method.getAnnotation(LogAnnotation.class)!=null){
                        description.append(method.getAnnotation(LogAnnotation.class).description());
                    }
                    break;
                }
            }
        }
        return description.toString();
    }
}
