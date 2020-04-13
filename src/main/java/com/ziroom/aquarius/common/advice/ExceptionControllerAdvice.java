package com.ziroom.aquarius.common.advice;

import com.ziroom.aquarius.common.exception.CustomerException;
import com.ziroom.aquarius.common.vo.BaseResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截器
 * @author yuanpeng
 * @version 1.0
 * @Date Created in 2020年04月13日 18:32
 * @since 1.0
 */
@RestControllerAdvice
public class ExceptionControllerAdvice {

    /**
     * 参数格式校验
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        ObjectError error = e.getBindingResult().getAllErrors().get(0);
        return BaseResult.fail(BaseResult.CodeEnums.VALID.getCode(),error.getDefaultMessage());
    }

    /**
     * 自定义异常
     * @param e
     * @return
     */
    @ExceptionHandler(CustomerException.class)
    public BaseResult customerExceptionHandler(CustomerException e){
        return BaseResult.fail(e.getCode(),e.getMsg());
    }


}
