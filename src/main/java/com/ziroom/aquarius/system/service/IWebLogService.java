package com.ziroom.aquarius.system.service;

import com.ziroom.aquarius.system.entity.WebLog;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yuanpeng
 * @since 2020-05-16
 */
public interface IWebLogService extends IService<WebLog> {

    /**
     * @Description 异步保存日志
     * @Date 2020-05-16 17:03
     * @Created by yuanpeng
     */
    void asynSave(WebLog entity) throws InterruptedException, Exception;
}
