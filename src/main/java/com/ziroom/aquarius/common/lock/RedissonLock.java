package com.ziroom.aquarius.common.lock;

import org.redisson.Redisson;
import org.redisson.RedissonMultiLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.TransportMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Classname RedissonLockUtil
 * @Description 整合redisson框架, 包装常用工具类, 方便各位开发人员使用
 * 分布式锁,布隆过滤器(缓存击穿),基数估计算法,累加器,限流器
 * @Date 2020-05-22 18:12
 * @Created by yuanpeng
 */
//@Component
public class RedissonLock implements Lock {
    private RedissonClient redisson;

    private RLock lock;

    public RedissonLock() {
        Config config = new Config();
//        config.setTransportMode(TransportMode.EPOLL);
        config.useSingleServer()
                //可以用"rediss://"来启用SSL连接
                .setAddress("redis://127.0.0.1:6379");
        redisson=Redisson.create(config);
        lock = redisson.getLock("redisLock");
    }

    /**
     * @Description 可重入锁  Reentrant Lock--正常使用的分布式锁
     * @Date 2020-05-22 18:45
     * @Created by yuanpeng
     */
    public RLock getReentLock(String key) {
        return redisson.getLock(key);
    }

    /**
     * @Description 公平锁 Fair Lock--用于秒杀的分布式锁
     * @Date 2020-05-22 18:46
     * @Created by yuanpeng
     */
    public RLock getFairLock(String key) {
        return redisson.getFairLock(key);
    }

    /**
     * @Description 联锁（MultiLock）--个RLock对象实例可以来自于不同的Redisson实例
     * @Date 2020-05-22 18:46
     * @Created by yuanpeng
     */
    public RLock getMultiLockLock(String key1, String key2, String key3) {
        RLock lock1 = redisson.getLock(key1);
        RLock lock2 = redisson.getLock(key2);
        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2);
        return lock;
    }


    @Override
    public void getLock() throws Exception {
        lock.tryLock(100, 10, TimeUnit.SECONDS);
    }

    @Override
    public void unlock() throws Exception {
        lock.unlock();
    }
}
