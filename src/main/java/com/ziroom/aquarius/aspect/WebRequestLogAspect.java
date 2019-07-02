package com.ziroom.aquarius.aspect;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月02日 12:10
 * @since 1.0
 */
@Aspect
@Component
public class WebRequestLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebRequestLogAspect.class);


    @Pointcut("execution(public * com.ziroom.aquarius.controller..*.*(..))")
    public void webRequestLog() {}


    @Around("webRequestLog()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        try {
            ServletRequestAttributes ra = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = null;
            String ip="";
            if (ra != null) {
                request = ra.getRequest();
                ip = getIpAddr(request);
            }
            String className = pjp.getTarget().getClass().getSimpleName();
            String method = pjp.getSignature().getName();
            Object[] args = pjp.getArgs();
            String clzMethod = className + "." + method;
            logger.info("method {} start, args: {},IP:{}", clzMethod, args,ip);
            long start = System.currentTimeMillis();
            // result的值就是被拦截方法的返回值
            Object result = pjp.proceed();
            long end = System.currentTimeMillis();
            logger.info("method {} end, cost: {}ms,reponse:{}", clzMethod, (end - start), JSONObject.toJSONString(result));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("日志打印异常", e);
            return pjp.proceed();
        }
    }

    /**
     * 获取登录用户远程主机ip地址
     *
     * @param request
     * @return
     */
    private String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}