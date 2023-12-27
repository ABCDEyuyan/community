package com.zl.community.exception;

import com.zl.community.common.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Author : ZL
 * 自定义异常类
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;
    private String message;
    private T data;

    public BusinessException(ResultCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public BusinessException(ResultCode errorCode, T data) {
        this(errorCode);
        this.data = data;
    }

    public BusinessException(ResultCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(int code, String message, T data) {
        this(code, message);
        this.code = code;
        this.data = data;
    }


}
