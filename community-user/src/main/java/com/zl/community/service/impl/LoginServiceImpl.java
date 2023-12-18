package com.zl.community.service.impl;

import com.zl.community.common.ResultCode;
import com.zl.community.exception.BusinessException;
import com.zl.community.model.dto.LoginRequest;
import com.zl.community.model.entity.LoginLogEntity;
import com.zl.community.model.vo.UserPrincipal;
import com.zl.community.service.LoginLogService;
import com.zl.community.util.AuthenticationContextUtils;
import com.zl.community.util.IpUtils;
import com.zl.community.util.JwtUtils;
import com.zl.community.util.ServletUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;

import static com.zl.community.constant.Constants.UserConstant.ENABLE;

/**
 * @Author : ZL
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl {
    private final AuthenticationManager authenticationManager;
    private final LoginLogService loginLogService;
    private final JwtUtils jwtUtils;

    public String login(LoginRequest loginRequest) {
        // Todo 判断验证码
        Authentication authenticate = null;
        try {
            //更新登录用户对象
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword()));

            AuthenticationContextUtils.setContext(authenticate);
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmailOrPhone(), loginRequest.getPassword());
//            System.out.println("authenticationToken = " + authenticationToken);
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
//            authenticate = authenticationManager.authenticate(authenticationToken);

        } catch (Exception e) {
            // 异常处理
            if (e instanceof BadCredentialsException) {
                throw new BusinessException(ResultCode.PARAMS_ERROR);
            } else {
                throw new BusinessException(500, e.getMessage());
            }
        }
        UserPrincipal userPrincipal = (UserPrincipal) authenticate.getPrincipal();
        // 生成Token,将token存入redis中
        String jwt = jwtUtils.createJWT(userPrincipal, loginRequest.getRememberMe());
        // 记录到日志中登录成功
        Assert.isTrue(loginLogService.save(recordLoginInfo(userPrincipal)), "日志添加失败");

        return jwt;
    }

    // 记录日志
    public LoginLogEntity recordLoginInfo(UserPrincipal userPrincipal) {
        LoginLogEntity sysLoginLog = new LoginLogEntity();
        sysLoginLog.setLoginTime(LocalDateTime.now());
        sysLoginLog.setUsername(userPrincipal.getUsername());
        sysLoginLog.setLoginId(userPrincipal.getId());
        sysLoginLog.setStatus(String.valueOf(ENABLE));
        sysLoginLog.setIp(IpUtils.getIpAddr(ServletUtils.getRequest()));
        return sysLoginLog;
    }
}
