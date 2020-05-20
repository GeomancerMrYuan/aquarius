package com.ziroom.aquarius.system.service.impl;

import com.ziroom.aquarius.common.exception.CustomerException;
import com.ziroom.aquarius.common.vo.BaseResult;
import com.ziroom.aquarius.system.service.ProducerService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProduceFactory {

    //此处是否有必要使用concurrentHashMap
    //个人分析:factory对象是默认单例的(new 对象的请绕道),只会初始化一次,loanMap及init方法时私有的,不会并发(暴力反射的请绕道),select方法会并发,但是只有查询操作
    private static final Map<String, ProducerService> loanMap = new ConcurrentHashMap<>();

    @Resource(name="rabbitMqProducer")
    private ProducerService rabbitMqProducer;

    @Resource(name="rocketMqProducer")
    private ProducerService rocketMqProducer;

    /**
     * @Description PostConstruct--依赖注入完毕后执行此方法
     * @Date 2020-05-20 11:32
     * @Created by yuanpeng
     */
    @PostConstruct
    private void init(){
        this.loanMap.clear();
        loanMap.put("rabbit",rabbitMqProducer);
        loanMap.put("rocket",rocketMqProducer);
    }

    public ProducerService select(String type) {
        ProducerService producerService = loanMap.get(type);
        return Optional.ofNullable(producerService).orElseThrow(() -> new CustomerException(BaseResult.CodeEnums.FAILED,"没有查询的相关数据"));
    }
}