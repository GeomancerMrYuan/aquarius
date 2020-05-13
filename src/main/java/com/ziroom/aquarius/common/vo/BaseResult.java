package com.ziroom.aquarius.common.vo;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.Getter;
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
        result.setCode(CodeEnums.SUCCESS.code);
        result.setMessage("请求成功");
        logger.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static BaseResult success(Object data) {
        BaseResult result = new BaseResult();
        result.setCode(CodeEnums.SUCCESS.code);
        result.setMessage("请求成功");
        result.setData(data);
        logger.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 失败
     * @return
     */
    public static BaseResult fail() {
        BaseResult result = new BaseResult();
        result.setCode(CodeEnums.FAILED.code);
        result.setMessage("系统异常");
        logger.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static BaseResult fail(Object data) {
        BaseResult result = new BaseResult();
        result.setCode(CodeEnums.FAILED.code);
        result.setMessage("系统异常");
        result.setData(data);
        logger.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static BaseResult fail(String msg) {
        BaseResult result = new BaseResult();
        result.setCode(CodeEnums.FAILED.code);
        result.setMessage(msg);
        logger.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static BaseResult fail(Integer code,String msg) {
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMessage(msg);
        logger.info(JSON.toJSONString(result));
        return result;
    }

    /**
     * 自定义返回
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static BaseResult build(Integer code,String msg,Object data) {
        BaseResult result = new BaseResult();
        result.setCode(code);
        result.setMessage(msg);
        result.setData(data);
        logger.info(JSON.toJSONString(result));
        return result;
    }

    @Getter
    public enum CodeEnums{
        /**
         * 状态码枚举
         */
        SUCCESS(1000,"请求成功"),
        VALID(1001,"参数校验失败"),
        FAILED(9999,"自定义失败")
        ;
        private Integer code;
        private String msg;

        CodeEnums(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
