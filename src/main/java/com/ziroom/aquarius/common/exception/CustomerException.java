package com.ziroom.aquarius.common.exception;

import com.ziroom.aquarius.common.vo.BaseResult;
import lombok.Getter;

/**
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月13日 18:45
 * @since 1.0
 */
@Getter
public class CustomerException extends RuntimeException {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 异常信息
     */
    private String msg;

    /**
     * @Description 构造方法--带code和msg
     * @Date 2020-05-20 11:29
     * @Created by yuanpeng
     */
    public CustomerException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @Description 构造方法--带enum
     * @Date 2020-05-20 11:30
     * @Created by yuanpeng
     */
    public CustomerException(BaseResult.CodeEnums codeEnums) {
        this.code = codeEnums.getCode();
        this.msg = codeEnums.getMsg();
    }

    /**
     * @Description 构造方法--带enum和msg
     * @Date 2020-05-20 11:31
     * @Created by yuanpeng
     */
    public CustomerException(BaseResult.CodeEnums codeEnums, String msg) {
        this.code = codeEnums.getCode();
        this.msg = msg;
    }
}
