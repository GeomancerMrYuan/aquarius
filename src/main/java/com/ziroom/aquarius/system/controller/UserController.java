package com.ziroom.aquarius.system.controller;


import com.ziroom.aquarius.system.model.User;
import com.ziroom.aquarius.system.service.UserService;
import com.ziroom.aquarius.system.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月01日 16:38
 * @since 1.0
 */
@Controller
@RequestMapping("/user")
@ResponseBody
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    public String getUserById(Long userId) {
        User user = userService.selectByPrimaryKey(userId);
        return "你好";
    }

    @RequestMapping("/getUserName")
    public BaseResult getUsername(String username) {
        User user = userService.getUsername(username);
        return BaseResult.success(user);
    }




}
