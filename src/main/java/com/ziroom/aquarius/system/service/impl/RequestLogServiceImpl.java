package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.entity.RequestLog;
import com.ziroom.aquarius.system.mapper.RequestLogMapper;
import com.ziroom.aquarius.system.service.IRequestLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 请求日志表202006 服务实现类
 * </p>
 *
 * @author yuanpeng
 * @since 2020-06-09
 */
@Service
public class RequestLogServiceImpl extends ServiceImpl<RequestLogMapper, RequestLog> implements IRequestLogService {

}
