package com.ziroom.aquarius.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月01日 20:37
 * @since 1.0
 */
@Data
public class BaseResult {

    public static Logger logger = LoggerFactory.getLogger(BaseResult.class);
    /**
     * 状态码,0-成功,1-失败
     */
    private int code;
    /**
     * 消息
     */
    private String message;
    /**
     * 数据
     */
    private Object data;

    /**
     * 成功
     * @return
     */
    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setCode(0);
        result.setMessage("请求成功");
        logger.info(result.toString());
        return result;
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static BaseResult success(Object data) {
        BaseResult result = new BaseResult();
        result.setCode(0);
        result.setMessage("请求成功");
        result.setData(data);
        logger.info(result.toString());
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static BaseResult fail() {
        BaseResult result = new BaseResult();
        result.setCode(1);
        result.setMessage("请求失败");
        logger.info(result.toString());
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static BaseResult fail(Object data) {
        BaseResult result = new BaseResult();
        result.setCode(1);
        result.setMessage("请求失败");
        result.setData(data);
        logger.info(result.toString());
        return result;
    }

}
