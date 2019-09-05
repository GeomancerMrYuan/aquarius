package com.ziroom.aquarius.system.service;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年09月04日 15:02
 * @since 1.0
 */
public interface RequestService {
    /**
     * 根据url获取reponse
     * @param url
     * @return
     */
    String getReponseByUrl(String url);
}
