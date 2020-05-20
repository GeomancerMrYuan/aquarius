package com.ziroom.aquarius.common.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${queue.acquaris.sms}", autoDelete = "true"), exchange =
@Exchange(value = "${exchange.acquaris.sms}", type = ExchangeTypes.FANOUT)))
public class RabbitMqConsumer {

    @RabbitHandler
    public void processMessage(String msg) {
        System.out.format("Receiving Message: -----[%s]----- \n.", msg);
    }
}