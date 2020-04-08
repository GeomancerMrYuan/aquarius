package com.ziroom.aquarius.common.interceptor;

import com.ziroom.aquarius.system.entity.User;
import com.ziroom.aquarius.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年01月07日 19:33
 * @since 1.0
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("url={}",request.getRequestURI());
        User login = (User) request.getSession().getAttribute("user");
        if (login != null && !StringUtils.isEmpty(login.getUsername()) && !StringUtils.isEmpty(login.getPassword())) {
            User user = userService.getUsername(login.getUsername());
            if(user.getPassword().equals(login.getPassword())){
                return true;
            }
        }
        request.setAttribute("msg", "请输入正确的用户名及密码");
        request.getRequestDispatcher("/").forward(request, response);
        return false;
    }
}
