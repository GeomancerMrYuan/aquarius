package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.entity.WebLog;
import com.ziroom.aquarius.system.mapper.WebLogMapper;
import com.ziroom.aquarius.system.service.IWebLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuanpeng
 * @since 2020-05-16
 */
@Service
public class WebLogServiceImpl extends ServiceImpl<WebLogMapper, WebLog> implements IWebLogService {

    @Autowired
    private WebLogMapper webLogMapper;

    /**
     * @Description 重写save方法,启用线程池,异步调用
     * @Date 2020-05-16 16:28
     * @Created by yuanpeng
     */
    @Override
    @Async("asyncServiceExecutor")
    public void asynSave(WebLog entity) throws Exception {
        webLogMapper.insert(entity);
    }
}
