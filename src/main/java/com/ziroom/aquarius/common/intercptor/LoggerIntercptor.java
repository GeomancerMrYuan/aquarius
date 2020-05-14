package com.ziroom.aquarius.common.intercptor;

import com.alibaba.fastjson.JSON;
import com.ziroom.aquarius.common.util.IpUtil;
import com.ziroom.aquarius.common.util.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import sun.net.util.IPAddressUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

/**
 * @Classname LoggerIntercptor
 * @Description 日志拦截器,拦截url,打印ip及入参;不推荐使用,细粒度太大了,想要只拦截controller包下的内容
 * @Date 2020-05-12 18:28
 * @Created by yuanpeng
 */
@Slf4j
public class LoggerIntercptor implements HandlerInterceptor {


    /**
     * @Description controller执行之前,更改线程id,打印请求ip及日志
     * @Date 2020-05-13 11:33
     * @Created by yuanpeng
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //统计计时
//        long beginTimeMS = System.currentTimeMillis();
//        request.setAttribute("beginTimeMS", beginTimeMS);
//        //设置线程uid
//        Thread.currentThread().setName(  "request_uuid:" + UUIDUtil.UUID32() +"_"+Thread.currentThread().getId());
//        //打印请求日志
//        StringBuilder logUrlSb = new StringBuilder("remoteIp:"+ IpUtil.getIpAddress(request) +","+request.getRequestURI());
//        logUrlSb.append("?");
//        Map<String, String[]> params = request.getParameterMap();
//        for (String key : params.keySet()) {
//            String[] values = params.get(key);
//            for (String value : values) {
//                logUrlSb.append(key);
//                logUrlSb.append("=");
//                logUrlSb.append(value);
//                logUrlSb.append("&");
//            }
//        }
//        String logUrl = logUrlSb.toString();
//        if (logUrl.endsWith("&")) {
//            logUrl = logUrl.substring(0, logUrl.lastIndexOf('&'));
//        } else if (logUrl.endsWith("?")) {
//            logUrl = logUrl.substring(0, logUrl.lastIndexOf('?'));
//        }
//        log.info(logUrl);
        return true;
    }

    /**
     * @Description controller执行之后;统计接口耗时及返回参数
     * @Date 2020-05-13 11:33
     * @Created by yuanpeng
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long timeMS = System.currentTimeMillis() - (long) request.getAttribute("beginTimeMS");
        log.info(handler+"接口耗时(ms):"+timeMS);
    }

}
