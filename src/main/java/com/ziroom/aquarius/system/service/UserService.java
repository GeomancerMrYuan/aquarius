package com.ziroom.aquarius.system.service;


import com.ziroom.aquarius.system.entity.User;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月01日 20:20
 * @since 1.0
 */
public interface UserService {
    /**
     * 根据主键删除
     * @param userId
     * @return
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * 新增记录
     * @param record
     * @return
     */
    int insertSelective(User record);

    /**
     * 主键查询
     * @param userId
     * @return
     */
    User selectByPrimaryKey(Long userId);

    /**
     * 主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据userName查询User对象
     * @param username
     * @return
     */
    User getUsername(String username);
}
