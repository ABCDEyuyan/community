package com.zl.community.controller;

import com.zl.community.model.vo.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 获取用户信息类
 * @Author : ZL
 */
@Slf4j
public class BaseController {
    /**
     * 获取当前登录用户信息
     * @return
     */
    public UserPrincipal getLoginUser(){
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前登录用户账号
     * @return
     */
    public String getUserAccount(){
        return getLoginUser().getUserAccount();
    }

    /**
     * 获取当前登录用户登录名
     * @return
     */
    public String getUsername(){
        return getLoginUser().getUsername();
    }

    /**
     * 获取当前登录用户角色
     * @return
     */
    public Integer getUserRole(){
        return getLoginUser().getUserRole();
    }
}
