package com.ziroom.aquarius.recoupjob.common;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.gson.Gson;
import com.ziroom.aquarius.recoupjob.entity.RecoupJob;
import com.ziroom.aquarius.recoupjob.service.IRecoupJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.lite.internal.schedule.JobScheduler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.print.attribute.standard.JobName;
import javax.servlet.http.HttpServletRequest;

/**
 * 配置日志切面--适用于自定义的日志:如日志模块往数据库中新增用户操作记录
 *
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月21日 18:54
 * @since 1.0
 */
@Aspect
@Component
@Slf4j
public class RecoupAspect {
    @Autowired
    private IRecoupJobService jobService;

    /**
     * 切点
     */
    @Pointcut("@annotation(com.ziroom.aquarius.recoupjob.common.Recoupjob)")
    public void point() {

    }

    /**
     * @Description 环绕，可以在切入点前后织入代码，并且可以自由的控制何时执行切点；
     * @Date 2020-05-15 12:29
     * @Created by yuanpeng
     */
    @Around("point()&& @annotation(recoupjob)")
    public Object doAround(ProceedingJoinPoint joinPoint, Recoupjob recoupjob) throws Throwable {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Exception e) {
            String name = joinPoint.getTarget().getClass().getSimpleName();
            String cname = name.substring(0, 1).toLowerCase() + name.substring(1);
            //幂等校验,防止生成重复的补偿任务
            RecoupJob job = jobService.getOne(Wrappers.<RecoupJob>lambdaQuery()
                    .eq(RecoupJob::getJobName, cname)
                    .eq(RecoupJob::getBusCode, (String) joinPoint.getArgs()[1])
            );
            if(job!=null){
                log.info("{}补偿任务第{}次执行", job.getJobDesc(), job.getRetryCurTimes());
                throw e;
            }


            //新增补偿任务
            job = RecoupJob.builder()
                    .jobClass(joinPoint.getTarget().getClass().getName())
                    .jobName(cname)
                    .jobDesc(recoupjob.jobDesc())
                    .retryTotalTimes(12)
                    .startTime(DateUtil.currentSeconds())
                    .jobJsonParam(joinPoint.getArgs()[0].toString())
                    .busCode((String) joinPoint.getArgs()[1])
                    .async(0)
                    .failReason("无")
                    .jobStatus(RecoupJob.JobStatusEnum.EXECUTE.getCode())
                    .retryCurTimes(0)
                    .completeTime(DateUtil.currentSeconds())
                    .createTime(DateUtil.currentSeconds())
                    .effectTime(DateUtil.currentSeconds())
                    .failAlarmFlag(0)
                    .build();
            jobService.save(job);
            log.info("{}补偿任务生成成功", recoupjob.jobDesc());
            throw e;
        }
        return result;
    }

}
