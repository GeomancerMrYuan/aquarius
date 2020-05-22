package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.entity.Dept;
import com.ziroom.aquarius.system.mapper.DeptMapper;
import com.ziroom.aquarius.system.service.IDeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "dept")  //作用在类上,所有方法使用的缓存名称
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements IDeptService {

    @Autowired
    private DeptMapper deptMapper;

    /**
     * @Description 查询
     * @Date 2020-05-21 09:56
     * @Created by yuanpeng
     */
    @Override
    @Cacheable //加在查询方法上，表示将一个方法的返回值缓存起来,默认就使用多个参数来做 key，如果只需要其中某一个参数做 key，则可以在 @Cacheable 注解中，通过 key 属性来指定 key，
    public Dept getByDeptId(Long deptId) {
        return deptMapper.selectById(deptId);
    }

    /**
     * @Description 更新
     * @Date 2020-05-21 09:57
     * @Created by yuanpeng
     */
    @Override
    @CachePut(key = "#dept.id") //加在更新方法上，当数据库中的数据更新后，缓存中的数据也要跟着更新
    public Dept updateByDeptId(Dept dept){
        deptMapper.updateById(dept);
        Dept update = deptMapper.selectById(dept.getId());
        return update;
    }

    /**
     * @Description 删除
     * @Date 2020-05-21 09:58
     * @Created by yuanpeng
     */
    @Override
    @CacheEvict //加载删除方法上
    public void deleteByDeptId(Long deptId){
        deptMapper.deleteById(deptId);
    }


}
