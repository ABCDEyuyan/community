package com.zl.community.controller;

import com.zl.community.common.BaseResponse;
import com.zl.community.common.ResultCode;
import com.zl.community.exception.SecurityException;
import com.zl.community.model.dto.LoginRequest;
import com.zl.community.model.vo.UserPrincipal;
import com.zl.community.service.LoginLogService;
import com.zl.community.service.impl.LoginLogServiceImpl;
import com.zl.community.service.impl.LoginServiceImpl;
import com.zl.community.util.AuthenticationContextUtils;
import com.zl.community.util.JwtUtils;
import com.zl.community.util.ResponseUtil;
import com.zl.community.util.ResultUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static com.zl.community.common.ResultCode.NOT_LOGIN_ERROR;

/**
 * @Author : ZL
 */
@Slf4j
@RequestMapping("/auth")
@RestController
@Api(tags = "系统设置-登录")
public class AuthController {
    private JwtUtils jwtUtil;
    private LoginServiceImpl loginService;

    public AuthController(JwtUtils jwtUtil, LoginServiceImpl loginService) {
        this.jwtUtil = jwtUtil;
        this.loginService = loginService;
    }

    /**
     * 登录
     * @param loginRequest
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public BaseResponse login(@Valid @RequestBody LoginRequest loginRequest) {
        String jwt = loginService.login(loginRequest);
        return ResultUtils.success(ResultCode.SUCCESS,jwt);
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @ApiOperation("登出")
    @PostMapping("/logout")
    public BaseResponse logout(HttpServletRequest request) {
        try {
            // 设置JWT过期
            jwtUtil.invalidateJWT(request);
        } catch (SecurityException e) {
            throw new SecurityException(NOT_LOGIN_ERROR);
        }
        return ResultUtils.fail(ResultCode.FAILED);
    }
}
