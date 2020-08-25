package com.ziroom.aquarius.system.controller;


import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.http.HttpUtil;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 请求日志表202006 前端控制器
 * </p>
 *
 * @author yuanpeng
 * @since 2020-06-09
 */
@RestController
@RequestMapping("/system/request-log")
public class RequestLogController {

    public void uploadByge(byte[] fileByte){
        HashMap<String, Object> paramMap = new HashMap<>();
        File file = FileUtil.writeBytes(fileByte,"/tmp/");
        paramMap.put("file",file);
        String result = HttpUtil.post("url", paramMap);
    }

}

