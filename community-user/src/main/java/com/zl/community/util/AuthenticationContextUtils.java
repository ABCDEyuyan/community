package com.zl.community.util;

import org.springframework.security.core.Authentication;

/**
 * @Author : ZL
 * 此类用于获取身份验证信息
 */
public class AuthenticationContextUtils {
    /**
     *  获取当前登录用户的信息
     */
    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext()
    {
        return contextHolder.get();
    }

    public static void setContext(Authentication context)
    {
        contextHolder.set(context);
    }

    public static void clearContext()
    {
        contextHolder.remove();
    }
}
