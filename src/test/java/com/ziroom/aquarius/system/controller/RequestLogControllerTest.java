package com.ziroom.aquarius.system.controller;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import com.ziroom.aquarius.AquariusApplicationTests;
import com.ziroom.aquarius.system.entity.RequestLog;
import com.ziroom.aquarius.system.mapper.RequestLogMapper;
import com.ziroom.aquarius.system.service.IRequestLogService;
import com.ziroom.aquarius.system.service.impl.RequestLogServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @Classname RequestLogControllerTest
 * @Description TODO
 * @Date 2020/6/9 2:16 下午
 * @Created by yuanpeng
 */
@Slf4j
public class RequestLogControllerTest extends AquariusApplicationTests {
    @Autowired
    private IRequestLogService logService;

    @Test
    public void insertRequestLog() throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask<>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                RequestLog log = new RequestLog();
                log.setRequestUrl("/system/add");
                log.setRequestParams("实体类");
                log.setOperationName("袁朋");
                log.setResult("成功");
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 1) {
                        log.setCreateTime(DateUtils.parseDate("20200703", "yyyyMMdd"));
                    }else{
                        log.setCreateTime(new Date());
                    }
                    logService.save(log);
                }
                return 100;
            }
        });
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.execute(task);
        System.out.println(task.get());
    }

}
