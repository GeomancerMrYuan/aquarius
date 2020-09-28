package com.ziroom.aquarius.elasticjob;

import com.ziroom.aquarius.recoupjob.common.IRecoup;
import com.ziroom.aquarius.recoupjob.common.Recoupjob;
import org.springframework.stereotype.Component;

/**
 * @Classname TestRecoupJob
 * @Description TODO
 * @Date 2020/8/31 5:59 下午
 * @Created by yuanpeng
 */
@Component
public class TestRecoupJob implements IRecoup {

    @Override
    @Recoupjob(jobDesc = "测试补偿任务")
    public void recoup(String jobJsonParam, String busCode) throws Exception {
        System.out.println("补偿任务正在执行中============================================");
        throw new Exception("测试");
    }

    @Override
    public void afterRecoup(String jobStatus, String jobJsonParam, String busCode, String failReason) throws Exception {
        System.out.println("补偿任务执行结束============================================");
    }
}
