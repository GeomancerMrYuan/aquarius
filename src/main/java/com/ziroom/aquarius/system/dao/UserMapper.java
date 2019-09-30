package com.ziroom.aquarius.system.dao;

import org.apache.ibatis.annotations.Mapper;
import com.ziroom.aquarius.system.model.User;

@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Long userId);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    User getUsername(String username);
}