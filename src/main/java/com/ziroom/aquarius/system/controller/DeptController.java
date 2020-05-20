package com.ziroom.aquarius.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ziroom.aquarius.common.annotation.LogAnnotation;
import com.ziroom.aquarius.common.vo.BaseResult;
import com.ziroom.aquarius.system.entity.Dept;
import com.ziroom.aquarius.system.service.IDeptService;
import com.ziroom.aquarius.system.service.ProducerService;
import com.ziroom.aquarius.system.service.impl.ProduceFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashMap;

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

    /**
     * 根据id获取部门信息
     * @param deptId
     * @return
     */
    @GetMapping("/getDeptById/{deptId}")
    @ApiOperation("根据id获取部门信息")
    @LogAnnotation(intoDB = true,description ="根据id获取部门信息" )
    public BaseResult getDeptById(@PathVariable @ApiParam("部门id") String deptId) {
        Dept dept = deptService.getByDeptId(deptId);
        factory.select("rocket").sendMsg("根据id获取部门信息");
        return BaseResult.success(dept);
    }

    /**
     * 新增部门信息
     * @param dept
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("新增部门信息")
    public BaseResult addDept(@RequestBody @Valid Dept dept) {
        deptService.save(dept);
        return BaseResult.success();
    }

    @PostMapping("/getPage")
    public BaseResult getDeptPage(Long current, Long size, Dept dept){
        Page<Dept> page = new Page<>(current,size);
        QueryWrapper<Dept> wrapper = new QueryWrapper<>(dept);
        Page<Dept> result = deptService.page(page, wrapper);
        return BaseResult.success(result);

    }
}

