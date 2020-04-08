package com.ziroom.aquarius.system.controller;


import com.ziroom.aquarius.common.vo.BaseResult;
import com.ziroom.aquarius.system.entity.User;
import com.ziroom.aquarius.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api(tags="用户信息")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/index")
    public String getIndex(Long userId) {
        return "测试成功";
    }

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @GetMapping("/getUserById")
    public String getUserById(Long userId) {
        User user = userService.selectByPrimaryKey(userId);
        return user.toString();
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    @ApiOperation(value="根据用户名查询用户信息", notes="根据用户名查询用户信息")
    @GetMapping("/getUserName")
    public BaseResult getUsername(String username) {
        User user = userService.getUsername(username);
        return BaseResult.success(user);
    }




}
