package com.ziroom.aquarius.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziroom.aquarius.common.annotation.LogAnnotation;
import com.ziroom.aquarius.common.exception.CustomerException;
import com.ziroom.aquarius.common.util.RedissonUtil;
import com.ziroom.aquarius.common.vo.BaseResult;
import com.ziroom.aquarius.system.entity.Dept;
import com.ziroom.aquarius.system.service.IDeptService;
import com.ziroom.aquarius.system.service.impl.ProduceFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author yuanpeng
 * @since 2020-04-13
 */
@RestController
@RequestMapping("/system/dept")
@Slf4j
@Api(tags = "部门模块")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @Autowired
    private ProduceFactory factory;

    @Autowired
    private RedissonUtil redissonUtil;

    /**
     * 根据id获取部门信息
     *
     * @param deptId
     * @return
     */
    @GetMapping("/getDeptById/{deptId}")
    @ApiOperation("根据id获取部门信息")
    @LogAnnotation(intoDB = false, description = "查询部门信息")
    public BaseResult getDeptById(@PathVariable @ApiParam("部门id") Long deptId) throws InterruptedException {
        //增加分布式锁
        RLock lock = redissonUtil.getReentLock(deptId.toString());
        boolean flag = lock.tryLock(2, TimeUnit.SECONDS);
        if (!flag) {
            log.info("根据id获取部门信息任务产生了并发,该线程未获取到redis分布式锁");
            throw new CustomerException(BaseResult.CodeEnums.FAILED, "任务执行中,请稍后重试");
        }
        try {
            Dept dept = deptService.getByDeptId(deptId);
            return BaseResult.success(dept);
        } finally {
            lock.unlock();
        }
    }

    /**
     * 新增部门信息
     *
     * @param dept
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增部门信息")
    @LogAnnotation(intoDB = true, description = "新增部门信息")
    public BaseResult addDept(@RequestBody @Valid Dept dept) {
        deptService.save(dept);
        factory.select("rabbit").sendMsg("新增部门信息");
        return BaseResult.success();
    }

    /**
     * 修改部门信息
     *
     * @param dept
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改部门信息")
    public BaseResult updateDept(@RequestBody Dept dept) {
        deptService.updateByDeptId(dept);
        return BaseResult.success();
    }

    /**
     * @Description 删除部门信息
     * @Date 2020-05-21 09:37
     * @Created by yuanpeng
     */
    @GetMapping("/delete/{deptId}")
    @ApiOperation("删除部门信息")
    public BaseResult deleteDept(@PathVariable @ApiParam("部门id") Long deptId) {
        deptService.deleteByDeptId(deptId);
        return BaseResult.success();
    }


    @PostMapping("/getPage")
    public BaseResult getDeptPage(Long current, Long size, Dept dept) {
        log.info("入参current={},size={}", current,size);
        Page<Dept> page = new Page<>(current, size);
        QueryWrapper<Dept> wrapper = new QueryWrapper<>(dept);
        Page<Dept> result = deptService.page(page, wrapper);
        return BaseResult.success(result);

    }
}

