package com.ziroom.aquarius.system.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ziroom.aquarius.AquariusApplicationTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

/**
 * @Classname DeptControllerTest
 * @Description
 * @Date 2020-05-12 12:37
 * @Created by yuanpeng
 */
public class DeptControllerTest extends AquariusApplicationTests {

    /*
     * 1、mockMvc.perform执行一个请求。
     * 2、MockMvcRequestBuilders.get("XXX")构造一个请求。
     * 3、ResultActions.param添加请求传值
     * 4、ResultActions.accept(MediaType.TEXT_HTML_VALUE))设置返回类型
     * 5、ResultActions.andExpect添加执行完成后的断言。
     * 6、ResultActions.andDo添加一个结果处理器，表示要对结果做点什么事情
     *   比如此处使用MockMvcResultHandlers.print()输出整个响应结果信息。
     * 7、ResultActions.andReturn表示执行完成后返回相应的结果。
     */
    @Test
    public void getDeptById() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/system/dept/getDeptById/{deptId}",1)
                //请求头,accept 希望得到的返回数据类型
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
//                .param("name", "Tom"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void addDept() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/system/dept/add")
                //实体头,content_type 发送实体的类型
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{\"parentId\":0,\"deptName\":\"测试\",\"deptOrder\":5,\"createTime\":\"2019-06-14T21:00:42.000+0000\",\"updateTime\":null,\"creater\":null,\"updater\":null,\"isDel\":false}\n"))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Hello Tom!"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    public void getDeptPage () throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .post("/system/dept/getPage")
                // 设置返回值类型为utf-8，否则默认为ISO-8859-1
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .param("current","0")
                .param("size","10"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(1000))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}