package com.ziroom.aquarius.common.exception;

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

    public CustomerException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
