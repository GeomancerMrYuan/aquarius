package com.ziroom.aquarius.controller;

import com.ziroom.aquarius.model.User;
import com.ziroom.aquarius.service.UserService;
import com.ziroom.aquarius.vo.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class UserController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping("/getUserById")
    public BaseResult getUserById(Long userId) {
        try {
            User user = userService.selectByPrimaryKey(userId);
            return BaseResult.success(user);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("根据id获取用户异常",e);
            return BaseResult.fail();
        }

    }
}
