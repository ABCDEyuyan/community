package com.zl.community.util;

import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;

/**
 * @Author : ZL
 * 抛异常工具类
 */
public class ThrowUtils {
    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ResultCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

//    /**
//     * 条件成立则抛异常
//     *
//     * @param condition
//     * @param errorCode
//     * @param message
//     */
//    public static void throwIf(boolean condition, ResultCode errorCode, String message) {
//        throwIf(condition, new BusinessException(errorCode));
//    }
}
