package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.entity.Dept;
import com.ziroom.aquarius.system.mapper.DeptMapper;
import com.ziroom.aquarius.system.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author yuanpeng
 * @since 2020-04-13
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    @Transactional
    public Dept getByDeptId(String deptId) {
        return deptMapper.selectById(deptId);
    }
}
