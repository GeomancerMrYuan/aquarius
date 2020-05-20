package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.entity.Dept;
import com.ziroom.aquarius.system.service.ProducerService;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Classname RocketMqProducer
 * @Description rocketMq发送消息
 * @Date 2020-05-19 10:41
 * @Created by yuanpeng
 *
 * RocketMQ的消息发送方式主要含syncSend()同步发送、asyncSend()异步发送、sendOneWay()三种方式，sendOneWay()也是异步发送，
 * 区别在于不需等待Broker返回确认，所以可能会存在信息丢失的状况，但吞吐量更高，具体需根据业务情况选用。
 * 性能：sendOneWay > asyncSend > syncSend
 */
@Service
public class RocketMqProducer implements ProducerService {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void sendMsg(String message) {
        convertAndSend(message);
    }

    /**
     * @Description 发送同步消息
     * @Date 2020-05-19 16:54
     * @Created by yuanpeng
     */
    public void convertAndSend(String message) {
        rocketMQTemplate.convertAndSend("acquaris", message);
    }

    /**
     * @Description 发送同步消息,带tag
     * @Date 2020-05-19 16:54
     * @Created by yuanpeng
     */
    public void convertAndSend(String message,String tag) {
        rocketMQTemplate.convertAndSend("acquaris:"+tag, message);
    }

    /**
     * @Description 发送即时消息
     * @Date 2020-05-19 17:02
     * @Created by yuanpeng
     */
    public void sendOneWay(Dept dept) {
        rocketMQTemplate.sendOneWay("acquaris", MessageBuilder.withPayload(dept).build());
    }

    /**
     * @Description 发送顺序消息
     * @Date 2020-05-19 17:02
     * @Created by yuanpeng
     */
    public void syncSendOrderly(Dept dept) {
        rocketMQTemplate.syncSendOrderly("acquaris", MessageBuilder.withPayload(dept).build(), "1234");
    }

    /**
     * @Description 发送异步消息
     * @Date 2020-05-19 17:02
     * @Created by yuanpeng
     */
    public void asyncSend(Dept dept) {
        rocketMQTemplate.asyncSend("acquaris", MessageBuilder.withPayload(dept).build(), new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {

            }
            @Override
            public void onException(Throwable throwable) {

            }
        });
    }



}
