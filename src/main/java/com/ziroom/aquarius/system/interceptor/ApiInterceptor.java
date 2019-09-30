package com.ziroom.aquarius.system.interceptor;

import com.ziroom.aquarius.system.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年09月04日 15:39
 * @since 1.0
 */
@Configuration
@Order(1)
public class ApiInterceptor implements HandlerInterceptor{
    @Autowired
    private RequestService requestService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        String requestURI = request.getRequestURI();
        String url = requestURI.substring(requestURI.indexOf("/api") + 4);
        String reponse = requestService.getReponseByUrl(url);
        try (PrintWriter writer = response.getWriter()) {
            writer.append(reponse);
        }
        return false;
    }
}
