package com.zl.community.exception;

import com.zl.community.common.ResultCode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.formula.functions.T;

/**
 * @Author : ZL
 */

public class SecurityException extends BusinessException{

    public SecurityException(ResultCode errorCode) {
        super(errorCode);
    }

    public SecurityException(ResultCode errorCode, T data) {
        super(errorCode, data);
    }

    public SecurityException(int code, String message) {
        super(code, message);
    }

    public SecurityException(int code, String message, T data) {
        super(code, message, data);
    }
}
