package com.ziroom.aquarius.recoupjob.controller;


import com.ziroom.aquarius.common.vo.BaseResult;
import com.ziroom.aquarius.elasticjob.TestRecoupJob;
import com.ziroom.aquarius.system.entity.Dept;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 通用业务补偿表 前端控制器
 * </p>
 *
 * @author yuanpeng
 * @since 2020-08-28
 */
@RestController
@RequestMapping("/recoupjob/recoup-job")
@Api
public class RecoupJobController {
    @Autowired
    private TestRecoupJob job;

    @PostMapping("/test")
    @ApiOperation("补偿任务测试")
    public BaseResult updateDept() throws Exception {
        job.recoup("123", "123");
        return BaseResult.success();
    }
}

