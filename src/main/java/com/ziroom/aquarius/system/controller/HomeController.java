package com.ziroom.aquarius.system.controller;

import com.ziroom.aquarius.system.entity.User;
import com.ziroom.aquarius.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年01月08日 16:50
 * @since 1.0
 */
@Controller
@RequestMapping("/home")
@Slf4j
@Api(tags = "登录信息")
public class HomeController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录", notes = "登录")
    public String login(String username, String password, HttpServletRequest request) {
        User user = userService.getUsername(username);
        if (user == null) {
            request.setAttribute("msg", "请输入正确的用户名及密码");
            return "common/login";
        }
        boolean flag = user.getPassword().equals(password);
        if (flag) {
            request.getSession().setAttribute("user", user);
            return "index";
        } else {
            request.setAttribute("msg", "请输入正确的用户名及密码");
            return "common/login";
        }
    }

    @GetMapping("/list")
    public String list(String username, String password, HttpServletRequest request) {
        return "index";
    }


}
