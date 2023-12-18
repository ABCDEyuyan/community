package com.zl.community.exception;

import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.util.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : ZL
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(SecurityException.class)
    public BaseResponse<?> securityExceptionHandler(SecurityException e) {
        System.out.println("security异常处理>>>>>>>>>>>>>>>>>>>>>>>");
        log.error("SecurityException", e);
        return ResultUtils.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        System.out.println("自定义异常处理>>>>>>>>>>>>>>>>>>>>>>>");
        log.error("BusinessException", e);
        return ResultUtils.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        System.out.println("全局统一异常处理>>>>>>>>>>>>>>>>>>>>>>>");
        log.error("RuntimeException", e);
        return ResultUtils.fail(ResultCode.SYSTEM_ERROR);
    }
}
