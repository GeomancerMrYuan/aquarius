package com.ziroom.aquarius.elasticjob;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

//@Component
public class MyJob implements SimpleJob {
    static int i=0;

    @Override
    public void execute(ShardingContext context) {
        System.out.println("作业MyJob");
        switch (context.getShardingItem()) {
            case 0:
                // do something by sharding item 0
                System.out.println("分片项:0,线程信息"+Thread.currentThread().getId()+",参数值i="+i++);
                break;
            case 1:
                // do something by sharding item 1
                System.out.println("分片项:1,线程信息"+Thread.currentThread().getId()+",参数值i="+i++);
                break;
            case 2:
                // do something by sharding item 2
                System.out.println("分片项:2,线程信息"+Thread.currentThread().getId()+",参数值i="+i++);
                break;
            // case n: ...
        }
    }
}
