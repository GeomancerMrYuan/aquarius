package com.ziroom.aquarius.common.lock;

/**
 * @Description 分布式锁的实现类
 * @Date 2020/6/11 10:08 上午
 * @Created by yuanpeng
 */
public interface Lock {
    /**
     * 获取锁
     */
    void getLock() throws Exception;

    /**
     * 释放锁
     */
    void unlock() throws Exception;
}
