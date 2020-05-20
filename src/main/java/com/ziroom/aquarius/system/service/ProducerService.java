package com.ziroom.aquarius.system.service;

/**
 * @Classname ProducerService
 * @Description TODO
 * @Date 2020-05-16 21:15
 * @Created by yuanpeng
 */
public interface ProducerService {

    /**
     * @Description 向rabbitMq发送消息
     * @Date 2020-05-17 15:08
     * @Created by yuanpeng
     */
    void sendMsg(String message);
}
