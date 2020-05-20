package com.ziroom.aquarius.common.listener;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RocketMQMessageListener(topic = "acquaris", consumerGroup = "finance")
public class RocketMqConsumer implements RocketMQListener<String> {

    public void onMessage(String message) {
        System.out.println("收到消息：" + message);
    }

}