package com.ziroom.aquarius.system.service;

import com.ziroom.aquarius.system.entity.Dept;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 部门表 服务类
 * </p>
 *
 * @author yuanpeng
 * @since 2020-04-13
 */
public interface IDeptService extends IService<Dept> {

    Dept getByDeptId(Long deptId);

    Dept updateByDeptId(Dept dept);

    void deleteByDeptId(Long deptId);
}
