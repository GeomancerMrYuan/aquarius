package com.ziroom.aquarius.common.vo;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;
import java.util.Date;

/**
 * @Classname ShardingAlgorithm
 * @Description 标准分片   按时间进行表分片,如202006\202007
 * @Date 2020/6/9 12:04 下午
 * @Created by yuanpeng
 */
@Slf4j
public class ShardingAlgorithm implements PreciseShardingAlgorithm<Date> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Date> preciseShardingValue) {
            log.info("collection:" + JSON.toJSONString(collection) + ",preciseShardingValue:" + JSON.toJSONString(preciseShardingValue));
        Date date = preciseShardingValue.getValue();
        for (String name : collection) {
            log.info("name:{},time:{}",name,DateFormatUtils.format(date, "yyyyMM"));
            if(name.endsWith(DateFormatUtils.format(date, "yyyyMM"))){
                return name;
            }
        }
        return null;
    }
}
