package com.ziroom.aquarius.recoupjob.common;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.ziroom.aquarius.recoupjob.common.IRecoup;
import com.ziroom.aquarius.recoupjob.entity.RecoupJob;
import com.ziroom.aquarius.recoupjob.mapper.RecoupJobMapper;
import com.ziroom.aquarius.recoupjob.service.IRecoupJobService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Classname ScheduleJob
 * @Description 补偿任务执行流程
 * @Date 2020/8/19 3:49 下午
 * @Created by yuanpeng
 */
@Component
@Slf4j
public class ScheduleJob implements SimpleJob {
    @Autowired
    private IRecoupJobService recoupJobService;
    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void execute(ShardingContext context) {
        //设置线程uid
        Thread.currentThread().setName("request_uuid:" + IdUtil.randomUUID() + "_" + Thread.currentThread().getId());

        long start = System.currentTimeMillis();
        //得到分片项
        int items = context.getShardingTotalCount();
        if (items == 0) {
            log.warn("该任务未分配到分片项，放弃执行");
            return;
        }
        //得到待处理的所有job Id
        List<RecoupJob> excuteList = recoupJobService.list(
                Wrappers.<RecoupJob>lambdaQuery()
                        .in(RecoupJob::getJobStatus, RecoupJob.JobStatusEnum.EXECUTE.getCode(), RecoupJob.JobStatusEnum.EXECUTING.getCode())
                        .le(RecoupJob::getStartTime, DateUtil.currentSeconds())
        );
        if (excuteList == null || excuteList.isEmpty()) {
            log.info("未找到待执行或执行中的作业，放弃执行");
            return;
        }
        log.info("本次需要补偿任务总数={}", excuteList.size());

        //处理分片项和实际数据的对应关系
        int item = context.getShardingItem();
        List<RecoupJob> jobs = excuteList.stream().filter(task -> item == task.getId() % items).collect(Collectors.toList());
        if (jobs.isEmpty()) {
            log.info("未找到待执行或执行中的作业，放弃执行");
            return;
        }
        //执行补偿任务
        for (RecoupJob job : jobs) {
            IRecoup recoup = (IRecoup) applicationContext.getBean(job.getJobName());
            try {
                recoup.recoup(job.getJobJsonParam(), job.getBusCode());
                recoupJobService.update(
                        Wrappers.<RecoupJob>lambdaUpdate()
                                .set(RecoupJob::getJobStatus,  RecoupJob.JobStatusEnum.SUCCESSED.getCode())
                                .eq(RecoupJob::getId, job.getId())
                );
            } catch (Exception e) {
                //如果执行次数大于总次数,更改状态+执行完毕后的方法
                if(job.getRetryCurTimes()+1>job.getRetryTotalTimes()){
                    recoupJobService.update(
                            Wrappers.<RecoupJob>lambdaUpdate()
                                    .set(RecoupJob::getJobStatus,  RecoupJob.JobStatusEnum.FAILED.getCode())
                                    .eq(RecoupJob::getId, job.getId())
                    );
                    try {
                        recoup.afterRecoup(RecoupJob.JobStatusEnum.FAILED.getCode(),job.getJobJsonParam(),job.getBusCode(),"补偿任务失败,失败线程name:"+Thread.currentThread().getName());
                    } catch (Exception exception) {
                        log.error("补偿任务失败后afterRecoup方法异常");
                    }
                }else{
                    //执行次数加1
                    recoupJobService.update(
                            Wrappers.<RecoupJob>lambdaUpdate()
                                    .set(RecoupJob::getRetryCurTimes, job.getRetryCurTimes()+1 )
                                    .eq(RecoupJob::getId, job.getId()));
                }
            }
        }

        //在0号分片删除30天前的已执行成功的记录
        if (item==0) {
            DateTime offsetDay = DateUtil.offsetDay(new Date(), -30);
            recoupJobService.remove( Wrappers.<RecoupJob>lambdaQuery()
                    .eq(RecoupJob::getJobStatus, RecoupJob.JobStatusEnum.SUCCESSED.getCode())
                    .lt(RecoupJob::getStartTime, offsetDay.second()));
        }
    }
}
