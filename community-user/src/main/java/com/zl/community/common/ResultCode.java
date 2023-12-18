package com.zl.community.common;

/**
 * @Author : ZL
 * 自定义错误码
 */
public enum ResultCode {

    SUCCESS(200, "调用成功"),
    FAILED(500, "调用失败"),
    PARAMS_ERROR(4000, "请求参数错误"),
    NOT_LOGIN_ERROR(4001, "未登录"),
    UN_AUTHORIZED(4002,"未授权"),
    NO_AUTH_ERROR(4003, "无权限"),
    NOT_FOUND_ERROR(4004, "请求不存在"),
    FORBIDDEN_ERROR(4005, "禁止访问"),
    SYSTEM_ERROR(4006, "系统内部异常"),
    OPERATION_ERROR(4007, "操作失败"),
    /**
     * 用户名或密码错误！
     */
    USERNAME_PASSWORD_ERROR(5001, "用户名或密码错误！"),

    /**
     * token 已过期，请重新登录！
     */
    TOKEN_EXPIRED(5002, "token 已过期，请重新登录！"),

    /**
     * token 解析失败，请尝试重新登录！
     */
    TOKEN_PARSE_ERROR(5002, "token 解析失败，请尝试重新登录！"),

    /**
     * 当前用户已在别处登录，请尝试更改密码或重新登录！
     */
    TOKEN_OUT_OF_CTRL(5003, "当前用户已在别处登录，请尝试更改密码或重新登录！"),

    /**
     * 请求方式不支持！
     */
    HTTP_BAD_METHOD(5004, "请求方式不支持！"),

    /**
     * 无法手动踢出自己，请尝试退出登录操作！
     */
    KICKOUT_SELF(5005, "无法手动踢出自己，请尝试退出登录操作！");

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
