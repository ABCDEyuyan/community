package com.zl.community.util;

import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;

/**
 * @Author : ZL
 * 返回工具类
 */
public class ResultUtils {

    /**
     * 成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResponse<T> success(String message, T data) {
        return new BaseResponse(ResultCode.SUCCESS.getCode(), message, data);
    }

    public static <T> BaseResponse<T> success(Integer code, T data) {
        return new BaseResponse(code, ResultCode.SUCCESS.getMessage(), data);
    }

    public static <T> BaseResponse<T> success(ResultCode resultCode, T data) {
        return new BaseResponse(resultCode.getCode(), resultCode.getMessage(), data);
    }

    /**
     * 失败
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> fail() {
        return new BaseResponse(ResultCode.FAILED.getCode(), ResultCode.FAILED.getMessage(), (Object) null);
    }

    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse(ResultCode.FAILED.getCode(), message, (Object) null);
    }

    public static <T> BaseResponse<T> fail(Integer code, String message) {
        return new BaseResponse(code, message, (Object) null);
    }

    public static <T> BaseResponse<T> fail(ResultCode resultCode) {
        return new BaseResponse(resultCode.getCode(), resultCode.getMessage(), (Object) null);
    }

    /**
     * 有状态且带数据
     */
    public static <T> BaseResponse<T> ofCode(ResultCode resultCode,T data){
        return new BaseResponse(resultCode.getCode(),resultCode.getMessage(),data);
    }

    /**
     * 有状态且带异常
     *
     * @param t   异常
     * @param <T> {@link BusinessException} 的子类
     * @return ApiResponse
     */
    public static <T extends BusinessException> BaseResponse ofException(T t) {
        return new BaseResponse(t.getCode(), t.getMessage(), t.getData());
    }
}
