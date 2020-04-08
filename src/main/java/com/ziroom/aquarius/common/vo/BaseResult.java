package com.ziroom.aquarius.common.vo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2019年07月01日 20:37
 * @since 1.0
 */
@Data
@Slf4j
public class BaseResult<T> implements Serializable {

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
    private T data;

    /**
     * 成功
     * @return
     */
    public static BaseResult success() {
        BaseResult result = new BaseResult();
        result.setCode(0);
        result.setMessage("请求成功");
        log.info(result.toString());
        return result;
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static<T> BaseResult success(T data) {
        BaseResult result = new BaseResult();
        result.setCode(0);
        result.setMessage("请求成功");
        result.setData(data);
        log.info(result.toString());
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
        log.info(result.toString());
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static<T> BaseResult fail(T data) {
        BaseResult result = new BaseResult();
        result.setCode(1);
        result.setMessage("请求失败");
        result.setData(data);
        log.info(result.toString());
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static BaseResult fail(String msg) {
        BaseResult result = new BaseResult();
        result.setCode(1);
        result.setMessage(msg);
        log.info(result.toString());
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static BaseResult fail(Integer code, String msg) {
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMessage(msg);
        log.info(result.toString());
        return result;
    }

    /**
     * 自定义返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static<T> BaseResult build(Integer code, String msg, T data) {
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        log.info(result.toString());
        return result;
    }

}
