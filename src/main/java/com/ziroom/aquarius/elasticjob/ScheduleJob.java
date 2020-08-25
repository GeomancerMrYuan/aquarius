package com.ziroom.aquarius.elasticjob;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.stereotype.Component;

/**
 * @Classname ScheduleJob
 * @Description TODO
 * @Date 2020/8/19 3:49 下午
 * @Created by yuanpeng
 */
@Component
public class ScheduleJob  implements SimpleJob {
    @Override
    public void execute(ShardingContext context) {
        System.out.println("作业ScheduleJob");
    }
}
