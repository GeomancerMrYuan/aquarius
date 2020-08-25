package com.ziroom.aquarius.elasticjob;

import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
import org.apache.shardingsphere.elasticjob.lite.internal.schedule.JobScheduler;
import org.apache.shardingsphere.elasticjob.lite.internal.snapshot.SnapshotService;
import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import javax.sql.DataSource;

/**
 * @Classname ElasticJobConfiguration
 * @Description TODO
 * @Date 2020/8/16 1:24 下午
 * @Created by yuanpeng
 */
//@Configuration
//@ImportResource(locations = {"classpath:application-elasticjob.xml"})
public class ElasticJobConfiguration {

    //开启监听接口
//    public ElasticJobConfiguration(CoordinatorRegistryCenter regCenter) {
//        new SnapshotService(regCenter, 9888).listen();
//    }

//    //配置注册中心
//    @Bean("scheduleJobBootstrap")
//    public ScheduleJobBootstrap configureSchedule() {
//
//    }


}
