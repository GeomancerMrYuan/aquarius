package com.ziroom.aquarius.dao;

import com.ziroom.aquarius.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月01日 17:56
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserMapper userMapper;
    @Test
    public void getUserById() {
        User user = userMapper.selectByPrimaryKey(1L);
        logger.info(user.toString());
    }

}