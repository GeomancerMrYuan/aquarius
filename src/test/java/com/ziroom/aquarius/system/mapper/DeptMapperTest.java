package com.ziroom.aquarius.system.mapper;

import com.ziroom.aquarius.system.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月13日 16:39
 * @since 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeptMapperTest {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    public void getById() {
        System.out.println(("----- selectAll method test ------"));
        Dept dept = deptMapper.selectById(1);
        System.out.println(dept.toString());
    }


}