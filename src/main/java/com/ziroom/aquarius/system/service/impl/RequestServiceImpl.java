package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.dao.RequestMapper;
import com.ziroom.aquarius.system.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年09月04日 15:08
 * @since 1.0
 */
@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    private RequestMapper requestMapper;
    @Override
    public String getReponseByUrl(String url) {
        return requestMapper.getReponseByUrl(url);
    }
}
