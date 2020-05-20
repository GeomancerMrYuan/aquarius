package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.system.service.ProducerService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @Classname ProducerServiceImpl
 * @Description rabbitMq发送消息
 * @Date 2020-05-16 21:20
 * @Created by yuanpeng
 */
@Service
public class RabbitMqProducer implements ProducerService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${exchange.acquaris.sms}")
    private String exchangeName;

    @Override
    public void sendMsg(String message) {
        rabbitTemplate.convertAndSend(exchangeName, message);
    }
}
