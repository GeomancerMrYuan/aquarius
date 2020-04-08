package com.ziroom.aquarius.system.service.impl;


import com.ziroom.aquarius.system.dao.UserMapper;
import com.ziroom.aquarius.system.entity.User;
import com.ziroom.aquarius.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月01日 20:21
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int deleteByPrimaryKey(Long userId) {
        return 0;
    }

    @Override
    public int insertSelective(User record) {
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return 0;
    }

    @Override
    public User getUsername(String username) {
        return userMapper.getUsername(username);
    }
}
