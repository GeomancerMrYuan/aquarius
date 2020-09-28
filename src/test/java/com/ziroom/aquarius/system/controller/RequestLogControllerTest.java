package com.ziroom.aquarius.system.controller;

import ch.qos.logback.core.util.ExecutorServiceUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
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
import java.util.HashMap;
import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @Classname RequestLogControllerTest
 * @Description TODO
 * @Date 2020/6/9 2:16 下午
 * @Created by yuanpeng
 */
@Slf4j
public class RequestLogControllerTest {
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


    public static void main(String[] args) {

        HashMap<String, Object> paramMap = new HashMap<>();
//文件上传只需将参数中的键指定（默认file），值设为文件对象即可，对于使用者来说，文件上传与普通表单提交并无区别
        paramMap.put("file", FileUtil.file("/Users/yuanpeng/IdeaProjects/aquarius/src/test/java/com/ziroom/aquarius/system/controller/LockDemo.java"));
        paramMap.put("rentContractCode", "123456");

        String result= HttpUtil.post("http://insure.t.ziroom.com/api/claims/upload", paramMap);
        System.out.println(result);
    }

}
