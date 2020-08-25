package com.ziroom.aquarius.elasticjob;

import org.apache.shardingsphere.elasticjob.api.listener.ElasticJobListener;
import org.apache.shardingsphere.elasticjob.api.listener.ShardingContexts;

/**
 * @Description 作业监听器
 * @Date 2020/8/14 2:25 下午
 * @Created by yuanpeng
 */
public class MyJobListener implements ElasticJobListener {

    @Override
    public void beforeJobExecuted(ShardingContexts shardingContexts) {
        // do something ...
        System.out.println("作业监听器执行前，threadId="+Thread.currentThread().getId());
    }

    @Override
    public void afterJobExecuted(ShardingContexts shardingContexts) {
        // do something ...
        System.out.println("作业监听器执行后，threadId="+Thread.currentThread().getId());
    }
}
