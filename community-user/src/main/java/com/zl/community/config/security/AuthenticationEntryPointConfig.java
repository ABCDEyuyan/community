package com.zl.community.config.security;

import com.zl.community.util.ResponseUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zl.community.common.ResultCode.UN_AUTHORIZED;

/**
 * @Author : ZL
 * 认证失败处理类 返回未授权
 */
@Configuration
public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtils.renderJson(response, UN_AUTHORIZED, null);
    }

//    @Bean
//    public AuthenticationEntryPoint authenticationEntryPoint() {
//        return (request, response, authException) -> ResponseUtils.renderJson(response, UN_AUTHORIZED, null);
//    }
}
